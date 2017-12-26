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

    ERROR(404,"操作失败！"),
    LOGIN_USERNAME_PASSWORD_NOT_EXISTS(10401,"用户名或密码不能为空！"),
    LOGIN_USERNAME_PASSWORD_ERROR(10402,"用户名或密码错误！"),
    LOGIN_ERROR(10404,"登录失败！");

    private int code;
    private String message;

}
