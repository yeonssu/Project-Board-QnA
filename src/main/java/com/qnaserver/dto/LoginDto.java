package com.qnaserver.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDto {
    @NotBlank
    private String memberEmail;
}
