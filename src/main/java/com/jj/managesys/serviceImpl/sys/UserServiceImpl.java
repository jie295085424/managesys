/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.domain.sys.User;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.UserMapper;
import com.jj.managesys.service.sys.UserService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author huangjunjie
 * @ClassName UserServiceImpl
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends CrudServiceImpl<User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public CrudMapper<User> getMapper() {
        return userMapper;
    }

}
