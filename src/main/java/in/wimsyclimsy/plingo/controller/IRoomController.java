package in.wimsyclimsy.plingo.controller;

import in.wimsyclimsy.plingo.commons.CreateRoomResponse;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public interface IRoomController {
    public CreateRoomResponse createRoom(String userId);
    public CRUDStatus joinRoom(String userId , String roomCode);
}
