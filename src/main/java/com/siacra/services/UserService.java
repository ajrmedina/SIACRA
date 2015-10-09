package com.siacra.services;

/**
 *
 * User Service
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 *
 */

import java.util.List;

import com.siacra.daos.UserDao;
import com.siacra.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
@Transactional(readOnly = true)
public class UserService {
    // UserDao is injected...
    @Autowired
    private UserDao userDAO;

    /**
     * Add User
     *
     * @param  user User
     */
    @Transactional(readOnly = false)
    public void addUser(User user) {
        getUserDao().addUser(user);
    }

    /**
     * Delete User
     *
     * @param   user User
     */
    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        getUserDao().deleteUser(user);
    }

    /**
     * Update User
     *
     * @param user User
     */
    @Transactional(readOnly = false)
    public void updateUser(User user) {
        getUserDao().updateUser(user);
    }

    /**
     * Get User
     *
     * @param  id int - User ID
     * @return User by ID - User
     */

    public User getUserById(int id) {
        return getUserDao().getUserById(id);
    }
    
    /**
     * Get User for Login
     *
     * @param  login String - User username
     * @return User by Username - User
     */

    public User getUserLogin(String login) {
        return getUserDao().getUserLogin(login);
    }
    
    /**
     * Get Users List
     *
     * @return Users List - User
     */

    public List<User> getUsers() {
        return getUserDao().getUsers();
    }
    public List<User> getUsersByEscuela(String codigo) {
        return getUserDao().getUsersByEscuela(codigo);
    }
    
    public boolean isUserDocente(int id) {
        return getUserDao().isUserDocente(id);
    }

    /**
     * Get User DAO
     *
     * @return userDAO - User DAO
     */
    public UserDao getUserDao() {
        return userDAO;
    }

    /**
     * Set User DAO
     *
     * @param  userDAO - User DAO
     */
    public void setUserDao(UserDao userDAO) {
        this.userDAO = userDAO;
    }
}
