package com.example.boardposts.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class Post {
    private static final String SEQ_NAME = "posts_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String title;
    @Lob
    @Type(type = "text")
    private String context;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
