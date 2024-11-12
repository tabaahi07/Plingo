package in.wimsyclimsy.plingo.engine;

import in.wimsyclimsy.plingo.commons.GameInfoResponse;
import in.wimsyclimsy.plingo.commons.PickCardRequest;
import in.wimsyclimsy.plingo.commons.RoomInfoResponse;
import in.wimsyclimsy.plingo.commons.ThrowCardRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public interface IPlingoEngine {
    public RoomInfoResponse getRoomInfo(String roomCode);
    public CRUDStatus leaveRoom(String roomCode , String userId);
    public CRUDStatus ready(String roomCode, String userId);
    public GameInfoResponse getGameInfo(String roomCode, String userId);
    public void pickCard(String roomCode, PickCardRequest request);
    public void throwCard(String roomCode, ThrowCardRequest request);
    public Boolean verifyWinner(String roomCode, String userId);
    public void kickOut(String roomCode, String userId, String kickoutId);
}
