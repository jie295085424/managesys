package com.jj.managesys.mapper.sys;

import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author huangjunjie
 * @ClassName RoleMapper
 * @Description
 * @Date 2017/12/26.
 */
@Mapper
public interface RoleMapper  extends CrudMapper<Role> {

    List<String> getRoleNamesByUser(User user);

    List<Long> getRoleIdsByUsername(@Param(value = "username") String username);
}
