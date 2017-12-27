/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.beans.sys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangjunjie
 * @ClassName UserRole
 * @Description
 * @Date 2017/12/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    private long id;
    private long roleId;
    private long userId;
}
