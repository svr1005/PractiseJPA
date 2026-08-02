package com.svr.PractiseJPA.Service;

import com.svr.PractiseJPA.DTO.UserRequest;
import com.svr.PractiseJPA.DTO.UserResponse;
import com.svr.PractiseJPA.Entity.Role;
import com.svr.PractiseJPA.GlobalException.ResourceNotFoundException;
import com.svr.PractiseJPA.Mapper.UserMapper;
import com.svr.PractiseJPA.Entity.User;
import com.svr.PractiseJPA.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptPasswordEncoder;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public UserService(UserRepository userRepository, PasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        List<User> list = userRepository.findAll();
        List<UserResponse> userResponse = list.stream().map(userMapper::UserToUserResponse).toList();

        return userResponse;
    }

    public UserResponse getSingleUser(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
        return userMapper.UserToUserResponse(user);
    }

    public UserResponse addUser(UserRequest user) {
        User createdUser = userMapper.UserRequestToUser(user);
        createdUser.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRole()==null)
        {
         createdUser.setRole(Role.USER);
        }
        return userMapper.UserToUserResponse(userRepository.save(createdUser));
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
