package hellospring.controller;

import hellospring.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("hellospring/user")
@Controller
public class UserController {

    @RequestMapping("/joinform")
    public String joinform() {
        return "/WEB-INF/hellospring/views/joinform.jsp";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
//    @ResponseBody
    public String join(@ModelAttribute UserVo userVo) {
        System.out.println(userVo);
//        return "UserController.Join()";
        return "redirect:/main";
    }

    @RequestMapping("/loginform")
    //response body 쓰면 뭐가 달라진다고?? ㅜㅠㅜㅠㅜ
    @ResponseBody
    public String loginform() {
        return "UserController.loginform()";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    //response body 쓰면 뭐가 달라진다고?? ㅜㅠㅜㅠㅜ
    @ResponseBody
    public String login() {
        return "UserController.login()";
    }
}
