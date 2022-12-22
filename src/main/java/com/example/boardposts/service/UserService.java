package com.example.boardposts.service;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.SmallUserDTO;
import com.example.boardposts.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService { //security
    User findByName(String name);
    User findUserById(Long id);
    UserDTO findByNameForDTO(String name);
    List<UserDTO> getAll();
    boolean save(UserDTO userDTO);
    SmallUserDTO show(Long id);
    SmallUserDTO findSmallUserDTOByIdUser(Long id);
    void update(Long id,SmallUserDTO dto);
}