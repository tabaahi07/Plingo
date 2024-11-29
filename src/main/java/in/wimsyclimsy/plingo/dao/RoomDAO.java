package in.wimsyclimsy.plingo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public class RoomDAO implements IRoomDAO{
    // Room code - Room info
    private Map<String, Room> rooms = new HashMap<>();
    
    @Override
    public CRUDStatus createRoom(String roomCode, Room room) {
        if(rooms.containsKey(roomCode)) return CRUDStatus.REJECTED;
        rooms.put(roomCode, room);
        return CRUDStatus.APPROVED;
    }

    @Override
    public CRUDStatus updateRoom(String roomCode, Room room) {
        rooms.put(roomCode, room);
        return CRUDStatus.APPROVED;
    }

    @Override
    public Optional<Room> getRoom(String roomCode) {
        if(!rooms.containsKey(roomCode)) return Optional.empty();
        return Optional.of(rooms.get(roomCode));
    }

    @Override
    public CRUDStatus deleteRoom(String roomCode) {
        if(!rooms.containsKey(roomCode)) return CRUDStatus.REJECTED;
        rooms.remove(roomCode);
        return CRUDStatus.APPROVED;
    }

}
