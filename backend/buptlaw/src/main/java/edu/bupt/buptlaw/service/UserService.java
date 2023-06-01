package edu.bupt.buptlaw.service;

import edu.bupt.buptlaw.model.User;

public interface UserService {
    User registerUserByUsernameAndPassword(String username, String password);
}
