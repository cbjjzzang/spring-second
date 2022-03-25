package com.sparta.myBlog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])|(?=.*[a-zA-Z]).{3,}", message = "닉네임은 3자 이상의 영문 대,소문자 혹은 숫자만 가능")
    private String username;

    @Size(min = 4, message = "비밀번호는 4자 이상입니다.")
    private String password;

    private String password2;

    private String email;
}