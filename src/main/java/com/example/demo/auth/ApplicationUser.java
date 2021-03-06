package com.example.demo.auth;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ApplicationUser implements UserDetails {

    // UserDetails is an interface

    private static final long serialVersionUID = 1L;

    private final Set<? extends GrantedAuthority> authorities;

    private final String password;

    private final String username;

    private final boolean isAccountNonExpired;

    private final boolean isAccountNonLocked;

    private final boolean isCredentialsNonExpired;

    private final boolean isEnabled;

    ApplicationUser(String username, String password, Set<? extends GrantedAuthority> authorities,
            boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
            boolean isEnabled) {

        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
