package com.sparta.backend.controller;

import com.sparta.backend.security.UserDetailsImpl;
import com.sparta.backend.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping("/posts/{postId}/like")
    public String likePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){


        boolean liked = heartService.liked(postId, userDetails);
        if(liked){
            return "true";
        } else{
            return "false";
        }

    }
}
