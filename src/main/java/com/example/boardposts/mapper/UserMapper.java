package com.example.boardposts.mapper;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.UserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO fromUser(User user);

    List<User> toUserList(List<UserDTO> userDTOS);

    List<UserDTO> fromUserDTOList(List<User> user);
}
