package com.vitraya.ocr_application_sevice.service;

import com.vitraya.ocr_application_sevice.dto.LoginRequestDto;
import com.vitraya.ocr_application_sevice.dto.UserDto;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface AuthService {
    public void createNewUser(UserDto userRequest);

    String loginWithCreds(LoginRequestDto loginRequestDto);

    Optional<User> getCurrentUser();
}
