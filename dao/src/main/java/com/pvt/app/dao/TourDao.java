package com.pvt.app.dao;

import com.pvt.app.entities.Tour;

import java.sql.SQLException;
import java.util.List;

/**
 * Class TourDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface TourDao extends DAO<Tour>{
    Tour getByOrderId(long orderId) throws SQLException;
    List<Tour> getByUserId(long userId) throws SQLException;
}
