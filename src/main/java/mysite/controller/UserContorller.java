package mysite.controller;

import mysite.service.UserService;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mysite/user")
public class UserContorller {

    @Autowired
    private UserService userService;

    @RequestMapping("/joinform")
    public String joinform() {
        //        return "/WEB-INF/views/user/joinform.jsp";
        return "user/joinform";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute UserVo uservo) {
        return "redirect:/mysite/user/joinsuccess";
    }

    @RequestMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping("/loginform")
    public String loginform() {
        return "user/loginform";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute UserVo vo, HttpSession session) {
        UserVo userVo = userService.login(vo);

        if (userVo == null) {
            return "user/loginform_fail";
        }
        session.setAttribute("authUser", userVo);
        return "redirect:/mysite/main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // 인증유무 체크
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser != null) {
            session.removeAttribute("authUser");
            session.invalidate();
        }

        return "redirect:/mysite/main";
    }

    @RequestMapping("/checkemail")
    @ResponseBody
    public Map<String, Object> checkEmail(
        @RequestParam(value = "email", required = true, defaultValue = "")
            String email) {
        System.out.println("ASDF" + email);
        UserVo userVo = userService.getUser(email);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", userVo == null);

        return map;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "mysite/hello:안녕";
    }
}
