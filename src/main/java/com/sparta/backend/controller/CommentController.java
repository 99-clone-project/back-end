package com.sparta.backend.controller;

import com.sparta.backend.domain.Comment;
import com.sparta.backend.dto.CommentRequestDto;
import com.sparta.backend.security.UserDetailsImpl;
import com.sparta.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return commentService.createComment(userId, requestDto);
    }

    // 댓글 조회
    @GetMapping("/comments/{postId}")
    public List<Comment> getCommentList(@PathVariable Long postId) {

        return commentService.getCommentList(postId);
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {

        return commentService.updateComment(commentId, requestDto);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);

        return "댓글 삭제 성공!";
    }

}
