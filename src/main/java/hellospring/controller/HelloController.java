package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping("/hellospring/hello")
    public String hello() {
        System.out.println("hello");
        return "/WEB-INF/hellospring/views/hello.jsp";
    }

    @RequestMapping("/hellospring/main")
    @ResponseBody
    public String main() {
        return "main";
    }
}
