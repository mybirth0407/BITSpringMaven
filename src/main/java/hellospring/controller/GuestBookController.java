package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/guestbook/*")
public class GuestBookController {

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        return "GuestbookController.list();";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert() {
        return "GuestbookController.insert();";
    }
}