package com.li.oauthserver.handler;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname AuthExceptionEntryPoint
 * @Description TODO
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-20 13:23
 * @Version 1.0
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    /**
     * token错误时进入到这里
     *
     * @param request
     * @param response
     * @param authException
     * @throws ServletException
     */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {

        Map map = new HashMap();
        map.put("code", 401);
        map.put("message", authException.getMessage());
        map.put("data", "");
        map.put("path", request.getServletPath());
        map.put("timestamp", String.valueOf(new Date().getTime()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
