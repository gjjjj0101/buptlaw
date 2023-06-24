package edu.bupt.law.user.dto;

import edu.bupt.law.user.model.User;

public class UserMapper {

    public String mapToUserNameDto(User user) {
        return user.getUsername();
    }

}
