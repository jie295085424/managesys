package com.jj.managesys.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author huangjunjie
 * @ClassName Permission
 * @Description
 * @Date 2017/12/26.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    private long id;
    private String name;
    private String method;
    private String href;
    private String description;
    private long createBy;
    private Date createTime;
    private Date updateTime;

}
