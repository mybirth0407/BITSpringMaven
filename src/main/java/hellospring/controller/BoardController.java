package hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hellospring/board")
public class BoardController {
    @RequestMapping("/list")
    @ResponseBody
    public String list(String kwd, @RequestParam("page") int page) {
        System.out.println(page + ": " + kwd);
        return "BoardController.list()";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(
        @RequestParam(value = "no", required = false) Long no) {
        return "BoardController.delete()";
    }

    @RequestMapping("/view/{no}")
    public ModelAndView view(@PathVariable("no") Long no) {
//        System.out.println(no);
//        return "BoardController.view()";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("no", no);
        modelAndView.addObject("name", "담담");
        modelAndView.addObject("email", "mybirth0407@gmail");
        modelAndView.setViewName(
            "/WEB-INF/hellospring/views/view.jsp");
        return modelAndView;
    }

    @RequestMapping("/view2/{no}")
    public String view2(@PathVariable("no") Long no, Model model) {
        model.addAttribute("no", no);
        model.addAttribute("name", "담담");
        model.addAttribute("email", "mybirth0407@gmail");
        return "/WEB-INF/hellospring/views/view.jsp";
    }
}
