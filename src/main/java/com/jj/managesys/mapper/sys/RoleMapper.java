package com.jj.managesys.mapper.sys;

import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangjunjie
 * @ClassName RoleMapper
 * @Description
 * @Date 2017/12/26.
 */
@Mapper
public interface RoleMapper  extends CrudMapper<Role> {
}
