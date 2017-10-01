package com.pvt.app.dao;

import com.pvt.app.entities.Hotel;

import java.sql.SQLException;
import java.util.List;

/**
 * Class HotelDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface HotelDao extends DAO<Hotel> {
    List<Hotel> getAllHotels() throws SQLException;
    List<Hotel> getByCity(long cityId) throws SQLException;
}
