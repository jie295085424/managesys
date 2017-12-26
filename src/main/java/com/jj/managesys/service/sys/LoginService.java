package com.jj.managesys.service.sys;

import com.jj.managesys.domain.sys.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangjunjie
 * @Date 2017/12/26
 * @Description
 */
public interface LoginService {

    String login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    void logout(String token);
}
