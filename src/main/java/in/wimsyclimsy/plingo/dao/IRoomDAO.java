package in.wimsyclimsy.plingo.dao;

import java.util.Optional;

import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public interface IRoomDAO {
   public CRUDStatus createRoom(String roomCode, Room room);
   public CRUDStatus updateRoom(String roomCode, Room room);
   public Optional<Room> getRoom(String roomCode);
   public CRUDStatus deleteRoom(String roomCode);
}
