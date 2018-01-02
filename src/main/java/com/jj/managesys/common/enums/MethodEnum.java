package com.jj.managesys.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangjunjie
 * @ClassName MethodEnum
 * @Description
 * @Date 2018/1/2.
 */
@Getter
@AllArgsConstructor
public enum MethodEnum {

    GET("GET"),
    DELETE("DELETE"),
    SAVE("SAVE"),
    UPDATE("UPDATE");

    private String method;

}
