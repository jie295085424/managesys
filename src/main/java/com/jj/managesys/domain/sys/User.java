package com.jj.managesys.domain.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangjunjie
 * @ClassName User
 * @Description
 * @Date 2017/12/26.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    private long id;
    private String username;
    private String password;
    private long roleId;
    private String realName;
    private long createBy;
    private Date createTime;
    private Date updateTime;
}
