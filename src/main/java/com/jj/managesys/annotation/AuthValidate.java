package com.jj.managesys.annotation;

import com.jj.managesys.common.enums.MethodEnum;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangjunjie
 * @ClassName AuthValidate
 * @Description
 * @Date 2018/1/2.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthValidate {


    String URL() default "";

    String[] Roles() default {"Admin"};

    MethodEnum Method() default MethodEnum.GET;

}
