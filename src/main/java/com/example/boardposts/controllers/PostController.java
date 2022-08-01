package com.example.boardposts.controllers;

import com.example.boardposts.dto.PostDTO;
import com.example.boardposts.service.PostService;
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
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new PostDTO());
        return "post";
    }

    @PostMapping("/new")
    public String savePost(PostDTO dto,
                           Principal principal,
                           @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!file.isEmpty()) {

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            dto.setFilename(resultFileName);
        }
       postService.addUserPost(dto, principal.getName());
       return "redirect:/posts";
    }

    @GetMapping
    public String postList(Model model,
                           Principal principal
    ) {
        List<PostDTO> postDTOS = postService.getByUsername(principal.getName());
        Collections.reverse(postDTOS);
        model.addAttribute("posts", postDTOS);
        return "postList";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                        Model model){
        model.addAttribute("post", postService.show(id));
        return "edit";
    }

    @PostMapping("/update")
    public String updatePost(PostDTO postDTO)
    {
        postService.update(postDTO);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/all")
    public String allPosts(Model model) {
        List<PostDTO> postDTO = postService.allPostsUsers();
        Collections.reverse(postDTO);
        model.addAttribute("posts", postDTO);
        return "allPostsUsers";
    }
}
