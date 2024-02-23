package com.platzi.pizzeria.controller;

import com.platzi.pizzeria.entity.dto.AuthenticationMfa;
import com.platzi.pizzeria.entity.dto.LoginRequestDto;
import com.platzi.pizzeria.entity.dto.LoginResponseDto;
import com.platzi.pizzeria.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @Operation(description = "Login for user")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/mfa")
    @Operation(description = "Verification code by User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    public ResponseEntity<LoginResponseDto> mfa(@Valid @RequestBody AuthenticationMfa request) {
        return new ResponseEntity<>(authenticationService.authenticationMfa(request), HttpStatus.OK);
    }

}
