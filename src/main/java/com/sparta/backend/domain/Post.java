package com.sparta.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.backend.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "user")
@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String image;

    @OneToMany(mappedBy = "post")
    private final List<Heart>hearts = new ArrayList<>();

    //heart 갯수 보여주기
    @Column(columnDefinition = "integer default 0")
    private int heartNum;

    public Post(User user, PostRequestDto requestDto) {
        this.user = user;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
    }

    public void updatePost(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.image = requestDto.getImage();
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
        this.heartNum -=1;

    }
    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        this.heartNum +=1;
    }
}