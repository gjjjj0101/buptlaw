package edu.bupt.law.user.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
