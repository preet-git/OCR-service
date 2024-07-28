package com.vitraya.ocr_application_sevice.serviceImpl;

import com.vitraya.ocr_application_sevice.dto.LoginRequestDto;
import com.vitraya.ocr_application_sevice.dto.UserDto;
import com.vitraya.ocr_application_sevice.model.UserEntity;
import com.vitraya.ocr_application_sevice.repository.UserRepository;
import com.vitraya.ocr_application_sevice.service.AuthService;
import com.vitraya.ocr_application_sevice.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public void createNewUser(UserDto userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setEmail(userRequest.getEmail());

        userRepository.save(userEntity);
    }

    @Override
    public String loginWithCreds(LoginRequestDto loginRequestDto){

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtUtil.generateToken(authenticate);
    }

    @Override
    public Optional<User> getCurrentUser() {
        User principal =
                (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return Optional.of(principal);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
