package com.example.boardposts.controllers;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.PostDTO;
import com.example.boardposts.service.PostService;
import com.example.boardposts.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new PostDTO());
        return "post";
    }

    @PostMapping("/new")
    public String savePost(PostDTO dto,
                          Principal principal) {
       postService.addUserPost(dto, principal.getName());
       return "redirect:/posts";
    }

    @GetMapping
    public String postList(Model model, Principal principal) {
        List<PostDTO> postDTOS = postService.getByUsername(principal.getName());
        model.addAttribute("posts", postDTOS);
        return "postList";
    }
}
