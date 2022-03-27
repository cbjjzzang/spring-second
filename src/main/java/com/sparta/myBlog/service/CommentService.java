package com.sparta.myBlog.service;

import com.sparta.myBlog.models.Comment;
import com.sparta.myBlog.dto.CommentRequestDto;
import com.sparta.myBlog.repository.CommentRepository;
import com.sparta.myBlog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //댓글 생성
    public Comment createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {
        Comment comment = new Comment(requestDto, userDetails.getUser().getUsername());
        return commentRepository.save(comment);
    }

    //댓글 수정
    @Transactional
    public Long update(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        return comment.update(requestDto);
    }

    //댓글 삭제
    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
