package com.sparta.backend.repository;

import com.sparta.backend.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository <Heart,Long> {


    Optional<Heart> findByPostIdAndUserId(Long postId, Long userId);

}
