/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangjunjie
 * @ClassName MapperScanConfigure
 * @Description
 * @Date 2017/12/26.
 */
@Configuration
@MapperScan("com.jj.managesys.mapper")
public class MapperScanConfigure {

}
