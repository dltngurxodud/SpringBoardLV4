package com.example.springboardlv3.controller;

import com.example.springboardlv3.dto.CommentRequestDto;
import com.example.springboardlv3.dto.CommentResponseDto;
import com.example.springboardlv3.dto.ResponseDto;
import com.example.springboardlv3.security.UserDetailsImpl;
import com.example.springboardlv3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor //
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

//  @RequestParam : HttpServletRequest와 같은 역할

    @PostMapping // postId를 PathVariable 게시글 id를 들고와서 게시글에 댓글을 생성
    public CommentResponseDto createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(commentRequestDto, userDetails.getUsername()); // getUser()로 하면 엔티티로 바로 접근하여 서비스부분에 비교가 필요가 없다.
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.update(commentId, commentRequestDto, userDetails.getUsername());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) { // 여기도 void를 넣어서 해도된다.
        commentService.delete(commentId, userDetails.getUsername());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 200은 그냥 void로 해도 상관 없지만 NO_CONTENT같은 애들은 204이기 때문에 리턴 http를 해야한다.
    }
}
