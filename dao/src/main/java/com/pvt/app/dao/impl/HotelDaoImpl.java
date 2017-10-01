package com.pvt.app.dao.impl;

import com.pvt.app.dao.HotelDao;
import com.pvt.app.entities.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class HotelDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class HotelDaoImpl extends AbstractDao implements HotelDao {
    private static volatile HotelDao INSTANCE = null;

    private static final String getAllQuery = "SELECT * FROM hotels";
    private static final String getByCityQuery = "SELECT * FROM hotels WHERE c_id=?";
    private static final String saveQuery = "INSERT INTO hotels (hotel_name, stars, c_id, b_id, room_price, board_price) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String getQuery = "SELECT * FROM hotels WHERE hotel_id=?";
    private static final String updateQuery = "UPDATE hotels SET hotel_name=?, stars=?, c_id=?, b_id=?, room_price=?, board_price=? WHERE hotel_id=?";
    private static final String deleteQuery = "DELETE FROM hotels WHERE hotel_id=?";

    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllByCity;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;


    public static HotelDao getInstance() {
        HotelDao hotelDao = INSTANCE;
        if (hotelDao == null) {
            synchronized (HotelDaoImpl.class) {
                hotelDao = INSTANCE;
                if (hotelDao == null) {
                    INSTANCE = hotelDao = new HotelDaoImpl();
                }
            }
        }
        return hotelDao;
    }

    @Override
    public List<Hotel> getAllHotels() throws SQLException {
        psGetAll = prepareStatement(getAllQuery);
        psGetAll.executeQuery();
        List<Hotel> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public List<Hotel> getByCity(long cityId) throws SQLException{
        psGetAllByCity = prepareStatement(getByCityQuery);
        psGetAllByCity.setLong(1, cityId);
        psGetAllByCity.executeQuery();
        ResultSet rs = psGetAllByCity.getResultSet();
        List<Hotel> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public Hotel save(Hotel hotel) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, hotel.getName());
        psSave.setInt(2, hotel.getStars());
        psSave.setLong(3, hotel.getCityId());
        psSave.setLong(4, hotel.getBoardId());
        psSave.setDouble(5, hotel.getRoomPrice());
        psSave.setDouble(6, hotel.getBoardPrice());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            hotel.setId(rs.getLong(1));
        }
        close(rs);
        return hotel;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Hotel get(long id) throws SQLException {
        psGet = prepareStatement(getQuery);
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
    public void update(Hotel hotel) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setString(1, hotel.getName());
        psUpdate.setInt(2, hotel.getStars());
        psUpdate.setLong(3, hotel.getCityId());
        psUpdate.setLong(4, hotel.getBoardId());
        psUpdate.setDouble(5, hotel.getRoomPrice());
        psUpdate.setDouble(6, hotel.getBoardPrice());
        psUpdate.setDouble(7, hotel.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, id);
        return psDelete.executeUpdate();
    }

    private Hotel fillEntity(ResultSet rs) throws SQLException {
        Hotel entity = new Hotel();
        entity.setId(rs.getLong(1));
        entity.setName(rs.getString(2));
        entity.setStars(rs.getInt(3));
        entity.setCityId(rs.getLong(4));
        entity.setBoardId(rs.getInt(5));
        entity.setRoomPrice(rs.getDouble(6));
        entity.setBoardPrice(rs.getDouble(7));
        return entity;
    }
}