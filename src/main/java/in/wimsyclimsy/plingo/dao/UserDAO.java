package in.wimsyclimsy.plingo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public class UserDAO implements IUserDAO{

    // UserId - User object
    private Map<String,User> users = new HashMap<>();
    
    @Override
    public CRUDStatus createUser(String userId, User user) {
        if(user == null) return CRUDStatus.REJECTED;
        users.put(userId, user) ;
        return CRUDStatus.APPROVED;
    }

    @Override
    public CRUDStatus updateUser(String userId, User user) {
        users.put(userId, user) ;
        return CRUDStatus.APPROVED;
    }

    @Override
    public CRUDStatus deleteUser(String userId) {
        if(!users.containsKey(userId)) return CRUDStatus.REJECTED;
        users.remove(userId);
        return CRUDStatus.APPROVED;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return Optional.of(users.get(userId));
    }

}
