package mysite.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse
        response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if (httpSession != null) {
            httpSession.removeAttribute("authUser");
            httpSession.invalidate();
        }
        response.sendRedirect(request.getContextPath());
        return false;
    }
}
