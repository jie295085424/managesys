package com.jj.managesys.web.sys;

import com.jj.managesys.annotation.AuthValidate;
import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.MethodEnum;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.exceptions.BadRequestException;
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
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.SAVE)
    public HttpResponse save(User user, String token) throws BadRequestException {
        HttpResponse response = new HttpResponse();
        if (userService.save(user, token) != 0) {
            response.setData(user);
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/user/{id}")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.GET)
    public HttpResponse selectById(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(userService.selectById(id, token));
        return response;
    }

    @GetMapping("/user")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.GET)
    public HttpResponse selectAll(int pageNum, int pageSize, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(userService.selectAll(pageNum, pageSize, token));
        return response;
    }

    @PutMapping("/user")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.UPDATE)
    public HttpResponse update(@RequestBody User user, String token) {
        HttpResponse response = new HttpResponse();
        try {
            if (userService.update(user, token) != 0) {
                return response;
            }
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/user/{id}")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.DELETE)
    public HttpResponse delete(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        try {
            if (userService.delete(id, token) != 0) {
                return response;
            }
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

}
