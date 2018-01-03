package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.beans.sys.RoleUserDTO;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.exceptions.BadRequestException;
import com.jj.managesys.common.utils.EncryUtils;
import com.jj.managesys.common.utils.SpringHelper;
import com.jj.managesys.common.utils.TokenUtils;
import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.PermissionMapper;
import com.jj.managesys.mapper.sys.RoleMapper;
import com.jj.managesys.mapper.sys.UserMapper;
import com.jj.managesys.service.sys.UserService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author huangjunjie
 * @ClassName UserServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@Service
@Transactional(rollbackFor = TransactionException.class)
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public CrudMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public int save(User user, String token) throws BadRequestException {

        if(!validatePermission(user, token)) {
            throw new BadRequestException(ResponseCodeEnum.PERMISSION_DENIED);
        }

        int isSuccess = 0;
        try {
            user.setPassword(EncryUtils.getInstance().encrypt(user.getPassword()));
            isSuccess = userMapper.save(user);
        } catch (Exception e) {
            log.error(e);
        }

        return isSuccess;
    }

    @Override
    public int update(User user, String token) throws BadRequestException {

        if(!validatePermission(user, token)) {
            throw new BadRequestException(ResponseCodeEnum.PERMISSION_DENIED);
        }

        int isSuccess = 0;
        try {
            user.setPassword(EncryUtils.getInstance().encrypt(user.getPassword()));
            isSuccess = userMapper.update(user);
        } catch (Exception e) {
            log.error(e);
        }
        return isSuccess;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }


    @Override
    public List<Permission> getPermissionsByUsername(String username) {

        PermissionMapper permissionMapper = SpringHelper.getBean(PermissionMapper.class);
        Role role = getRoleByUsername(username);
        return permissionMapper.getPermissionsByRoleId(role.getId());
    }

    @Override
    public Role getRoleByUsername(String username) {

        RoleMapper roleMapper = SpringHelper.getBean(RoleMapper.class);
        return roleMapper.getRoleByUsername(username);
    }

    @Override
    public int deleteByRoleId(long id) {

        return userMapper.deleteByRoleId(id);
    }

    private boolean validatePermission(User user, String token) {

        RoleUserDTO roleUserDTO = TokenUtils.getInstance().getRoleUser(token);
        Role role = SpringHelper.getBean(RoleMapper.class).selectById(user.getRoleId());

        if(!role.getParents().equals(new StringBuilder()
                .append(roleUserDTO.getRole().getParents()).append(",")
                .append(roleUserDTO.getRole().getId()).toString())) {

            return false;
        }
        return true;
    }

}
