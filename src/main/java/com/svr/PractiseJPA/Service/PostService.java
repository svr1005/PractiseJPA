package com.svr.PractiseJPA.Service;

import com.svr.PractiseJPA.Entity.Post;
import com.svr.PractiseJPA.Entity.User;
import com.svr.PractiseJPA.GlobalException.ResourceNotFoundException;
import com.svr.PractiseJPA.Repository.PostRepository;
import com.svr.PractiseJPA.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> allPosts() {
        return postRepository.findAll();
    }

    public Post addPostsByUserId(int id, @Valid Post post) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id of the user is invalid"));
        post.setUser(user);
        return postRepository.save(post);
    }
}
