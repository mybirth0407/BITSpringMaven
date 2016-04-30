package mysite.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor2 extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse,
        Object o) throws Exception {
        System.out.println("my intercepter2 prehandle");
        return true;
    }
}
