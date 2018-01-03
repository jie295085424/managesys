package com.jj.managesys.web.sys;

import com.jj.managesys.annotation.AuthValidate;
import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.MethodEnum;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.exceptions.BadRequestException;
import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.service.sys.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjunjie
 * @ClassName RoleController
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@RestController
@RequestMapping("/sys")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.SAVE)
    public HttpResponse save(Role role, String token) {

        HttpResponse response = new HttpResponse();
        try {
            if (roleService.save(role, token) != 0) {
                response.setData(role);
                return response;
            }
        } catch (BadRequestException e) {
            if (e.getMessage().equals(ResponseCodeEnum.ROLE_NAME_NOT_EXISTS.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.ROLE_NAME_NOT_EXISTS);
            }
            if (e.getMessage().equals(ResponseCodeEnum.ROLE_SAVE_NAME_EXISTS.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.ROLE_SAVE_NAME_EXISTS);
            }
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/role/{id}")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.GET)
    public HttpResponse selectById(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(roleService.selectById(id, token));
        return response;
    }

    @GetMapping("/role")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.GET)
    public HttpResponse selectAll(int pageNum, int pageSize, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(roleService.selectAll(pageNum, pageSize, token));
        return response;
    }

    @PutMapping("/role")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.UPDATE)
    public HttpResponse update(@RequestBody Role role, String token) {
        HttpResponse response = new HttpResponse();
        try {
            if (roleService.update(role, token) != 0) {
                return response;
            }
        } catch (BadRequestException e) {
            log.error(e);
            if (e.getMessage().equals(ResponseCodeEnum.ROLE_NAME_NOT_EXISTS.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.ROLE_NAME_NOT_EXISTS);
                return response;
            }
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/role/{id}")
    @AuthValidate(URL = "/sys/role", Roles = {"Root", "Admin"}, Method = MethodEnum.DELETE)
    public HttpResponse delete(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        try {
            if (roleService.delete(id, token) != 0) {
                return response;
            }
        } catch (BadRequestException e) {
            log.error(e);
            if (e.getMessage().equals(ResponseCodeEnum.PERMISSION_DENIED.getMessage())) {
                response.setCodeMessage(ResponseCodeEnum.PERMISSION_DENIED);
                return response;
            }
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

}
