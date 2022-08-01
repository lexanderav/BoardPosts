package com.example.boardposts.mapper;
import com.example.boardposts.domains.Post;
import com.example.boardposts.dto.PostDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {

    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    Post toPost(PostDTO dto);

    @InheritInverseConfiguration
    PostDTO fromPost(Post post);

    List<Post> toPostList(List<PostDTO> PostDTOS);

    List<PostDTO> fromPostList(List<Post> posts);

}