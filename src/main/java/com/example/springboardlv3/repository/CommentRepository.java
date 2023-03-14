package com.example.springboardlv3.repository;

import com.example.springboardlv3.entity.Comment;
import com.example.springboardlv3.entity.Post;
import com.example.springboardlv3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndUser(Long id, User user);
    void deleteByPostId(Long id); // 이거 왜 썼는데! 이거 뭔데! 댓글을 지우기 위해서(게시글을 지우려면 댓글을 삭제 해야함)
    List<Comment> findAllByOrderByModifiedAtDesc();


    Optional<Comment> findByIdAndUserId(Long commentid, Long userid);
}
