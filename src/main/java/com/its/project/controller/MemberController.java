package com.its.project.controller;

import com.its.project.dto.MemberDTO;
import com.its.project.entity.MemberEntity;
import com.its.project.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.save(memberDTO);
        return "memberPages/login";
    }

    @PostMapping("/duplicateCheck")
    public @ResponseBody String duplicateCheck(@RequestParam String memberEmail) {
        MemberEntity memberEntity = memberService.duplicateCheck(memberEmail);
        if (memberEntity != null) {
            return "no";
        } else {
            return "ok";
        }
    }
    @GetMapping("/login-form")
    public String loginForm(){
        return "memberPages/login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult=memberService.login(memberDTO);
        if(loginResult!=null){
            session.setAttribute("id",loginResult.getId());
            session.setAttribute("memberEmail",loginResult.getMemberEmail());
            return "redirect:/board/list";
        }else{
            return "memberPages/login";
        }
    }
    @GetMapping("/logout")
    public String logout (HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model){
        Long id=(Long)session.getAttribute("id");
        MemberDTO memberDTO=memberService.findById(id);
        model.addAttribute("updateMember",memberDTO);
        return "memberPages/update";
    }
//    @PostMapping("/update")
//    public String update(@ModelAttribute MemberDTO memberDTO){
//        memberService.update(memberDTO);
//        return "redirect:/member/"+memberDTO.getId();
//    }

}
