package mysite.controller;

import mysite.service.BoardService;
import mysite.vo.BoardVo;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mysite/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    //    @RequestMapping(value = {"", "/list"})
    //    public String list(Model model) {
    //        model.addAttribute("list", boardService.getList());
    //        return "board/list";
    //    }

    @RequestMapping(value = {"", "/list"})
    public String pageList(
        @RequestParam(value = "page", defaultValue = "1") Long page,
        @RequestParam(value = "keyword", defaultValue = "") String keyword,
        Model model) {
        model.addAttribute(
            "list", boardService.pageGetList(page, keyword));
        model.addAttribute(
            "pageMap", boardService.pageMap(page, keyword));
        return "board/list";
    }

    @RequestMapping("/writeform")
    public String writeform(HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }
        return "/board/write";
    }

    @RequestMapping("/write")
    public String write(
        @ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }

        boardVo.setUserNo(authUser.getNo());
        boardService.write(boardVo);
        return "redirect:/mysite/board";
    }

    @RequestMapping("/view/{no}")
    public String view(@PathVariable("no") Long no, Model model) {
        model.addAttribute("boardVo", boardService.get(no));
        boardService.viewCountIncrease(no);
        return "board/view";
    }

    @RequestMapping("/modifyform/{no}")
    public String modifyform(
        @PathVariable("no") Long no, Model model, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }

        model.addAttribute("boardVo", boardService.get(no));
        return "board/modify";
    }

    @RequestMapping("/modify")
    public String modify(
        @ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }

        boardService.modify(boardVo);
        return "redirect:/mysite/board/view/" + boardVo.getNo();
    }

    @RequestMapping("/replyform/{no}")
    public String replyform(
        @PathVariable("no") Long no, Model model, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }

        model.addAttribute("boardVo", boardService.get(no));
        return "board/reply";
    }

    @RequestMapping("/reply")
    public String reply(
        @ModelAttribute BoardVo boardVo, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }

        boardVo.setUserNo(authUser.getNo());
        boardVo.setGroupNo(boardVo.getGroupNo());
        boardVo.setOrderNo(boardVo.getOrderNo() + 1);
        boardVo.setDepth(boardVo.getDepth() + 1);
        boardService.reply(boardVo);
        return "redirect:/mysite/board";
    }

    @RequestMapping("/delete/{no}")
    public String delete(
        @PathVariable("no") Long no, HttpSession httpSession) {
        UserVo authUser = (UserVo) httpSession.getAttribute("authUser");
        if (authUser == null) {
            return "redirect:/mysite/main";
        }
        boardService.delete(no);
        return "redirect:/mysite/board";
    }
}
