package edu.bupt.law.user.controller;


import edu.bupt.law.common.utils.UUidUtils;
import edu.bupt.law.security.exception.UserInternalException;
import edu.bupt.law.security.utils.JWTUtils;
import edu.bupt.law.user.controller.request.LoginRequest;
import edu.bupt.law.user.controller.request.RegisterRequest;
import edu.bupt.law.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        System.out.println(username);
        System.out.println(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword())
        );
        String token = JWTUtils.createTokenByAuthentication(authentication);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return ResponseEntity.ok(model);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerByUsernameAndPassword(@RequestBody RegisterRequest registerRequest){
        return Optional.ofNullable(userService.registerUserByUsernameAndPassword(registerRequest.getUsername(), registerRequest.getPassword()))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserInternalException("用户" + registerRequest.getUsername() +"注册失败"));
    }

    @GetMapping("/hello")
    public String hello(){
        return UUidUtils.generateUuid();
    }
}
