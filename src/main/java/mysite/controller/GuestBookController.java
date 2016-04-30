package mysite.controller;

import mysite.service.GuestBookService;
import mysite.vo.GuestBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mysite/guestbook")
public class GuestBookController {
    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping("/ajax")
    public String ajaxMain() {
        return "guestbook/ajax-main";
    }

    @RequestMapping(value = {"", "/list"})
    public String list(Model model) throws SQLException {
        model.addAttribute("list", guestBookService.getList());
        return "guestbook/list";
    }

    @RequestMapping("/ajax-list/{page}")
    @ResponseBody
    public Map<String, Object> ajaxList(@PathVariable("page") Long page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", guestBookService.getList(page));
        return map;
    }

    @RequestMapping("/ajax-insert")
    @ResponseBody
    public Map<String, Object> ajaxInsert(
        @ModelAttribute GuestBookVo guestBookVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", guestBookService.insert(guestBookVo));
        return map;
    }

    @RequestMapping("/ajax-delete")
    @ResponseBody
    public Map<String, Object> ajaxDelete(
        @ModelAttribute GuestBookVo guestBookVo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");
        map.put("data", guestBookService.delete(guestBookVo));
        return map;
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

    @RequestMapping("/delete")
    public String delete(@ModelAttribute GuestBookVo guestBookVo) {
//        System.out.println(no);
//        GuestBookVo guestBookVo = guestBookService.get(no);
        guestBookService.delete(guestBookVo);
        return "redirect:/mysite/guestbook";
    }
}
