package com.noticeboard.Board.mapper;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.component.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void signup(UserDTO userDTO);

    CustomUserDetails getUserById(String id);

    List<UserDTO> getUserList();

    UserDTO getUserInfo(String id);

    void editUser(UserDTO userDTO);

    void changePassword(String id, String password);
}
