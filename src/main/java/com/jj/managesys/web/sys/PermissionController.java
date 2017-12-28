package com.jj.managesys.web.sys;

import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.exceptions.BadRequestException;
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
    public HttpResponse save(Permission permission, String token) throws BadRequestException {
        HttpResponse response = new HttpResponse();
        if (permissionService.save(permission, token) != 0) {
            response.setData(permission);
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @GetMapping("/permission/{id}")
    public HttpResponse selectById(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(permissionService.selectById(id, token));
        return response;
    }

    @GetMapping("/permission")
    public HttpResponse selectAll(int pageNum, int pageSize, String token) {
        HttpResponse response = new HttpResponse();
        response.setData(permissionService.selectAll(pageNum, pageSize, token));
        return response;
    }

    @PutMapping("/permission")
    public HttpResponse update(@RequestBody Permission permission, String token) throws BadRequestException {
        HttpResponse response = new HttpResponse();
        if (permissionService.update(permission, token) != 0) {
            return response;
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }

    @DeleteMapping("/permission/{id}")
    public HttpResponse delete(@PathVariable long id, String token) {
        HttpResponse response = new HttpResponse();
        try {
            if (permissionService.delete(id, token) != 0) {
                return response;
            }
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        response.setCodeMessage(ResponseCodeEnum.ERROR);
        return response;
    }
}
