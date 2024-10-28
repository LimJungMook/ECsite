package com.mookShopping.mook.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageForm {

    private Long id;

    @NotEmpty(message = "회원이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "메일은 필수입니다.")
    @Email(message = "메일형식으로 적어주세요.")
    private String mail;

    @NotEmpty(message = "비밀번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "비밀번호를 확인해주세요")
    private String confirmPassword;

    private String address;
}
