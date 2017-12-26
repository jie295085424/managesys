package com.jj.managesys.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author huangjunjie
 * @ClassName Role
 * @Description
 * @Date 2017/12/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private long id;
    private String name;
    private long createBy;
    private String description;
    private Date createTime;
    private Date updateTime;
}
