package com.docuservice.service.impl;

import com.docuservice.api.controller.exception.EntityObjectNotFoundException;
import com.docuservice.persistance.repository.UserRepository;
import com.docuservice.security.UserPrincipal;
import com.docuservice.security.model.User;
import com.docuservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByUserPrincipal(UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new EntityObjectNotFoundException("Cannot found user with id " + userPrincipal.getId()));
    }
}
