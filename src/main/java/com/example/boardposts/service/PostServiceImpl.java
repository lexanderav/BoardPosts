package com.example.boardposts.service;

import com.example.boardposts.domains.Post;
import com.example.boardposts.domains.User;
import com.example.boardposts.dto.PostDTO;
import com.example.boardposts.mapper.PostMapper;
import com.example.boardposts.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper mapper = PostMapper.MAPPER;
    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<PostDTO> getAll() {
        return mapper.fromPostList(postRepository.findAll());
    }


    @Override
    public void addUserPost(PostDTO dto, String name) {
        User user = userService.findByName(name);
        if (user == null) {
            throw new RuntimeException("You are not authorize");
        }
        Post post = mapper.toPost(dto);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getByUsername(String name) {
        User user = userService.findByName(name);
        List<Post> posts = user.getPosts();
        List<PostDTO> postDTOS = posts == null ? new ArrayList<>() : new ArrayList<>(mapper.fromPostList(posts));
        return postDTOS;
    }
}
