package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.PermissionMapper;
import com.jj.managesys.service.sys.PermissionService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huangjunjie
 * @ClassName PermissionServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends CrudServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public CrudMapper<Permission> getMapper() {
        return permissionMapper;
    }
}
