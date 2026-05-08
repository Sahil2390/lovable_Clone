package com.firstproject.lovable_clone.controller;


import com.firstproject.lovable_clone.dto.auth.AuthResponse;
import com.firstproject.lovable_clone.dto.auth.SigninRequest;
import com.firstproject.lovable_clone.dto.auth.SignupRequest;
import com.firstproject.lovable_clone.dto.auth.UserProfileResponse;
import com.firstproject.lovable_clone.service.AuthService;
import com.firstproject.lovable_clone.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody SigninRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile(){
        Long userId =1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }

}
