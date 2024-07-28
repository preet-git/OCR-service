package com.vitraya.ocr_application_sevice.controller;

//import com.netflix.discovery.converters.Auto;
import com.vitraya.ocr_application_sevice.dto.LoginRequestDto;
import com.vitraya.ocr_application_sevice.dto.UserDto;
import com.vitraya.ocr_application_sevice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(method = RequestMethod.POST,path="/signup")
    public ResponseEntity<String> userSignUp(@RequestBody UserDto userRequest){
        authService.createNewUser(userRequest);
        return new ResponseEntity<>("User Created Successfully.", HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, path="/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginRequestDto loginRequestDto){
        String jwt = authService.loginWithCreds(loginRequestDto);
        return new ResponseEntity<>(jwt,HttpStatus.OK);
    }

}
