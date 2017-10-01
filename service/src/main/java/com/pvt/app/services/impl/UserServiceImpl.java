package com.pvt.app.services.impl;

import com.pvt.app.dao.UserDao;
import com.pvt.app.dao.impl.UserDaoImpl;
import com.pvt.app.entities.User;
import com.pvt.app.services.ServiceException;
import com.pvt.app.services.UserService;

import java.sql.SQLException;

/**
 * Class UserServiceImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class UserServiceImpl extends AbstractService implements UserService {
    private static volatile UserService INSTANCE = null;
    private UserDao userDao = UserDaoImpl.getInstance();

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public User getByLogin(String login) {
        try {
            return userDao.getByLogin(login);
        } catch (SQLException e) {
            throw new ServiceException("Error getting User by login" + login);
        }
    }

    public User registerUser(String login, String password){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole("user");
        try {
            return userDao.save(user);
        } catch (SQLException e) {
            throw new ServiceException("Error registering new User" + user.getLogin());
        }
    }
}
