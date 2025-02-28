package com.demo.translation_management.service;

import com.demo.translation_management.base.dto.request.UserRequestDTO;
import com.demo.translation_management.base.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO addUser(UserRequestDTO model) throws Exception;
    UserResponseDTO updateUser(UserRequestDTO model,Long Id) throws Exception;
    List<UserResponseDTO> getAllUsers() throws Exception;
}
