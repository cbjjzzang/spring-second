package com.sparta.myBlog.controller;

import com.sparta.myBlog.dto.CommentRequestDto;
import com.sparta.myBlog.models.Comment;
import com.sparta.myBlog.repository.CommentRepository;
import com.sparta.myBlog.security.UserDetailsImpl;
import com.sparta.myBlog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    //댓글 생성
    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(requestDto, userDetails);
    }

    //댓글 조회
    @GetMapping("/api/comments")
    public List<Comment> getComment(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return commentRepository.findAllByContentsIdOrderByCreatedAtDesc(id);
    }
    //댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }
    //댓글 수정
    @PutMapping("/api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }
}
