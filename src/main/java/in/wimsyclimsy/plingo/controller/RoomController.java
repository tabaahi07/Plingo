package in.wimsyclimsy.plingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.wimsyclimsy.plingo.commons.CreateRoomResponse;
import in.wimsyclimsy.plingo.commons.JoinRoomRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController implements IRoomController{
    @Autowired
    private RoomService roomService;

    @Override
    @PostMapping(value = "/create")
    public CreateRoomResponse createRoom(String userId) {
        return CreateRoomResponse.builder().roomCode(roomService.createRoom(userId)).build();
    }

    @Override
    @GetMapping(value = "/join") 
    public CRUDStatus joinRoom(@RequestHeader("X-User-Id") String userId,@RequestParam("room_code") String roomCode) {
        return roomService.joinRoom(userId, roomCode);
    }
    
}
