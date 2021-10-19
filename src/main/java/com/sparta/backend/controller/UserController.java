package com.sparta.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController  (UserService userService) {
        this.userService = userService;
    }


    // 회원가입 요청
    @PostMapping("/user/signup")
    public String  registerUser(@RequestBody SignupRequestDto requestDto) {
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