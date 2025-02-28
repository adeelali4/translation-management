package com.demo.translation_management.mapper;

import com.demo.translation_management.base.dto.request.UserRequestDTO;
import com.demo.translation_management.base.dto.response.UserResponseDTO;
import com.demo.translation_management.persistence.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User add(UserRequestDTO model) throws Exception {
        User user = new User();
        user.setName(model.getName());
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user.setStatus(model.getStatus());
        return user;
    }

    public UserResponseDTO find(User user) throws Exception {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setStatus(user.getStatus());
        return response;
    }

    public User update(UserRequestDTO model, User user) throws Exception {
        user.setName(model.getName());
        user.setUsername(model.getUsername());
        user.setPassword(model.getPassword());
        user.setStatus(model.getStatus());
        return user;
    }
}
