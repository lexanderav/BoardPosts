package com.example.boardposts.repository;

import com.example.boardposts.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
