package com.icia.board.controller;

import com.icia.board.dto.MemberSaveDTO;
import com.icia.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;
    @GetMapping("/save")
    public String memberLogin(){
        return "board/memberSave";
    }
    @PostMapping("/save")
    public String memberSave(@ModelAttribute MemberSaveDTO memberSaveDTO, HttpSession session){
        Long memberId = ms.save(memberSaveDTO);
        session.setAttribute("memberId",memberId);
        return "board/save";
    }
}
