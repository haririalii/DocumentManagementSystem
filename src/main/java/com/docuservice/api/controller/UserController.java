package com.docuservice.api.controller;

import com.docuservice.api.controller.dto.UserResponseDto;
import com.docuservice.api.controller.exception.ResourceNotFoundException;
import com.docuservice.api.controller.mapper.DocumentMapper;
import com.docuservice.persistance.repository.UserRepository;
import com.docuservice.security.model.User;
import com.docuservice.security.payload.UserIdentityAvailability;
import com.docuservice.security.payload.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DocumentMapper documentMapper;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public List<UserResponseDto> getUsers() {
        return this.userRepository.findAll().stream().map(documentMapper::userResponseDto).collect(Collectors.toList());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), user.getRoles());

        return userProfile;
    }


}
