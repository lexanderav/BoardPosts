package com.example.boardposts.service;

import com.example.boardposts.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();
    void addUserPost(PostDTO dto, String name);
    List<PostDTO> getByUsername(String name);
}
