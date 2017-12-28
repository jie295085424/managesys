package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.common.utils.EncryUtils;
import com.jj.managesys.common.utils.SpringHelper;
import com.jj.managesys.domain.sys.Permission;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    public int save(User user, String token) {

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
    public int update(User user, String token) {

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

        RoleMapper roleMapper = SpringHelper.getBean(RoleMapper.class);
        PermissionMapper permissionMapper = SpringHelper.getBean(PermissionMapper.class);
        List<Long> roleIds = roleMapper.getRoleIdsByUsername(username);
        Set<Permission> permissions = new HashSet<>();
        for(Long roleId : roleIds) {
            permissions.addAll(permissionMapper.getPermissionsByRoleId(roleId));
        }
        return new ArrayList<>(permissions);
    }

    @Override
    public String getRoleName(User user) {

        if(user.getRoleId() == 0) {
            user = userMapper.selectByUsername(user.getUsername());
        }
        RoleMapper roleMapper = SpringHelper.getBean(RoleMapper.class);
        return roleMapper.selectById(user.getRoleId()).getName();
    }
}
