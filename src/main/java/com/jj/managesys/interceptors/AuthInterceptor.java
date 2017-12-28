/**
 * @Project: rcp-java
 * @Copyright: ©2017  广州弘度信息科技有限公司. 版权所有
 */
package com.jj.managesys.interceptors;
import com.alibaba.fastjson.JSON;
import com.jj.managesys.beans.sys.UserRole;
import com.jj.managesys.common.HttpResponse;
import com.jj.managesys.common.enums.ResponseCodeEnum;
import com.jj.managesys.common.utils.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if(StringUtils.isEmpty(token)) {

            sendErrorResponse(httpServletResponse, ResponseCodeEnum.TOKEN_NOT_EXISTS);
            return false;
        }

        if(!TokenUtils.getInstance().validate(token)) {

            sendErrorResponse(httpServletResponse, ResponseCodeEnum.TOKEN_ERROR);
            return false;
        }

        String auth = TokenUtils.getInstance().getAuth(token);

        /*
          权限校验
         */

        return true;
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
