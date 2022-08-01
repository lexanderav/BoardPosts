package com.example.boardposts.dto;

import com.example.boardposts.domains.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String context;
    private String filename;
    private User user;
}
