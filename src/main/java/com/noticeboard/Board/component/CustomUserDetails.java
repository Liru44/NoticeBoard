package com.noticeboard.Board.component;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Setter
public class CustomUserDetails implements UserDetails {
    private String id;
    private String password;
    private String name;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Assigned role : " + role);
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        ArrayList<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roleList.add(new SimpleGrantedAuthority(role));
        return roleList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
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
