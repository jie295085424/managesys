package com.jj.managesys.common.utils;


import com.jj.managesys.common.enums.RedisTopicEnum;
import org.springframework.data.redis.core.StringRedisTemplate;

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

    private static TokenUtils instance = null;

    public static TokenUtils getInstance() {
        if(instance == null) {
            instance = new TokenUtils();
        }
        return instance;
    }

    public String getToken() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        while (true) {
            String token = UUID.randomUUID().toString().replaceAll("-","");
            if(SpringHelper.getBean(StringRedisTemplate.class).opsForValue().get(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token) != null) {
                continue;
            }
            return token;
        }
    }
}
