package com.jj.managesys.web.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
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
        if( userService.save(user) != 0) {
            response.setData(user);
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/user/{id}")
    public HttpResponse selectById(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        response.setData(userService.selectById(id));
        return response;
    }

    @GetMapping("/user")
    public HttpResponse selectAll() {
        HttpResponse response = new HttpResponse();
        response.setData(userService.selectAll());
        return response;
    }

    @PutMapping("/user")
    public HttpResponse update(@RequestBody User user) {
        HttpResponse response = new HttpResponse();
        if(userService.update(user) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/user/{id}")
    public HttpResponse delete(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        if(userService.delete(id) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

}