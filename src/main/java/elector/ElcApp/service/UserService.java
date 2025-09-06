package elector.ElcApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import elector.ElcApp.dto.request.UserLoginDto;
import elector.ElcApp.dto.request.UserRegistrationDto;
import elector.ElcApp.dto.response.UserResponseDto;
import elector.ElcApp.model.User;
import elector.ElcApp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto registerUser(UserRegistrationDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword()); // Cần mã hóa mật khẩu trong thực tế
        user.setFullName(userDto.getFullName());
        user.setIsAdmin(false);

        User savedUser = userRepository.save(user);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setFullName(savedUser.getFullName());
        responseDto.setIsAdmin(savedUser.getIsAdmin());

        return responseDto;
    }

    public UserResponseDto validateLogin(UserLoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setId(user.getId());
            responseDto.setUsername(user.getUsername());
            responseDto.setFullName(user.getFullName());
            responseDto.setIsAdmin(user.getIsAdmin());
            return responseDto;
        }
        return null;
    }
}