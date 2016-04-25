package mysite.controller;

import mysite.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mysite/guestbook")
public class GuestBookController {
    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping("")
    public String list() {

        return "guestbook/list";
    }
//
//    @RequestMapping("/insert")
//    public String insert() {
//
//        return "guestbook/";
//    }
//
//    @RequestMapping("/deleteform")
//    public String deleteForm() {
//        return "";
//    }
//
//    @RequestMapping("/delete")
//    public String delete() {
//        return "guestbook
//    }
}
