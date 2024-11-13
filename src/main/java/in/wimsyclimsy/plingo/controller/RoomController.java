package in.wimsyclimsy.plingo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import in.wimsyclimsy.plingo.commons.CreateRoomResponse;
import in.wimsyclimsy.plingo.commons.JoinRoomRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.service.RoomService;

public class RoomController implements IRoomController{
    @Autowired
    private RoomService roomService;

    @Override
    public CreateRoomResponse createRoom(String userId) {
        return CreateRoomResponse.builder().roomCode(roomService.createRoom(userId)).build();
    }

    @Override
    public CRUDStatus joinRoom(String userId, JoinRoomRequest request) {
        return roomService.joinRoom(userId, request.getRoomCode());
    }
    
}
