package in.wimsyclimsy.plingo.dao;
import java.util.Optional;

import in.wimsyclimsy.plingo.commons.User;
import in.wimsyclimsy.plingo.commons.Enums.CRUDStatus;

public interface IUserDAO {
    public CRUDStatus createUser(String userId, User user);
    public CRUDStatus updateUser(String userId, User user);
    public CRUDStatus deleteUser(String userId);
    public Optional<User> getUser(String userId);
}
