package com.sparta.backend.controller;

import com.sparta.backend.domain.Post;
import com.sparta.backend.dto.PostRequestDto;
import com.sparta.backend.security.UserDetailsImpl;
import com.sparta.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 포스트 전체 조회
    @GetMapping("/")
    public List<Post> getList() {

        return postService.getList();
    }

    // 포스트 생성
    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        postService.createPost(userId, requestDto);

        return "redirect:/";
    }

    // 포스트 상세 조회
    @GetMapping("/posts/{postId}")
    public Post readPost(@PathVariable Long postId) {

        return postService.readPost(postId);
    }

    // 포스트 수정
    @PutMapping("/posts/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {

        postService.updatePost(postId, requestDto);

        return "redirect:/post/" + postId;
    }

    // 포스트 삭제
    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

        return "redirect:/";
    }

}