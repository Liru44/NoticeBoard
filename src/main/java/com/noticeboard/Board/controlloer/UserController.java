package com.noticeboard.Board.controlloer;

import com.noticeboard.Board.dto.UserDTO;
import com.noticeboard.Board.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public boolean isDuplicatedId(@RequestParam("id") String id) {
        boolean isDuplicated = userService.isDuplicatedId(id);
        return isDuplicated;
    }

    //로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //관리자 페이지
    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        List<UserDTO> userList = this.getUserList();
        if (!userList.isEmpty()) {
            model.addAttribute("userList", userList);
        }
        return "adminPage";
    }

    //유저 목록 조회
    @GetMapping("/userList")
    public List<UserDTO> getUserList() {
        List<UserDTO> userList = userService.getUserList();
        return userList;
    }

    //유저 상제 조회
    @GetMapping("/user/{id}")
    public String getUserInfo(@PathVariable("id") String id, Model model) {
        UserDTO userDTO = userService.getUserInfo(id);
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }
        return "userInfo";
    }

    //유저 정보 수정
    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable("id") String id, Model model) {
        UserDTO userDTO = userService.getUserInfo(id);
        if (userDTO != null) {
            model.addAttribute("user", userDTO);
        }
        return "editUser";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(UserDTO userDTO, Model model) {
        userService.editUser(userDTO);

        userDTO = userService.getUserInfo(userDTO.getId());
        model.addAttribute("user", userDTO);
        return "redirect:/userinfo";
    }

    //비밀번호 변경
    @GetMapping("/changePassword/{userID}")
    public String changePassword(@PathVariable("userID") String id, Model model) {
        model.addAttribute("userID", id);
        return "changePassword";
    }

    @PostMapping("/changePassword/{userID}")
    @ResponseBody
    public String changePassword(@PathVariable("userID") String id, @RequestParam String password) {
        userService.changePassword(id, password);

        return "<script>alert('비밀번호가 변경되었습니다. '); window.close();</script>";
    }
}
