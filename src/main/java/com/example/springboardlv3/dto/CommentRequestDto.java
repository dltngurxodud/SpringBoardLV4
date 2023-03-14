package com.example.springboardlv3.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequestDto {

    private Long postId;
    private String comment;
//    private Long id;
//    private String content;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
//    private String username;
}
