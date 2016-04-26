package mysite.controller;

import mysite.service.GuestBookService;
import mysite.vo.GuestBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/mysite/guestbook")
public class GuestBookController {
    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping(value = {"", "/list"})
    public String list(Model model) throws SQLException {
        model.addAttribute("list", guestBookService.getList());
        return "guestbook/list";
    }

    @RequestMapping("/insert")
    public String insert(@ModelAttribute GuestBookVo guestBookVo) {
        guestBookService.insert(guestBookVo);
        return "redirect:/mysite/guestbook";
    }

    @RequestMapping("/deleteform/{no}")
    public String deleteform(@PathVariable("no") Long no, Model model) {
        model.addAttribute("no", no);
        return "guestbook/deleteform";
    }

    @RequestMapping(value = "/delete")
    public String delete(@ModelAttribute GuestBookVo guestBookVo) {
//        System.out.println(no);
//        GuestBookVo guestBookVo = guestBookService.get(no);
        guestBookService.delete(guestBookVo);
        return "redirect:/mysite/guestbook";
    }
}
