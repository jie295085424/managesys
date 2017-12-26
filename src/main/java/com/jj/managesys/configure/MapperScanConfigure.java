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
