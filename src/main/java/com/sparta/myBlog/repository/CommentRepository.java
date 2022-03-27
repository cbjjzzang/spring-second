package com.sparta.myBlog.repository;

import com.sparta.myBlog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentsIdOrderByCreatedAtDesc(Long contentsId);
}
