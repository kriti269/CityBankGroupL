package com.citybank.groupl.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthTokenInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        url = url.substring(url.indexOf("CityBank/") + 9);
        String[] openURLs = new String[] {"login", "loginProcess"};
        if(null == request.getSession().getAttribute("user_id") 
        		&& !Arrays.asList(openURLs).contains(url) && !url.contains("resources")){
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
