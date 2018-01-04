package com.jj.managesys.common.utils;


import com.alibaba.fastjson.JSON;
import com.jj.managesys.beans.sys.RoleUserDTO;
import com.jj.managesys.common.enums.RedisTopicEnum;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author huangjunjie
 * @Date 2017/12/27
 * @Description
 */
public class TokenUtils {

    private TokenUtils() {
    }

/*    //饿汉式
    private static TokenUtils instance = new TokenUtils();

    public static TokenUtils getInstance() {
        return instance;
    }*/

    //ioDH(Initialization Demand Holder,初始化需求持有者)
    private static class TokenUtilsHolder {
        private static final TokenUtils instance = new TokenUtils();
    }

    public static TokenUtils getInstance() {
        return TokenUtilsHolder.instance;
    }

    public String getToken() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        while (true) {

            String token = UUID.randomUUID().toString().replaceAll("-","");

            if(SpringHelper.getBean(StringRedisTemplate.class).opsForValue().get(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token) != null) { continue; }

            return token;
        }
    }

    public boolean validate(String token) {

        if(SpringHelper.getBean(StringRedisTemplate.class).opsForValue().get(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token) != null) {
            return true;
        } else {
            return false;
        }
    }

    public RoleUserDTO getRoleUser(String token) {

        return JSON.parseObject(SpringHelper.getBean(StringRedisTemplate.class).opsForValue().get(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token), RoleUserDTO.class);
    }

}
