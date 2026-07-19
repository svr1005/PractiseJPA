package com.svr.PractiseJPA.Controller;

import com.svr.PractiseJPA.Entity.Post;
import com.svr.PractiseJPA.Entity.User;
import com.svr.PractiseJPA.GlobalException.ResourceNotFoundException;
import com.svr.PractiseJPA.Repository.PostRepository;
import com.svr.PractiseJPA.Repository.UserRepository;
import com.svr.PractiseJPA.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.allPosts();
    }

    @PostMapping("/add/{id}")
    public Post addPosts(@PathVariable int id,@RequestBody @Valid Post post) {
        return postService.addPostsByUserId(id,post);
    }

}
