package com.example.springboardlv3.dto;

import com.example.springboardlv3.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUser().getUsername();
    }

    public CommentResponseDto(Comment comment, String username) {
        this.id = comment.getId();
        this.content = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = username;
    }


}
