package com.platzi.pizzeria.service;

import com.platzi.pizzeria.common.exception.service.AuthenticationFailedException;
import com.platzi.pizzeria.config.security.jwt.JwtTokenProvider;
import com.platzi.pizzeria.entity.UserEntity;
import com.platzi.pizzeria.entity.dto.AuthenticationMfa;
import com.platzi.pizzeria.entity.dto.LoginRequestDto;
import com.platzi.pizzeria.entity.dto.LoginResponseDto;
import com.platzi.pizzeria.repository.PermissionRepository;
import com.platzi.pizzeria.repository.UserRepository;
import com.platzi.pizzeria.utils.GenericLoginAttempts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        UserEntity user = userRepository.findByUserName(loginRequestDto.getUserName()).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));
        if (user.isLocked()) throw new AuthenticationFailedException("El usuario esta bloqueado");

        if (!(passwordEncoder.matches(loginRequestDto.getUserPassword(), user.getUserPassword()))){
            if (user.getLoginAttempts()>=2){
                updateLockedUser(user);
            }
            else {
                updateLoginAttemptsUser(user, GenericLoginAttempts.LOGIN_ATTEMPTS);
            }
        }

        String code = generateCode(user);

        user.updateLoginAttempts(0);
        user.addCodeVerification(code);
        userRepository.save(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getUserPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return new LoginResponseDto(user.getUserId(), code);
    }

    public LoginResponseDto authenticationMfa(AuthenticationMfa authenticationMfa){
        UserEntity user = userRepository.findById(authenticationMfa.getUserId()).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        if(user.isLocked()) throw new AuthenticationFailedException("El usuario esta bloqueado");

        if(user.getCreateCodeVerification() == null || !Objects.equals(user.getCodeVerification(), authenticationMfa.getCode())) updateLoginAttemptsUser(user, GenericLoginAttempts.LOGIN_ATTEMPTS_MFA);

        Duration duration = Duration.between(user.getCreateCodeVerification(), LocalDateTime.now());
        if (duration.toMinutes()>5) throw new AuthenticationFailedException("El token ha expirado");

        user.updateLoginAttemptsMfa(0);
        user.resetCodeVerification();
        userRepository.save(user);

        return new LoginResponseDto(jwtTokenProvider.createJwt(user.getUserName()), userRepository.getInfoOfAUser(user.getUserId()));
    }

    private void updateLockedUser(UserEntity user){
        user.updateLocked(true);
        userRepository.save(user);
        throw new AuthenticationFailedException("Usuario bloqueado por intentos fallidos");
    }

    private void updateLoginAttemptsUser(UserEntity user, GenericLoginAttempts attempts){
        if (GenericLoginAttempts.LOGIN_ATTEMPTS.equals(attempts)){
            user.updateLoginAttempts(user.getLoginAttempts() + 1);
        }else {
            if (user.getLoginAttemptsMfa()>=2){
                this.updateLockedUser(user);
            }
            user.updateLoginAttemptsMfa(user.getLoginAttemptsMfa()+1);
        }
        userRepository.save(user);
        String message = (attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS))?".":" el código es inválido";
        throw new AuthenticationFailedException("Quedan "+(attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS)?3-user.getLoginAttempts():3-user.getLoginAttemptsMfa())+" intentos"+message);
    }

    private String generateCode(UserEntity user){
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
}
