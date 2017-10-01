package com.pvt.app.dao;

import com.pvt.app.entities.Flight;

import java.sql.SQLException;
import java.util.List;

/**
 * Class FlightDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface FlightDao extends DAO<Flight> {
    List<Flight> getAllFlights() throws SQLException;
    List<Flight> getByCountry(long countryId) throws SQLException;
    int getDuration(long flightId) throws SQLException;
}
