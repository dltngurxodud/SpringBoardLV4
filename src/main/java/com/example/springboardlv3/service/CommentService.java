package com.example.springboardlv3.service;

import com.example.springboardlv3.dto.CommentRequestDto;
import com.example.springboardlv3.dto.CommentResponseDto;
import com.example.springboardlv3.entity.Comment;
import com.example.springboardlv3.entity.Post;
import com.example.springboardlv3.entity.User;
import com.example.springboardlv3.entity.UserRoleEnum;
import com.example.springboardlv3.exception.ApiException;
import com.example.springboardlv3.exception.ExceptionEnum;
import com.example.springboardlv3.jwt.JwtUtil;
import com.example.springboardlv3.repository.CommentRepository;
import com.example.springboardlv3.repository.PostRepository;
import com.example.springboardlv3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

    private Comment getComment(Long replyId) {
        return commentRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }

    private void checkRole(Long commentId, User user) {
        if (user.getRole() == UserRoleEnum.ADMIN) return;
        commentRepository.findByIdAndUser(commentId, user).orElseThrow(
                () -> new IllegalArgumentException("권한이 없습니다.")
        );
    }

    private User userInfo(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new ApiException(ExceptionEnum.NOT_FOUND_USER) // 회원을 찾을 수 없습니다.
        );
    }

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, String userName) {

        User user = userInfo(userName);
        Post post = getPost(commentRequestDto.getPostId());

        Comment comment = commentRepository.saveAndFlush(new Comment(commentRequestDto.getComment(), user, post));
        return new CommentResponseDto(comment, userName);
    }

    @Transactional
    public CommentResponseDto update(Long commentId, CommentRequestDto commentRequestDto, String userName) {
        User user = userInfo(userName);

        Comment comment = getCommentAdminInfo(commentId, user); // 관리자 권한 밑에는 유저가 댓글쓰기인데 이렇게 써버리면 밑에 관리자 메서드 거치면서 관리자 체크 하면서 관리자와 유저 거르기?
//      Comment comment = getComment(commentId);
        checkRole(commentId, user);
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment, user.getUsername());
    }

    @Transactional
    public void delete(Long commentId, String userName) {
        User user = userInfo(userName);
//        Comment comment = getCommentAdminInfo(commentId, user);
        getComment(commentId); // 위와 같은 것이라서 주석처리 해야하는것?
        checkRole(commentId, user);
        commentRepository.deleteById(commentId);
//        return ResponseEntity.status(HttpStatus.OK).body("댓글 삭제 완료");
    }



//    관리자 계정만 모든 댓글 수정, 삭제 가능
    public Comment getCommentAdminInfo(Long id, User user) {
        Comment comment;
        if (user.getRole().equals(UserRoleEnum.ADMIN)) {
            // 관리자 계정이기 때문에 게시글 아이디만 일치하면 수정,삭제 가능
            comment = commentRepository.findById(id).orElseThrow(
                    () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ADMIN)
            );
        } else {
            // 사용자 계정이므로 게시글 아이디와 작성자 이름이 있는지 확인하고 있으면 수정,삭제 가능
            comment = commentRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT)
            );
        }
        return comment;
    }
}
