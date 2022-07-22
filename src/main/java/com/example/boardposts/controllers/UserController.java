package com.example.boardposts.controllers;

import com.example.boardposts.dto.UserDTO;
import com.example.boardposts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user";
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") UserDTO dto,
                           Model model) {
        if (userService.save(dto)) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", dto);
        }
        return "user";
    }

}
