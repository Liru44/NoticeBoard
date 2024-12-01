package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    //회원가입
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDTO userDTO) {
        userService.signup(userDTO);
        return "redirect:/signup";
    }
}
