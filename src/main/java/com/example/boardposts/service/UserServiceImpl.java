package com.example.boardposts.service;

import com.example.boardposts.domains.Role;
import com.example.boardposts.domains.User;
import com.example.boardposts.dto.UserDTO;
import com.example.boardposts.mapper.UserMapper;
import com.example.boardposts.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper = UserMapper.MAPPER;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    public UserDTO findByNameForDTO(String name) {
        return mapper.fromUser(findByName(name));
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password not equals");
        }
        User user = User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .roles(Role.USER)
                .filename(userDTO.getFilename())
                .build();
        userRepository.save(user);
        return true;
    }

    public List<UserDTO> getAll() {
        return mapper.fromUserDTOList(userRepository.findAll().stream().collect(Collectors.toList()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRoles().name()));
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles);
    }

    public UserDTO show(Long id) {
        User user = userRepository.findById(id).get();
        return mapper.fromUser(user);
    }

    public void update(UserDTO dto) {
        boolean checked = false;
        User user = userRepository.findFirstByName(dto.getName());
        if (!user.getName().equals(dto.getName())) {
            user.setName(dto.getName());
            checked = true;
        } else if (!user.getEmail().equals(dto.getEmail())) {
            user.setEmail(dto.getEmail());
            checked = true;
        } else if (!user.getFilename().equals(dto.getFilename())) {
            user.setFilename(dto.getFilename());
            checked = true;
        }
        if (checked) {
            userRepository.save(user);
        }
    }

}
