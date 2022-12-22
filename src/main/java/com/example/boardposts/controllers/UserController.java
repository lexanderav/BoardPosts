package com.example.boardposts.controllers;

import com.example.boardposts.dto.PostDTO;
import com.example.boardposts.dto.SmallUserDTO;
import com.example.boardposts.dto.UserDTO;
import com.example.boardposts.service.PostService;
import com.example.boardposts.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @Value("${user.path}")
    private String uploadPathUser;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }
    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user";
    }
    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") UserDTO dto,
                           Model model){
        if (userService.save(dto)) {
            return "redirect:/login";
        } else {
            model.addAttribute("user", dto);
        }
        return "user";
    }
    @GetMapping("/get")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                        Model model) {
        SmallUserDTO dto = userService.show(id);
        model.addAttribute("user", dto);
        return "editUser";
    }


    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         SmallUserDTO dto,
                         @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            File uploadDir = new File(uploadPathUser);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPathUser + "/" + resultFileName));
            dto.setFilename(resultFileName);
        }
        userService.update(id, dto);
        return "redirect:/users/show";
    }

    @GetMapping("/show")
    public String show(Principal principal,
                       Model model) {
        UserDTO userDTO = userService.findByNameForDTO(principal.getName());
        List<PostDTO> postDTOS = postService.getByUsername(principal.getName());
        Collections.reverse(postDTOS);
        model.addAttribute("posts", postDTOS);
        model.addAttribute("user", userDTO);
        return "pageUser";
    }

}
