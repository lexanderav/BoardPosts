package com.example.boardposts.service;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //security
    boolean save(UserDTO userDTO);
    List<UserDTO> getAll();
    User findByName(String name);

    UserDTO show(Long id);

    void update(UserDTO dto);

    UserDTO findByNameForDTO(String name);
}