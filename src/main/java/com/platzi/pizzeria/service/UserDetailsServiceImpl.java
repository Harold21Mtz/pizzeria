package com.platzi.pizzeria.service;

import com.platzi.pizzeria.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class UserDetailsServiceImpl implements UserDetails {

    private UUID userId;

    private String userName;

    private String password;

    private String imgProfile;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsServiceImpl(UUID userId, String userName, String password, String imgProfile, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.imgProfile = imgProfile;
        this.authorities = authorities;
    }

    public static UserDetailsServiceImpl build(UserEntity user, List<String> permissionResponses){
        List<GrantedAuthority> grantedAuthorities = permissionResponses.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRoles().get(0).getRoleName()));
        return new UserDetailsServiceImpl(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getProfileImage(),
                grantedAuthorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
