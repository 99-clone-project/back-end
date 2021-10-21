package com.sparta.backend.service;

import com.sparta.backend.domain.Heart;
import com.sparta.backend.domain.Post;
import com.sparta.backend.domain.User;

import com.sparta.backend.repository.HeartRepository;
import com.sparta.backend.repository.PostRepository;
import com.sparta.backend.repository.UserRepository;
import com.sparta.backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    
    @Transactional
    public boolean liked(Long postId, UserDetailsImpl userDetails){

        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Heart heart =heartRepository.findByPostIdAndUserId(post.getId(), user.getId()).orElse(null);
        if(heart ==null) {
            heart =  heartRepository.save(new Heart(post, user));
            user.addHeart(heart);
            post.addHeart(heart);
            return true;
        }
        else {
            user.deleteHeart(heart);
            post.deleteHeart(heart);
            heartRepository.delete(heart);
            return false;
        }






    }

}
