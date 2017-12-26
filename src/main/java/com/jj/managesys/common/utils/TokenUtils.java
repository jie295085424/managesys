package com.jj.managesys.common.utils;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangjunjie
 * @Date 2017/12/27
 * @Description
 */
public class TokenUtils {

    private TokenUtils() {
    }

    private static final TokenUtils instance = new TokenUtils();

    public static TokenUtils getInstance() {
        return instance;
    }

    public static String getToken(String username) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return EncryUtils.encrypt(username);
    }


}
