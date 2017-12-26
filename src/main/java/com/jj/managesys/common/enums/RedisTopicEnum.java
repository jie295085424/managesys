package com.jj.managesys.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author huangjunjie
 * @Date 2017/12/27
 * @Description
 */
@Getter
@AllArgsConstructor
public enum RedisTopicEnum {

    TOKEN_TOPIC("token:");

    private String topic;

}
