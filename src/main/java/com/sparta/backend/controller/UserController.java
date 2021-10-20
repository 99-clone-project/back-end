package com.sparta.backend.controller;

import com.sparta.backend.dto.SignupRequestDto;
import com.sparta.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController  (UserService userService) {
        this.userService = userService;
    }


    // 회원가입 요청
    @PostMapping("/user/signup")
    public String  registerUser( @Valid @RequestBody SignupRequestDto requestDto, Errors errors) {

        if(errors.hasErrors()){
            System.out.println("회원가입 안됨");
            return "redirect:/user/signup";}

        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    //회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {return "signup";}


    //로그인 요청
    @PostMapping("/user/login")
    public List<Map<String, String>> login(@RequestBody SignupRequestDto requestDto) {
        List<Map<String, String>> ret = userService.login(requestDto);
        return ret;

    }


}