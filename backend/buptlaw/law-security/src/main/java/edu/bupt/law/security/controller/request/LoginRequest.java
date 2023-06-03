package edu.bupt.law.security.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
