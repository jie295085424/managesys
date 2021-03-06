package com.jj.managesys.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author huangjunjie
 * @ClassName ResponseCodeEnum
 * @Description
 * @Date 2017/12/26.
 */
@Getter
@AllArgsConstructor
public enum  ResponseCodeEnum {

    ERROR(404, "操作失败！"),

    LOGIN_USERNAME_PASSWORD_NOT_EXISTS(10401, "用户名或密码不能为空！"),
    LOGIN_USERNAME_PASSWORD_ERROR(10402, "用户名或密码错误！"),
    LOGIN_ERROR(10404, "登录失败！"),

    TOKEN_NOT_EXISTS(10405, "token不存在！"),
    TOKEN_ERROR(10406, "token已过时或无效！"),
    PERMISSION_DENIED(10407, "权限不足，拒绝访问！"),

    ROLE_SAVE_NAME_EXISTS(20401, "角色已存在！"),
    ROLE_NAME_NOT_EXISTS(20402, "角色名称不能为空！");

    private int code;
    private String message;

}
