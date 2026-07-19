package com.svr.PractiseJPA.Mapper;

import com.svr.PractiseJPA.DTO.UserRequest;
import com.svr.PractiseJPA.DTO.UserResponse;
import com.svr.PractiseJPA.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper
public interface UserMapper {

    @Mapping(source="age", target="yearsOld")
    UserResponse UserToUserResponse(User user);

    @Mapping(source="yearsOld", target="age")
    User UserRequestToUser(UserRequest userRequest);
}
