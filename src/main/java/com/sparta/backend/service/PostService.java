package com.sparta.backend.service;

import com.sparta.backend.domain.Post;
import com.sparta.backend.domain.User;
import com.sparta.backend.dto.PostRequestDto;
import com.sparta.backend.repository.PostRepository;
import com.sparta.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 포스트 전체 조회
    public List<Post> getList() {

        return postRepository.findAllByOrderByRegDateDesc();
    }

    // 포스트 생성
    public void createPost(Long userId, PostRequestDto requestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다.")
        );

        Post post = new Post(user, requestDto);

        postRepository.save(post);
    }

    // 포스트 상세 조회
    public Post readPost(Long postId) {

        return postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 포스트입니다.")
        );
    }

    // 포스트 수정
    public Post updatePost(Long postId, PostRequestDto requestDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 포스트입니다.")
        );

        post.updatePost(requestDto);

        return postRepository.save(post);
    }

    // 포스트 삭제
    public void deletePost(Long postId) {

        postRepository.deleteById(postId);
    }

}
