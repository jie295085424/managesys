package com.jj.managesys.controller.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.service.sys.PermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangjunjie
 * @ClassName PermissionController
 * @Description
 * @Date 2017/12/26.
 */
@Log4j2
@RestController
@RequestMapping("/sys")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission")
    public HttpResponse save(Permission permission) {
        HttpResponse response = new HttpResponse();
        if (permissionService.save(permission) != 0) {
            response.setData(permission);
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/permission/{id}")
    public HttpResponse selectById(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        response.setData(permissionService.selectById(id));
        return response;
    }

    @GetMapping("/permission")
    public HttpResponse selectAll() {
        HttpResponse response = new HttpResponse();
        response.setData(permissionService.selectAll());
        return response;
    }

    @PutMapping("/permission")
    public HttpResponse update(@RequestBody Permission permission) {
        HttpResponse response = new HttpResponse();
        if (permissionService.update(permission) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/permission/{id}")
    public HttpResponse delete(@PathVariable long id) {
        HttpResponse response = new HttpResponse();
        if (permissionService.delete(id) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }
}
