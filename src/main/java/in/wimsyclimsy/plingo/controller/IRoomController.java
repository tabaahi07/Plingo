package in.wimsyclimsy.plingo.controller;

import in.wimsyclimsy.plingo.commons.CreateRoomResponse;
import in.wimsyclimsy.plingo.commons.JoinRoomRequest;

public interface IRoomController {
    public CreateRoomResponse createRoom(String userId);
    public void joinRoom(String userId , JoinRoomRequest request);
}
