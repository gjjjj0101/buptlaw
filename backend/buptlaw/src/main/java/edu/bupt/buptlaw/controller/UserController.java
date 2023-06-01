package edu.bupt.buptlaw.controller;


import edu.bupt.buptlaw.controller.request.LoginRequest;
import edu.bupt.buptlaw.controller.request.RegisterRequest;
import edu.bupt.buptlaw.exception.user.UserInternalException;
import edu.bupt.buptlaw.model.User;
import edu.bupt.buptlaw.service.UserService;
import edu.bupt.buptlaw.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
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
}
