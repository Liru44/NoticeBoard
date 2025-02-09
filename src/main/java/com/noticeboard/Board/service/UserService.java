package com.noticeboard.Board.service;

import com.noticeboard.Board.component.CustomUserDetails;
import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void signup(UserDTO userDTO) {
        String password = userDTO.getPassword();
        password = passwordEncoder.encode(password);
        userDTO.setPassword(password);
        log.info("SignUp User Password : " + password);
        userMapper.signup(userDTO);
    }

    public boolean isDuplicatedId(String id) {
        boolean isDuplicated = false;
        CustomUserDetails customUserDetails = userMapper.getUserById(id);
        if (customUserDetails != null) {
            isDuplicated = true;
        }
        return isDuplicated;
    }

    public List<UserDTO> getUserList() {
        return userMapper.getUserList();
    }

    public UserDTO getUserInfo(String id) {
        return userMapper.getUserInfo(id);
    }

    public void editUser(UserDTO userDTO) {
        userMapper.editUser(userDTO);
    }

    public void changePassword(String id, String password) {
        password = passwordEncoder.encode(password);
        log.info("SignUp User Password : " + password);
        userMapper.changePassword(id, password);
    }
}
