package com.docuservice.service;

import com.docuservice.security.UserPrincipal;
import com.docuservice.security.model.User;

public interface UserService {

    User findUserByUserPrincipal(UserPrincipal userPrincipal);


}
