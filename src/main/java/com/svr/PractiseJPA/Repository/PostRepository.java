package com.svr.PractiseJPA.Repository;

import com.svr.PractiseJPA.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
