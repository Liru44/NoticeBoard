package com.noticeboard.Board.service;

import com.noticeboard.Board.component.CustomUserDetails;
import com.noticeboard.Board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        CustomUserDetails user = userMapper.getUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException("ID " + id + "not found");
        }

        System.out.println("\nID : " + id);

        return user;
    }
}
