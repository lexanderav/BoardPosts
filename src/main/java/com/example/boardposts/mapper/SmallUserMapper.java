package com.example.boardposts.mapper;

import com.example.boardposts.domains.User;
import com.example.boardposts.dto.SmallUserDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SmallUserMapper {

    SmallUserMapper MAPPER = Mappers.getMapper(SmallUserMapper.class);

    User toUser(SmallUserDTO smallUserDTO);

    @InheritInverseConfiguration

    SmallUserDTO fromUser(User user);

    List<User> toUserList(List<SmallUserDTO> smallUserDTOS);

    List<SmallUserDTO> fromSmallUserDTO(List<User> user);

}
