package edu.bupt.law.security.service;


import edu.bupt.law.security.domain.User;

public interface UserService {
    User registerUserByUsernameAndPassword(String username, String password);
}
