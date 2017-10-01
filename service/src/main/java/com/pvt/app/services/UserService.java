package com.pvt.app.services;

import com.pvt.app.entities.User;

/**
 * Class UserService
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface UserService {

    User getByLogin(String login);
    User registerUser(String login, String password);
}
