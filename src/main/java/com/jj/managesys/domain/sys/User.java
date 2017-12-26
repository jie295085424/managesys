/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
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
    private String realName;
    private long createBy;
    private Date createTime;
    private Date updateTime;
}
