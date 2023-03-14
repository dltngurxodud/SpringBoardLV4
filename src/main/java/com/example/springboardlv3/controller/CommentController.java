package com.example.springboardlv3.controller;

import com.example.springboardlv3.dto.CommentRequestDto;
import com.example.springboardlv3.dto.CommentResponseDto;
import com.example.springboardlv3.dto.ResponseDto;
import com.example.springboardlv3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor //
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

//  @RequestParam : HttpServletRequest와 같은 역할

    @PostMapping // postId를 PathVariable 게시글 id를 들고와서 게시글에 댓글을 생성
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(commentRequestDto, request);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.update(commentId, commentRequestDto, request);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        return commentService.delete(commentId, request);
    }

}
