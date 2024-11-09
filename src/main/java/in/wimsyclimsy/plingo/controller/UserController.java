package in.wimsyclimsy.plingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import in.wimsyclimsy.plingo.commons.GenerateUserResponse;
import in.wimsyclimsy.plingo.commons.UpdateUsernameRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.service.UserService;

public class UserController implements IUserController{
    @Autowired
    private UserService userService;

    @Override
    public GenerateUserResponse generateUser() {
        return userService.createUser();
    }

    @Override
    public CRUDStatus updateUserName(String userId, UpdateUsernameRequest request) {
        return userService.updateUserName(userId, userId);
    }

}
