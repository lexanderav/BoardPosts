package com.example.boardposts.dto;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallUserDTO {
    private Long id;
    private String name;
    private String email;
    private String filename;
}