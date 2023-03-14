package com.example.springboardlv3.dto;


import com.example.springboardlv3.entity.Comment;
import com.example.springboardlv3.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> comment;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentResponseDtoList) { // 게시글을 뽑아올때 사용하는 생성자, 생성자는 객체를 생성할때
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.comment = commentResponseDtoList;
    }
}
