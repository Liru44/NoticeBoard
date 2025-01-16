package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입 기능
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDTO userDTO) {
        userService.signup(userDTO);
        return "/login";
    }

    //아이디 중복 체크
    @GetMapping("isDuplicatedId")
    @ResponseBody
    public boolean isDuplicatedId(@RequestParam("id") String id, Model model) {
        boolean isDuplicated = userService.isDuplicatedId(id);
        return isDuplicated;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
