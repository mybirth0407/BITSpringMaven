package mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController  {
    @RequestMapping("/mysite/main")
    public String index() {
//        return "/WEB-INF/mysite/views/main/index.jsp";
        return "main/index";
    }
}
