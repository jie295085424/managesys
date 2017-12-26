package com.jj.managesys.common;

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

    ERROR(404,"操作失败！");


    private int code;
    private String message;

}
