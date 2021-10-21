package com.sparta.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private int heartNum;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Heart(Post post, User user) {
        this.user = user;
        this.post = post;
    }

}
