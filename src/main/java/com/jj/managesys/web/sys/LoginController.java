package com.jj.managesys.web.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.service.sys.LoginService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangjunjie
 * @Date 2017/12/26
 * @Description
 */
@Log4j2
@RestController
@RequestMapping("/sys")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public HttpResponse login(User user) {

        HttpResponse response = new HttpResponse();

        String result = null;

        try {
            result = loginService.login(user);
        } catch (Exception e) {

            log.error(e);

            if(e.getMessage().equals(ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_NOT_EXISTS.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_NOT_EXISTS);
            }

            else if(e.getMessage().equals(ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR);
            }

            else {
                response.setCodeMessage(ResponseCodeEnum.LOGIN_ERROR);
            }

            return response;
        }

        response.setData(result);

        return response;

    }

    @DeleteMapping("/logout")
    public HttpResponse logout(String token) {

        loginService.logout(token);

        return new HttpResponse();
    }

}
