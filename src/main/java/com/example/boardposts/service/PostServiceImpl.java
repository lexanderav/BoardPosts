package com.example.boardposts.service;

import com.example.boardposts.domains.Post;
import com.example.boardposts.domains.User;
import com.example.boardposts.dto.PostDTO;
import com.example.boardposts.mapper.PostMapper;
import com.example.boardposts.repository.PostRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostMapper mapper = PostMapper.MAPPER;
    private final PostRepository postRepository;
    private final UserService userService;

    public PostServiceImpl(PostRepository postRepository, @Lazy UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<PostDTO> getAll() {
        return mapper.fromPostList(postRepository.findAll());
    }

    @Override
    public PostDTO show(Long idPost) {
        return mapper.fromPost(postRepository.findAll().stream().filter(post -> post.getId() == idPost).findFirst().orElse(null));
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


    @Override
    public List<PostDTO> getListPostsByUserId(Long id) {
        User user = userService.findUserById(id);

        return null;
    }

    @Override
    public void update(PostDTO postDTO) {
        Post post = postRepository.findById(postDTO.getId()).get();
        boolean checked = false;
        if (!post.getTitle().equals(postDTO.getTitle())) {
            post.setTitle(postDTO.getTitle());
            checked = true;
        } else if (!post.getContext().equals(postDTO.getContext())) {
            post.setContext(postDTO.getContext());
            checked = true;
        }
        if (checked) {
            postRepository.save(post);
        }
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public User getUserByPostDTOId(Long id) {
        Post post = postRepository.findAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        return post.getUser();
    }

    @Override
    public List<PostDTO> allPostsUsers() {
        return mapper.fromPostList(postRepository.findAll());
    }
}
