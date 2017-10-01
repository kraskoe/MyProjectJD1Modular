package com.pvt.app.dao.impl;

import com.pvt.app.dao.UserDao;
import com.pvt.app.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class UserDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {
    private static volatile UserDao INSTANCE = null;

    private static final String getUserByLogin = "SELECT * FROM users WHERE LOGIN=?";
    private static final String saveUserQuery = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";
    private static final String getUserQuery = "SELECT * FROM users WHERE user_id=?";
    private static final String updateUserQuery = "UPDATE users SET login=?, password=?, role=? WHERE user_id=?";
    private static final String deleteUserQuery = "DELETE FROM users WHERE user_id=?";


    private PreparedStatement psGetByLogin;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;


    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(getUserByLogin);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public User save(User user) throws SQLException {
        psSave = prepareStatement(saveUserQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, user.getLogin());
        psSave.setString(2, user.getPassword());
        psSave.setString(3, user.getRole());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public User get(long id) throws SQLException {
        psGet = prepareStatement(getUserQuery);
        psGet.setLong(1, id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(User user) throws SQLException {
        psUpdate = prepareStatement(updateUserQuery);
        psUpdate.setString(1, user.getLogin());
        psUpdate.setString(2, user.getPassword());
        psUpdate.setString(3, user.getRole());
        psUpdate.setLong(4, user.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteUserQuery);
        psDelete.setLong(1, id);
        return psDelete.executeUpdate();
    }

    private User fillEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setLogin(rs.getString(2));
        entity.setPassword(rs.getString(3));
        entity.setRole(rs.getString(4));
        return entity;
    }
}