package com.jj.managesys.mapper.sys;

import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangjunjie
 * @ClassName PermissionMapper
 * @Description
 * @Date 2017/12/26.
 */
@Mapper
public interface PermissionMapper extends CrudMapper<Permission> {
}
