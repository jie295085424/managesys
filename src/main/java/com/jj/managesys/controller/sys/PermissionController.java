package com.jj.managesys.controller.sys;

import com.jj.managesys.service.sys.PermissionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
