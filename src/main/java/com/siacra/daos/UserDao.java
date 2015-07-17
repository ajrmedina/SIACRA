package com.siacra.daos;

import java.util.List;
import com.siacra.models.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * User DAO
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 */
@Repository
public class UserDao  {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Add User
     *
     * @param   user   User
     */

    public void addUser(User user) {
        getSessionFactory().getCurrentSession().save(user);
    }

    /**
     * Delete User
     *
     * @param   user   User
     */

    public void deleteUser(User user) {
        getSessionFactory().getCurrentSession().delete(user);
    }

    /**
     * Update User
     *
     * @param  user   User
     */

    public void updateUser(User user) {
        getSessionFactory().getCurrentSession().update(user);
    }

    /**
     * Get User
     *
     * @param  id int
     * @return User
     */

    public User getUserById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from User where idUsuario=?")
                                            .setParameter(0, id).list();
        return (User)list.get(0);
    }
    
    /**
     * Get User for Login
     *
     * @param  id int
     * @return User
     */

    public User getUserLogin(String login) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from User where nombreUsuario=?")
                                            .setParameter(0, login).list();
        if (list.size() > 0)
            return (User)list.get(0);
        else
            return null;
    }
    
    /**
     * Get User List
     *
     * @return List - Lista Users
     */

    public List<User> getUsers() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  User").list();
        return list;
    }

}

