package com.sparta.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    private final List<Heart> hearts = new ArrayList<>();

    public User(String email, String pw, String nickname){
        this.email = email;
        this.pw = pw;
        this.nickname = nickname;
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
    }
    public void addHeart(Heart heart){
        this.hearts.add(heart);
    }

}