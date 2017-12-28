/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.RedisTopicEnum;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.utils.SpringHelper;
import com.jj.managesys.common.utils.TokenUtils;
import com.jj.managesys.domain.sys.Permission;
import com.jj.managesys.service.sys.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/**
 * @author huangjunjie
 * @ClassName AuthInterceptor
 * @Description
 * @Date 2017/12/27.
 */
@Log4j2
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getParameter("token");

        if (StringUtils.isEmpty(token)) {
            sendErrorResponse(httpServletResponse, ResponseCodeEnum.TOKEN_NOT_EXISTS);
            return false;
        }

        if (!TokenUtils.getInstance().validate(token)) {
            sendErrorResponse(httpServletResponse, ResponseCodeEnum.TOKEN_ERROR);
            return false;
        }

        String username = JSON.parseObject(TokenUtils.getInstance().getAuth(token)).get("username").toString();
        if("root".equals(username.trim())) {
            return true;
        }

        String method = httpServletRequest.getMethod();
        String href = httpServletRequest.getRequestURI();
        int isPermit = 0;

        StringRedisTemplate redisTemplate = SpringHelper.getBean(StringRedisTemplate.class);
        String permissions = redisTemplate.opsForValue().get(RedisTopicEnum.PERMISSION_TOPIC.getTopic() + token);

        if (!StringUtils.isEmpty(permissions)) {
            List<Permission> permissionsInCache = JSON.parseArray(permissions, Permission.class);
            isPermit = permissionsInCache.parallelStream()
                            .filter(permission -> href.startsWith(permission.getHref()) && method.equals(permission.getMethod()))
                            .collect(toList()).size();
            if (isPermit > 0) {
                return true;
            }
        }

        UserService userService = SpringHelper.getBean(UserService.class);
        List<Permission> permissionsInDB = userService.getPermissionsByUsername(username);
        isPermit = permissionsInDB.parallelStream()
                         .filter(permission -> href.startsWith(permission.getHref()) && method.equals(permission.getMethod()))
                         .collect(toList()).size();
        if (isPermit > 0) {
            redisTemplate.opsForValue().set(RedisTopicEnum.PERMISSION_TOPIC.getTopic() + token, JSON.toJSONString(permissionsInDB), 12L, TimeUnit.HOURS);
            return true;
        }

        sendErrorResponse(httpServletResponse, ResponseCodeEnum.PERMISSION_DENIED);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void sendErrorResponse(HttpServletResponse httpServletResponse, ResponseCodeEnum codeEnum) {

        HttpResponse response = new HttpResponse();
        response.setCodeMessage(codeEnum);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            httpServletResponse.getWriter().write(JSON.toJSONString(response));
        } catch (IOException e) {
            log.error(e);
        }
    }

}
