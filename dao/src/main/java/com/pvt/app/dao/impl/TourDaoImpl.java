package com.pvt.app.dao.impl;

import com.pvt.app.dao.TourDao;
import com.pvt.app.entities.Tour;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class TourDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class TourDaoImpl extends AbstractDao implements TourDao {
    private static volatile TourDao INSTANCE = null;

    private static final String saveTourQuery = "INSERT INTO tours (h_id, duration, b_id, p_quantity, full_cost, f_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String getTourQuery = "SELECT * FROM tours WHERE tour_id=?";
    private static final String updateTourQuery = "UPDATE tours SET h_id=?, duration=?, b_id=?, p_quantity=?, full_cost=?, f_id=? WHERE tour_id=?";
    private static final String deleteTourQuery = "DELETE FROM tours WHERE tour_id=?";
    private static final String getTourByOrderQuery = "SELECT * FROM tours, orders WHERE orders.t_id=tours.tour_id AND order_id=?";
    private static final String getToursByUserQuery = "SELECT * FROM tours, orders, users WHERE orders.t_id=tours.tour_id AND orders.u_id=users.user_id AND user_id=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetByOrder;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    protected TourDaoImpl() {
    }

    public static TourDao getInstance() {
        TourDao tourDao = INSTANCE;
        if (tourDao == null) {
            synchronized (TourDaoImpl.class) {
                tourDao = INSTANCE;
                if (tourDao == null) {
                    INSTANCE = tourDao = new TourDaoImpl();
                }
            }
        }
        return tourDao;
    }

    @Override
    public Tour save(Tour tour) throws SQLException {
        psSave = prepareStatement(saveTourQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, tour.getHotelID());
        psSave.setInt(2, tour.getDuration());
        psSave.setLong(3, tour.getBoardID());
        psSave.setInt(4, tour.getQuantity());
        psSave.setDouble(5, tour.getFullCost());
        psSave.setLong(6, tour.getFlightID());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            tour.setId(rs.getLong(1));
        }
        close(rs);
        return tour;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Tour get(long id) throws SQLException {
        psGet = prepareStatement(getTourQuery);
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
    public void update(Tour tour) throws SQLException {
        psUpdate = prepareStatement(updateTourQuery);
        psUpdate.setLong(1, tour.getHotelID());
        psUpdate.setInt(2, tour.getDuration());
        psUpdate.setLong(3, tour.getBoardID());
        psUpdate.setInt(4, tour.getQuantity());
        psUpdate.setDouble(5, tour.getFullCost());
        psUpdate.setLong(6, tour.getFlightID());
        psUpdate.setLong(7, tour.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteTourQuery);
        psDelete.setLong(1, id);
        return psDelete.executeUpdate();
    }

    @Override
    public Tour getByOrderId(long orderId) throws SQLException {
        psGetByOrder = prepareStatement(getTourByOrderQuery);
        psGetByOrder.setLong(1, orderId);
        psGetByOrder.execute();
        Tour tour = new Tour();
        ResultSet rs = psGetByOrder.getResultSet();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public List<Tour> getByUserId(long userId) throws SQLException {
        psGetAll = prepareStatement(getToursByUserQuery);
        psGetAll.setLong(1, userId);
        psGetAll.execute();
        List<Tour> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    private Tour fillEntity(ResultSet rs) throws SQLException {
        Tour entity = new Tour();
        entity.setId(rs.getLong(1));
        entity.setHotelID(rs.getLong(2));
        entity.setDuration(rs.getInt(3));
        entity.setBoardID(rs.getLong(4));
        entity.setFullCost(rs.getDouble(5));
        entity.setFlightID(rs.getLong(6));
        return entity;
    }
}
