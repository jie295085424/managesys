package com.jj.managesys.controller.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
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
    public HttpResponse save(Role role) {
        HttpResponse response = new HttpResponse();
        if(roleService.save(role) != 0) {
            response.setData(role);
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/role/{id}")
    public HttpResponse selectById(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        response.setData(roleService.selectById(id));
        return response;
    }

    @GetMapping("/role")
    public HttpResponse selectAll() {
        HttpResponse response = new HttpResponse();
        response.setData(roleService.selectAll());
        return response;
    }

    @PutMapping("/role")
    public HttpResponse update(@RequestBody Role role) {
        HttpResponse response = new HttpResponse();
        if(roleService.update(role) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/role/{id}")
    public HttpResponse delete(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        if(roleService.delete(id) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }
}
