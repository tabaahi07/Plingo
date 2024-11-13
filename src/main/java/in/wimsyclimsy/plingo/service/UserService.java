package in.wimsyclimsy.plingo.service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                .kickoutCount(0)
                .cards(new ArrayList<Card>()).build()
        ) ;
        return GenerateUserResponse.builder().userId(userId).userName(userName).userToken(userToken).build() ;
    }

    

    public CRUDStatus updateUserName(String userId , String userName){
        Optional<User> currentUser = userDAO.getUser(userId);
        if(currentUser == null) return CRUDStatus.REJECTED;
        // updating userName
        currentUser.get().setUserName(userName);
        // updating in db
        userDAO.updateUser(userId, currentUser.get());
        return CRUDStatus.APPROVED;
    }

    public CRUDStatus updateReadyStatus(String userId , Boolean status){
        Optional<User> currentUser = userDAO.getUser(userId);
        if(currentUser == null) return CRUDStatus.REJECTED;
        // updating ready status
        currentUser.get().setIsReady(status);
        // updating in db
        userDAO.updateUser(userId, currentUser.get());
        return CRUDStatus.APPROVED;
    }

}
