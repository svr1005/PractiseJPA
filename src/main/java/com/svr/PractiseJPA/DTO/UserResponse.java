package com.svr.PractiseJPA.DTO;

import com.svr.PractiseJPA.Entity.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class UserResponse {

    private int id;
    private String name;
    private int yearsOld;
    private List<Post> posts;
}
