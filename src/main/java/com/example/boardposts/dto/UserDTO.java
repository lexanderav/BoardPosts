package com.example.boardposts.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String password;
    private String matchingPassword;
    private String email;
}
