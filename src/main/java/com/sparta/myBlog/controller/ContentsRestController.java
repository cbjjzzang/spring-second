package com.sparta.myBlog.controller;

import com.sparta.myBlog.models.Contents;
import com.sparta.myBlog.repository.ContentsRepository;
import com.sparta.myBlog.dto.ContentsRequestDto;
import com.sparta.myBlog.security.UserDetailsImpl;
import com.sparta.myBlog.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final ContentsRepository contentsRepository;
    private final ContentsService contentsService;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Contents> getContents() {
        return contentsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/contents/detail")
    public ModelAndView getContents(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return contentsService.getContentAndComments(id, userDetails);
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername());
        Contents contents = new Contents(requestDto);
        return contentsRepository.save(contents);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        contentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id) {
        contentsRepository.deleteById(id);
        return id;
    }

}