package in.wimsyclimsy.plingo.controller;
import in.wimsyclimsy.plingo.commons.GenerateUserResponse;
import in.wimsyclimsy.plingo.commons.UpdateUsernameRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public interface IUserController {
    public GenerateUserResponse generateUser();
    public CRUDStatus updateUserName(String userId, UpdateUsernameRequest request);
}
