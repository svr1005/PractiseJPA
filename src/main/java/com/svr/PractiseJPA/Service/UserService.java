package com.svr.PractiseJPA.Service;

import com.svr.PractiseJPA.DTO.UserRequest;
import com.svr.PractiseJPA.DTO.UserResponse;
import com.svr.PractiseJPA.Entity.Post;
import com.svr.PractiseJPA.GlobalException.ResourceNotFoundException;
import com.svr.PractiseJPA.Mapper.UserMapper;
import com.svr.PractiseJPA.Entity.User;
import com.svr.PractiseJPA.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<User> list = userRepository.findAll();
        System.out.println(list.get(0).getPosts());
        List<UserResponse> userResponse = list.stream().map(userMapper::UserToUserResponse).toList();

        return userResponse;
    }

    public UserResponse getSingleUser(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
        return userMapper.UserToUserResponse(user);
    }

    public UserResponse addUser(UserRequest user) {
        return userMapper.UserToUserResponse(userRepository.save(userMapper.UserRequestToUser(user)));
    }

    public UserResponse replaceUserByID(int id, UserRequest user) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId with " + id + " is invalid"));
        userRepository.delete(userFound);
        return userMapper.UserToUserResponse(userRepository.save(userMapper.UserRequestToUser(user)));
    }

    public UserResponse updateUserByID(int id, UserRequest user) {
        User updatedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId with " + id + " is invalid"));
        if (user.getYearsOld() > 0) {
            updatedUser.setAge(user.getYearsOld());
        }
        if (user.getName() != null) {
            updatedUser.setName(user.getName());
        }
        return userMapper.UserToUserResponse(updatedUser);
    }

    public UserResponse deleteUserByUserId(int id) {
        User userToBeDelted = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId with " + id + " is invalid"));
        userRepository.deleteById(id);
        return userMapper.UserToUserResponse(userToBeDelted);
    }
}
