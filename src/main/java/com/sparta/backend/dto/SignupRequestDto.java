package com.sparta.backend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String pw;

    @NotBlank(message = "비밀번호 중복검사는 필수 입니다.")
    private String pwcheck;

    @NotBlank (message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

}
