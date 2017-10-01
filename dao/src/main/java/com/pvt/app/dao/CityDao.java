package com.pvt.app.dao;

import com.pvt.app.entities.City;

import java.sql.SQLException;
import java.util.List;

/**
 * Class CityDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface CityDao extends DAO<City> {
    List<City> getAllCities() throws SQLException;
    List<City> getByCountry(long countryId) throws SQLException;
}
