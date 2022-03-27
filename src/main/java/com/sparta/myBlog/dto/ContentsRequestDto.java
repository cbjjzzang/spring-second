package com.sparta.myBlog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContentsRequestDto {
    private String title;
    private String name;
    private String contents;
}