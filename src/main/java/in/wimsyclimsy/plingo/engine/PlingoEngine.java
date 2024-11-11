package in.wimsyclimsy.plingo.engine;

import org.springframework.beans.factory.annotation.Autowired;

import in.wimsyclimsy.plingo.commons.GameInfoResponse;
import in.wimsyclimsy.plingo.commons.PickCardRequest;
import in.wimsyclimsy.plingo.commons.RoomInfoResponse;
import in.wimsyclimsy.plingo.commons.ThrowCardRequest;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.service.GameService;
import in.wimsyclimsy.plingo.service.RoomService;
import in.wimsyclimsy.plingo.service.UserService;

public class PlingoEngine implements IPlingoEngine{

    @Autowired
    RoomService roomService;
    @Autowired
    UserService userService;
    @Autowired
    GameService gameService;

    @Override
    public RoomInfoResponse getRoomInfo(String roomCode) {
        return roomService.getRoomInfo(roomCode).get();
    }

    @Override
    public CRUDStatus leaveRoom(String roomCode, String userId) {
        return roomService.leaveRoom(userId, roomCode) ;
    }

    @Override
    public CRUDStatus ready(String roomCode, String userId) {
        return userService.updateReadyStatus(userId, true);
    }
    
    @Override
    public GameInfoResponse getGameInfo(String roomCode, String userId) {
        return gameService.getGameInfo(roomCode, userId).get();
    }

    
    @Override
    public void pickCard(String roomCode, PickCardRequest request) {
        gameService.pickCard(request.getUserId() , roomCode, request.getIsOpen());
    }

    @Override
    public void throwCard(String roomCode, ThrowCardRequest request) {
        gameService.throwCard(request.getUserId(), roomCode, request.getThrownCard());
    }

    @Override
    public CRUDStatus verifyWinner(String roomCode, String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyWinner'");
    }

    @Override
    public void kickOut(String roomCode, String userId, String kickoutId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'kickOut'");
    }

}
