/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.beans.sys;

import com.jj.managesys.domain.sys.Role;
import com.jj.managesys.domain.sys.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangjunjie
 * @ClassName RoleUserDTO
 * @Description
 * @Date 2017/12/28.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUserDTO {

    private Role role;
    private User user;
}
