package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.RoleMapper;
import com.jj.managesys.service.sys.RoleService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

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
}
