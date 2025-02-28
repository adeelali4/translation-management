package com.demo.translation_management.service.Impl;

import com.demo.translation_management.base.dto.request.UserRequestDTO;
import com.demo.translation_management.base.dto.response.UserResponseDTO;
import com.demo.translation_management.mapper.UserMapper;
import com.demo.translation_management.persistence.entity.User;
import com.demo.translation_management.persistence.repository.UserRepository;
import com.demo.translation_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO model) throws Exception {
        UserResponseDTO userDTO = new UserResponseDTO();
        User user = userMapper.add(model);
        userRepository.save(user);
        userDTO = userMapper.find(user);
        return userDTO;
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO model, Long Id) throws Exception {
        Optional<User> user = userRepository.findById(Id);
        if (user.isPresent()) {
            User userToUpdate = user.get();
            userMapper.update(model, userToUpdate);
            userRepository.save(userToUpdate);
            return userMapper.find(userToUpdate);
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users.stream()
                    .map(user -> {
                        try {
                            return userMapper.find(user);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
