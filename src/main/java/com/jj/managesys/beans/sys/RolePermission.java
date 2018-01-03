/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.beans.sys;

import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.domain.sys.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huangjunjie
 * @ClassName RolePermission
 * @Description
 * @Date 2017/12/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {

    private Role role;
    private List<Permission> permissions;
}
