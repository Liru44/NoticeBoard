package com.noticeboard.Board.mapper;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.component.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void signup(UserDTO userDTO);

    CustomUserDetails getUserById(String id);
}
