package com.chronos.auth_service.service;

import com.chronos.auth_service.entity.User;
import com.chronos.auth_service.dto.JwtRequest;
import com.chronos.auth_service.dto.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    JwtResponse login(JwtRequest loginFormDTO);

    User register(User user);

}
