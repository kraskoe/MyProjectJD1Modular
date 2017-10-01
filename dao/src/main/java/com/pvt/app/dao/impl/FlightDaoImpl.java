package com.pvt.app.dao.impl;

import com.pvt.app.dao.FlightDao;
import com.pvt.app.entities.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class FlightDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class FlightDaoImpl extends AbstractDao implements FlightDao {
    private static volatile FlightDao INSTANCE = null;

    private static final String getAllQuery = "SELECT * FROM flights";
    private static final String getByCountryQuery = "SELECT * FROM flights WHERE cn_id=?";
    private static final String saveQuery = "INSERT INTO flights (departure, arrival, cn_id, flight_cost) VALUES (?, ?, ?, ?)";
    private static final String getQuery = "SELECT * FROM flights WHERE flight_id=?";
    private static final String updateQuery = "UPDATE flights SET departure=?, arrival=?, cn_id=?, flight_cost=? WHERE flight_id=?";
    private static final String deleteQuery = "DELETE FROM flights WHERE flight_id=?";
    private static final String getDurationQuery = "SELECT DATEDIFF(arrival, departure) AS days FROM flights WHERE flight_id = ?";

    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllByCountry;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;
    private PreparedStatement psGetDuration;


    public static FlightDao getInstance() {
        FlightDao flightDao = INSTANCE;
        if (flightDao == null) {
            synchronized (FlightDaoImpl.class) {
                flightDao = INSTANCE;
                if (flightDao == null) {
                    INSTANCE = flightDao = new FlightDaoImpl();
                }
            }
        }
        return flightDao;
    }

    @Override
    public int getDuration(long flightId) throws SQLException {
        int duration = 0;
        psGetDuration = prepareStatement(getDurationQuery);
        psGetDuration.setLong(1, flightId);
        psGetDuration.executeQuery();
        ResultSet rs = psGetDuration.getResultSet();
        if (rs.next()) {
            duration = rs.getInt(1) - 1;
        }
        close(rs);
        return duration;
    }

    @Override
    public List<Flight> getAllFlights() throws SQLException {
        psGetAll = prepareStatement(getAllQuery);
        psGetAll.executeQuery();
        List<Flight> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public List<Flight> getByCountry(long countryId) throws SQLException{
        psGetAllByCountry = prepareStatement(getByCountryQuery);
        psGetAllByCountry.setLong(1, countryId);
        psGetAllByCountry.executeQuery();
        ResultSet rs = psGetAllByCountry.getResultSet();
        List<Flight> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public Flight save(Flight flight) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setDate(1, flight.getDeparture());
        psSave.setDate(2, flight.getArrival());
        psSave.setLong(3, flight.getCountryId());
        psSave.setDouble(4, flight.getFlightCost());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            flight.setId(rs.getLong(1));
        }
        close(rs);
        return flight;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Flight get(long id) throws SQLException {
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
    public void update(Flight flight) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setDate(1, flight.getDeparture());
        psUpdate.setDate(2, flight.getArrival());
        psUpdate.setLong(3, flight.getCountryId());
        psUpdate.setDouble(4, flight.getFlightCost());
        psUpdate.setLong(5, flight.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, id);
        return psDelete.executeUpdate();
    }

    private Flight fillEntity(ResultSet rs) throws SQLException {
        Flight entity = new Flight();
        entity.setId(rs.getLong(1));
        entity.setDeparture(rs.getDate(2));
        entity.setArrival(rs.getDate(3));
        entity.setCountryId(rs.getLong(4));
        entity.setFlightCost(rs.getDouble(5));
        return entity;
    }
}