package com.example.springboardlv3.entity;

import com.example.springboardlv3.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne // user한명이 댓글을 여러개
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // Post한개에 댓글을 여러개 // 관계에 주인이 된다 : 외래키 값을 받는다.
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String comment, User user, Post post) { // String comment 부분이 commentRequestDto가 아닌이유
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

    @Transactional
    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
