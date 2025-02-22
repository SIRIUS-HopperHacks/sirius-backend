package com.app.hopperhacks.controller;

import com.app.hopperhacks.config.BoardValidator;
import com.app.hopperhacks.domain.Board;
import com.app.hopperhacks.service.BoardService;
import com.sun.net.httpserver.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    public BoardController(){System.out.println("###LOG : BoardController() 생성");}

    @GetMapping("/write")
    public void write(){}

    @PostMapping("/write")
    public String writeSuccess(@ModelAttribute Board board, Model model){
        model.addAttribute("board_list", boardService.board_write(board));
        return "board/writeSuccess";
    }

    @GetMapping("/detail")
    public void detail(Long board_code, Model model, HttpContext context){
        model.addAttribute("board_list", boardService.board_detail(board_code));
        model.addAttribute("conPath", context.getPath());
    }

    @GetMapping("/list")
    public void list(Integer page, Model model){
        boardService.board_list(page,model);
    }

    @GetMapping("/update")
    public void update(long board_code, Model model){
        model.addAttribute("board_list", boardService.selectByBoard_code(board_code));
    }

    @PostMapping("/update")
    public String updateSuccess(@ModelAttribute Board board, Model model){
        model.addAttribute("result", boardService.board_update(board));
        return "board/updateSuccess";
    }

    @PostMapping("/delete")
    public String deleteSuccess(long board_code, Model model){
        model.addAttribute("result", boardService.board_delete(board_code));
        return "board/deleteSuccess";
    }

    @PostMapping("/pageRows")
    public String pageRows(Integer page, Integer pageRows){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attrs.getRequest().getSession();
        session.setAttribute("pageRows", pageRows);
        return "redirect:/board/list?page="+page;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setValidator(new BoardValidator());
    }

}
