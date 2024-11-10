package in.wimsyclimsy.plingo.service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.BooleanArraySerializer;

import in.wimsyclimsy.plingo.commons.GenerateUserResponse;
import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;
import in.wimsyclimsy.plingo.dao.RoomDAO;
import in.wimsyclimsy.plingo.dao.UserDAO;
import in.wimsyclimsy.plingo.game.Enums.Card;
import lombok.Builder;

@Builder
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoomDAO roomDAO;

    public GenerateUserResponse createUser(){
        String userId = UUID.randomUUID().toString();
        String userToken = UUID.randomUUID().toString();
        String userName = UUID.randomUUID().toString();
        userDAO.createUser(userId, 
            User.builder().userId(userId)
                .userName(userName)
                .userToken(userToken)
                .roomCode(null)
                .isReady(false)
                .cards(new ArrayList<Card>()).build()
        ) ;
        return GenerateUserResponse.builder().userId(userId).userName(userName).userToken(userToken).build() ;
    }

    

    public CRUDStatus updateUserName(String userId , String userName){
        Optional<User> currentUser = userDAO.getUser(userId);
        if(currentUser == null) return CRUDStatus.REJECTED;
        User updatedUser = User.builder()
                .roomCode(currentUser.get().getRoomCode())
                .userId(userId)
                .userName(userName)
                .userToken(currentUser.get().getUserToken())
                .cards(currentUser.get().getCards())
                .isReady(currentUser.get().getIsReady())
                .build();

        userDAO.updateUser(userId, updatedUser);
        return CRUDStatus.APPROVED;
    }

    public CRUDStatus updateReadyStatus(String userId , Boolean status){
        Optional<User> currentUser = userDAO.getUser(userId);
        if(currentUser == null) return CRUDStatus.REJECTED;
        User updatedUser = User.builder()
                .roomCode(currentUser.get().getRoomCode())
                .userId(userId)
                .userName(currentUser.get().getUserName())
                .userToken(currentUser.get().getUserToken())
                .cards(currentUser.get().getCards())
                .isReady(status)
                .build();

        userDAO.updateUser(userId, updatedUser);
        return CRUDStatus.APPROVED;
    }
}
