package com.jj.managesys.mapper.sys;
import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.mapper.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName UserMapper
 * @Description
 * @Date 2017/12/26.
 */
@Mapper
public interface UserMapper extends CrudMapper<User> {

    User selectByUsername(String username);

}
