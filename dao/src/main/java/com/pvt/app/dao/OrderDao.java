package com.pvt.app.dao;

import com.pvt.app.entities.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Class OrderDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface OrderDao extends DAO <Order> {
    List<Order> getByUserId(long userId) throws SQLException;
}
