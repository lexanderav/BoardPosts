package com.example.boardposts.service;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();
    void addUserPost(PostDTO dto, String name);
    List<PostDTO> getByUsername(String name);
//    void addUserPost(PostDTO dto, Long id);
    List<PostDTO> getListPostsByUserId(Long id);
    PostDTO show(Long idPost);
    void update(PostDTO postDTO);
    void delete(Long id);
    User getUserByPostDTOId(Long id);
    List<PostDTO> allPostsUsers();
}
