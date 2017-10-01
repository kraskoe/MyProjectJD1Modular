package com.pvt.app.dao;

import java.sql.SQLException;

/**
 * Class DAO
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface DAO<T> {
    T save(T t) throws SQLException;
    T get(long id) throws SQLException;
    void update(T t) throws SQLException;
    int delete(long id) throws SQLException;
}
