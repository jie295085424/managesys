/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.configure;

import com.jj.managesys.interceptors.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author huangjunjie
 * @ClassName WebMvcConfigure
 * @Description
 * @Date 2017/12/27.
 */
@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/sys/login","/swagger-resources/**","/error");
        super.addInterceptors(registry);
    }
}
