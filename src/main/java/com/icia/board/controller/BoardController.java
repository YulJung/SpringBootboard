package com.icia.board.controller;

import com.icia.board.common.PagingConst;
import com.icia.board.dto.BoardDetailDTO;
import com.icia.board.dto.BoardPagingDTO;
import com.icia.board.dto.BoardSaveDTO;
import com.icia.board.dto.BoardUpdateDTO;
import com.icia.board.entity.BoardEntity;
import com.icia.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("{boardId}")
    public ResponseEntity findById(@PathVariable Long boardId,Model model){
        BoardDetailDTO board = bs.findById(boardId);
        return new ResponseEntity<BoardDetailDTO>(board, HttpStatus.OK);

    }
    @GetMapping("update/{boardId}")
    public String update(Model model,@PathVariable Long boardId){
        model.addAttribute("board",bs.findById(boardId));
        return "board/update";
    }
    @PostMapping("update")
    public String updateProcess(@ModelAttribute BoardUpdateDTO boardSaveDTO, Model model){

        Long boardId = bs.update(boardSaveDTO);
        model.addAttribute("board",bs.findById(boardId));
        return "/board/findById";

    }
    @PutMapping("update/{boardId}")
    public ResponseEntity updatedo(@RequestBody BoardUpdateDTO boardSaveDTO){
        Long boardId = bs.update(boardSaveDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
    //page 5번글
    @GetMapping("board/(page=1)")
    public String paging(@PageableDefault(page =1) Pageable pageable,Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList",boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;

        int endPage = ((startPage + PagingConst.BLOCK_LIMIT -1) < boardList.getTotalPages()) ? startPage+PagingConst.BLOCK_LIMIT-1:boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "board/paging";
    }


}
