package edu.bupt.law.user.service;


import edu.bupt.law.user.model.User;

public interface UserService {
    User registerUserByUsernameAndPassword(String username, String password);
}
