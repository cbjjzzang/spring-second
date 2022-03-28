package com.sparta.myBlog.service;

import com.sparta.myBlog.models.Comment;
import com.sparta.myBlog.models.Contents;
import com.sparta.myBlog.repository.CommentRepository;
import com.sparta.myBlog.repository.ContentsRepository;
import com.sparta.myBlog.dto.ContentsRequestDto;
import com.sparta.myBlog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto) {
        Contents Contents = contentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }

    public ModelAndView getContentAndComments(Long id, UserDetailsImpl userDetails) {
        Contents contents = contentsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다")
        );
        List<Comment> comment = commentRepository.findAllByContentsIdOrderByCreatedAtDesc(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("detail");
        mv.addObject("data", contents);
        mv.addObject("comments", comment);
        if(userDetails != null){
            mv.addObject("user", userDetails.getUser().getUsername());
        }else{
            mv.addObject("user",null );
        }
        return mv;
    }
}