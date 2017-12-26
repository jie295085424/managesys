package com.jj.managesys.serviceImpl.sys;

import com.jj.managesys.common.utils.EncryUtils;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.mapper.CrudMapper;
import com.jj.managesys.mapper.sys.UserMapper;
import com.jj.managesys.service.sys.UserService;
import com.jj.managesys.serviceImpl.CrudServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


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
    public int save(User user) {

        int isSuccess = 0;
        try {
            String encrypwd = EncryUtils.encrypt(user.getPassword());
            user.setPassword(encrypwd);
            isSuccess = userMapper.save(user);
        } catch (Exception e) {
            log.error(e);
        }
        return isSuccess;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
