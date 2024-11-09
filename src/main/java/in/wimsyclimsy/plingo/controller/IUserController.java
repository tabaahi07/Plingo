package in.wimsyclimsy.plingo.controller;
import in.wimsyclimsy.plingo.commons.GenerateUserResponse;
import in.wimsyclimsy.plingo.commons.UpdateUsernameRequest;

public interface IUserController {
    public GenerateUserResponse generateUser();
    public void updateUserName(String userId, UpdateUsernameRequest request);
}
