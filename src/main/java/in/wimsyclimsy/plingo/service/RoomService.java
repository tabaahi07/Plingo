package in.wimsyclimsy.plingo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.dao.Room;
import in.wimsyclimsy.plingo.dao.RoomDAO;
import in.wimsyclimsy.plingo.dao.UserDAO;
import in.wimsyclimsy.plingo.game.Game;
import lombok.Builder;

@Builder
@Service
public class RoomService {
    @Autowired
    private RoomDAO roomDAO;
    @Autowired
    private UserDAO userDAO;

    public String createRoom(String userId){
        String roomCode = UUID.randomUUID().toString();
        Room room = Room.builder()
                    .roomCode(roomCode)
                    .userList(new ArrayList<User>())
                    .game(Game.builder().build())
                    .build();
        roomDAO.createRoom(roomCode, room);
        return roomCode;
    }

    public CRUDStatus joinRoom(String userId , String roomCode){
        Optional<User> user = userDAO.getUser(userId);
        if(user == null) return CRUDStatus.REJECTED;
        Optional<Room> room = roomDAO.getRoom(roomCode);
        if(room == null) return CRUDStatus.REJECTED;
        
        List<User> userList = room.get().getUserList();
        userList.add(user.get());
        roomDAO.updateRoom(roomCode,
                Room.builder()
                .roomCode(roomCode)
                .game(room.get().getGame())
                .userList(userList)
                .build()
        );
        return CRUDStatus.APPROVED;
    }
}
