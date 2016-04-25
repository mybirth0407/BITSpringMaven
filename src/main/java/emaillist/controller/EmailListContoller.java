package emaillist.controller;

import emaillist.dao.EmailListDao;
import emaillist.vo.EmailListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmailListContoller {
    //    @Autowired
    @Autowired
    private EmailListDao emailListDao;

//    @RequestMapping(value = "/emaillist/insert", method = RequestMethod.POST)
//    public String insert(@RequestParam(value = "fn", required = true,
//        defaultValue = "") String firstName, @RequestParam(value = "ln",
//        required = true, defaultValue = "") String lastName, @RequestParam
//        (value = "email", required = true, defaultValue = "") String email) {
//        EmailListVo emailListVo = new EmailListVo();
//        emailListVo.setEmail(email);
//        emailListVo.setFirstName(firstName);
//        emailListVo.setLastName(lastName);
//
//        emailListDao.insert(emailListVo);
//        return "redirect:/index";
//    }

    @RequestMapping(value = "/emaillist/insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute EmailListVo vo) {
        emailListDao.insert(vo);
        System.out.println(vo);
        return "redirect:/emaillist/index";
    }

    @RequestMapping("/emaillist/form")
    public String form() {
        return "/WEB-INF/emaillist/views/form.jsp";
    }

    @RequestMapping("/emaillist/index")
    public String index(Model model) {
        List<EmailListVo> list = emailListDao.getList();
        model.addAttribute("list", list);
        return "/WEB-INF/emaillist/views/index.jsp";
    }

    @RequestMapping("/emaillist/index2")
    public ModelAndView index2() {
        List<EmailListVo> list = emailListDao.getList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("/WEB-INF/emaillist/views/index.jsp");
        return modelAndView;
    }
}
