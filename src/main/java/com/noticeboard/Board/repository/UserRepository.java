package com.noticeboard.Board.repository;

import com.noticeboard.Board.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final SqlSessionTemplate sqlSessionTemplate;

    public void signup(UserDTO userDTO) {
        sqlSessionTemplate.insert("User.signup", userDTO);
    }
}
