package org.airlineTickets.controller;

import lombok.RequiredArgsConstructor;
import org.airlineTickets.Service.MemberService;
import org.airlineTickets.dto.MemberFormDto;
import org.airlineTickets.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "";
    }
    @PostMapping(value = "")
    public String memberForm(MemberFormDto memberFormDto) {
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/"; // /로 URL을 요청
    }
    @PostMapping(value = "")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        //검증하려는 객체의 앞에 @Valid 어노테이션을 선언하고 bindingResult 객체를 추가하여 검사 후 결과는 bindingResult에 담는다
        if(bindingResult.hasErrors()) { //에러가 있다면 회원가입 페이지로 이동
            return "";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch (IllegalStateException e ) {
            model.addAttribute("errorMessage", e.getMessage());
            return ""; //중복 회원 가입 예외가 발생하면 에러 메시지를 뷰로 전달
        }
        return "redirect:/";
    }
    @GetMapping(value = "")
    public String loginMember() {
        return "";
    }
    @GetMapping(value = "")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "";
    }
}
