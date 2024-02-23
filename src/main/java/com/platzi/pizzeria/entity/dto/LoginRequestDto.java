package com.platzi.pizzeria.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @Size(max = 20)
    @NotBlank
    @JsonProperty(value = "user_name")
    private String userName;

    @Size(max = 30)
    @NotBlank
    @JsonProperty(value = "user_password")
    private String userPassword;

}
