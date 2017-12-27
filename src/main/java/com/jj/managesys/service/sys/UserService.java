package com.jj.managesys.service.sys;


import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.service.CrudService;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName UserService
 * @Description
 * @Date 2017/12/26.
 */
public interface UserService extends CrudService<User> {

    User selectByUsername(String username);

    List<String> getRoleNames(User user);

    List<Permission> getPermissionsByUsername(String username);
}
