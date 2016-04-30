package mysite.interceptor;

import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler) throws Exception {
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");

        UserVo userVo = new UserVo();
        userVo.setEmail(email);
        userVo.setPasswd(passwd);

        UserVo authUser = userService.login(userVo);
        if (authUser == null) {
            response.sendRedirect(
                request.getContextPath() + "/mysite/user/loginform");
            return false;
        }

        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute("authUser", authUser);
        response.sendRedirect(request.getContextPath() + "/mysite/main");
//        userService.login(userVo);
        return false;
    }
}
