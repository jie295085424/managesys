package com.jj.managesys.controller.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.domain.sys.User;
import com.jj.managesys.service.sys.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjunjie
 * @ClassName UserController
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@RestController
@RequestMapping("/sys")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public HttpResponse save(User user) {
        HttpResponse response = new HttpResponse();
        userService.save(user);
        response.setData(user);
        return response;
    }

    @GetMapping("/user/{id}")
    public HttpResponse selectById(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        response.setData(userService.selectById(id));
        return response;
    }
}
