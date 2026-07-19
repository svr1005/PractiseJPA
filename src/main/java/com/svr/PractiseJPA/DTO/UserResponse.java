package com.svr.PractiseJPA.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserResponse {

    private int id;
    private String name;
    private int yearsOld;
}
