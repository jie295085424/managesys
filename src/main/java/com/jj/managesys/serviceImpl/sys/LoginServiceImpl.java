package com.jj.managesys.serviceImpl.sys;

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
        String token = TokenUtils.getToken(user.getUsername());
        redisTemplate.opsForSet().add(RedisTopicEnum.TOKEN_TOPIC.getTopic() + token, user.getUsername());
        return token;
    }

    private boolean validateUser(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User userInDB = userService.selectByUsername(user.getUsername());
        if(userInDB == null) {
            return false;
        } else {
            return EncryUtils.decrypt(user.getPassword(), userInDB.getPassword());
        }
    }

    @Override
    public void logout(String token) {
    }
}
