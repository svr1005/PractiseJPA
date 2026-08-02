package com.svr.PractiseJPA.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id", nullable = false)
    private int id;

    @NotNull
    String title;

    String description;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    /**
     * Many posts belong to one user.
     * The 'user_id' column will be the foreign key in the 'posts' table.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
