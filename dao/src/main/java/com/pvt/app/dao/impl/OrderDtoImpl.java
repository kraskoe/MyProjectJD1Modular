package com.pvt.app.dao.impl;

import com.pvt.app.db.ConnectionManager;
import com.pvt.app.dao.OrderDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class OrderDtoImplImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class OrderDtoImpl {
    private static volatile OrderDtoImpl INSTANCE = null;

    private static final String getAllByUserQuery =
            "SELECT countries.country_name, " +
            "cities.city_name, " +
            "hotels.hotel_name, " +
            "boards.board_type, " +
            "flights.departure, " +
            "flights.arrival, " +
            "tours.duration, " +
            "tours.p_quantity, " +
            "tours.full_cost, " +
            "orders.order_date, " +
            "orders.order_id " +
            "FROM orders, tours, flights, hotels, cities, countries, boards " +
            "WHERE orders.t_id=tours.tour_id " +
            "AND tours.h_id=hotels.hotel_id " +
            "AND tours.b_id=boards.board_id " +
            "AND tours.f_id=flights.flight_id " +
            "AND hotels.c_id=cities.city_id " +
            "AND cities.c_id=countries.country_id " +
            "AND orders.u_id=? ORDER BY orders.order_date DESC;";

    private PreparedStatement psGetAllByUserId;

    public static OrderDtoImpl getInstance() {
        OrderDtoImpl orderDto = INSTANCE;
        if (orderDto == null) {
            synchronized (OrderDtoImpl.class) {
                orderDto = INSTANCE;
                if (orderDto == null) {
                    INSTANCE = orderDto = new OrderDtoImpl();
                }
            }
        }
        return orderDto;
    }

    protected PreparedStatement prepareStatement(String query) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query);
    }

    protected void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderDto> getByUserId(long userId) throws SQLException {
        psGetAllByUserId = prepareStatement(getAllByUserQuery);
        psGetAllByUserId.setLong(1, userId);
        psGetAllByUserId.executeQuery();
        ResultSet rs = psGetAllByUserId.getResultSet();
        List<OrderDto> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    private OrderDto fillEntity(ResultSet rs) throws SQLException {
        OrderDto entity = new OrderDto();
            entity.setCountry(rs.getString(1));
            entity.setCity(rs.getString(2));
            entity.setHotel(rs.getString(3));
            entity.setBoard(rs.getString(4));
            entity.setDeparture(rs.getDate(5));
            entity.setArrival(rs.getDate(6));
            entity.setDuration(rs.getInt(7));
            entity.setQuantity(rs.getInt(8));
            entity.setFullCost(rs.getDouble(9));
            entity.setOrderDate(rs.getDate(10));
            entity.setId(rs.getLong(11));
        return entity;
    }
}
