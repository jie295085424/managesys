package com.jj.managesys.serviceImpl.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jj.managesys.common.enums.RedisTopicEnum;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.utils.EncryUtils;
import com.jj.managesys.common.utils.TokenUtils;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.service.sys.LoginService;
import com.jj.managesys.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author huangjunjie
 * @Date 2017/12/26
 * @Description
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Override
    public String login(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_NOT_EXISTS.getMessage();
        }
        if(!validateUser(user)) {
            return ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR.getMessage();
        }
        String token = TokenUtils.getInstance().getToken();
        List<String> roleNames = userService.getRoleNames(user);
        JSONObject jo = new JSONObject();
        jo.put("roleNames", roleNames);
        jo.put("username", user.getUsername());
        redisTemplate.opsForValue().set(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token, jo.toJSONString(), 7L, TimeUnit.DAYS);
        return token;
    }

    private boolean validateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User userInDB = userService.selectByUsername(user.getUsername());
        if(userInDB == null) {
            return false;
        } else {
            return EncryUtils.getInstance().decrypt(user.getPassword(), userInDB.getPassword());
        }
    }

    @Override
    public void logout(String token) {

        redisTemplate.delete(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token);
    }
}
