package in.wimsyclimsy.plingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.wimsyclimsy.plingo.commons.GenerateUserResponse;
import in.wimsyclimsy.plingo.commons.UpdateUsernameRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController implements IUserController{
    @Autowired
    private UserService userService;
    
    @Override
    @PostMapping(value = "/create")
    public GenerateUserResponse generateUser() {
        return userService.createUser();
    }

    @Override
    @PostMapping(value = "/update/name")
    public CRUDStatus updateUserName(String userId, UpdateUsernameRequest request) {
        return userService.updateUserName(userId, userId);
    }

}
