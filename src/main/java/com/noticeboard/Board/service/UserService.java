package com.noticeboard.Board.service;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(UserDTO userDTO) {
        userRepository.signup(userDTO);
    }
}
