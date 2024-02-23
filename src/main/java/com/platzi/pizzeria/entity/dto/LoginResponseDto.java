package com.platzi.pizzeria.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import com.platzi.pizzeria.entity.projection.InfoBasicUser;

import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDto {

    @JsonProperty(value = "user_id", required = true)
    private UUID userId;

    @JsonProperty(value = "code", required = true)
    private String code;

    @JsonProperty(value = "jwt", required = true)
    private String jwt;

    @JsonProperty(value = "type_token", required = true)
    private String typeToken;

    @JsonProperty(value = "user_data", required = true)
    private InfoBasicUser infoBasicUser;

    public LoginResponseDto(UUID userId, String code) {
        this.userId = userId;
        this.code = code;
    }

    public LoginResponseDto(String jwt, InfoBasicUser infoBasicUser) {
        this.jwt = jwt;
        this.typeToken = "Bearer";
        this.infoBasicUser = infoBasicUser;
    }

}


