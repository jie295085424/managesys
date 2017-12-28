package com.jj.managesys.serviceImpl.sys;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.jj.managesys.beans.sys.RoleUserDTO;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.exceptions.BadRequestException;
import com.jj.managesys.common.utils.SpringHelper;
import com.jj.managesys.common.utils.TokenUtils;
import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.RoleMapper;
import com.jj.managesys.service.sys.RoleService;
import com.jj.managesys.service.sys.UserService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName RoleServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@Service
@Transactional(rollbackFor = TransactionException.class)
public class RoleServiceImpl extends CrudServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public CrudMapper<Role> getMapper() {
        return roleMapper;
    }

    @Override
    public int save(Role role, String token) throws BadRequestException {

        int isSuccess = 0;

        if(roleMapper.getRoleByName(role.getName()) != null) {
            throw new BadRequestException(ResponseCodeEnum.ROLE_SAVE_NAME_EXIST);
        }

        RoleUserDTO roleUserDTO = TokenUtils.getInstance().getRoleUser(token);

        role.setCreateBy(roleUserDTO.getRole().getId());
        role.setParents(new StringBuilder().append(roleUserDTO.getRole().getParents())
                .append(",").append(roleUserDTO.getRole().getId()).toString());

        isSuccess = roleMapper.save(role);

        return isSuccess;
    }

    @Override
    public Page selectAll(int pageNum, int pageSize, String token) {


        return null;
    }


}
