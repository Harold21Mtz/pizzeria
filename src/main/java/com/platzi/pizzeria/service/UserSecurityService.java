package com.platzi.pizzeria.service;

import com.platzi.pizzeria.entity.UserEntity;
import com.platzi.pizzeria.repository.PermissionRepository;
import lombok.Getter;
import com.platzi.pizzeria.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;

    public UserSecurityService(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario buscado"));
       List<String> permissionResponses = permissionRepository.getAllPermissionByUserName(username);
        return UserDetailsServiceImpl.build(user, permissionResponses);
    }

}
