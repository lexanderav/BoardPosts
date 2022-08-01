package com.example.boardposts.domains;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    private static final String SEQ_NAME = "users_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;
}
