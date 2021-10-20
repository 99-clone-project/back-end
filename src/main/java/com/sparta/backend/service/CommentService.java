package com.sparta.backend.service;

import com.sparta.backend.domain.Comment;
import com.sparta.backend.domain.Post;
import com.sparta.backend.domain.User;
import com.sparta.backend.dto.CommentRequestDto;
import com.sparta.backend.repository.CommentRepository;
import com.sparta.backend.repository.PostRepository;
import com.sparta.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    public Comment createComment (Long userId, CommentRequestDto requestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다.")
        );

        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new NullPointerException("존재하지 않는 게시글입니다.")
        );

        Comment comment = new Comment(user, post, requestDto.getContent());

        return commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getCommentList(Long postId) {

        return commentRepository.findAllByPostPostIdByRegDateDesc(postId);
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 댓글입니다.")
        );

        comment.changeContent(requestDto.getContent());

        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {

        commentRepository.deleteById(commentId);
    }

}
