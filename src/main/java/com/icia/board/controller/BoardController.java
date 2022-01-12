package com.icia.board.controller;

import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService bs;

    //게시글 저장 페이지요청
    @GetMapping("save")
    public String saveView(){
        return "/board/save";
    }
    //게시글 저장 실행
    @PostMapping("save")
    public String saveProcess(@ModelAttribute BoardSaveDTO boardSaveDTO){
        System.out.println(boardSaveDTO.toString()+"!!!");
//        if(bindingResult.hasErrors()) return "board/save";
        bs.save(boardSaveDTO);
        return "redirect:/board/findAll";
    }
    @GetMapping("findAll")
    public String findAll(Model model){
        List<BoardDetailDTO> boardList = bs.findAll();
        model.addAttribute("boardList",boardList);
        return "board/findAll";
    }
    @GetMapping("{boardId}")
    public String findById(Model model, @PathVariable Long boardId){
        //log기록 남기기
        log.info("글보기 메서드 호출 요청글번호{}",boardId);
        BoardDetailDTO board = bs.findById(boardId);
        model.addAttribute("board",board);
        return "board/findById";
    }

}
