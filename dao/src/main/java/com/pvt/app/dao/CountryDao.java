package com.pvt.app.dao;

import com.pvt.app.entities.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Class CityDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface CountryDao extends DAO<Country> {
    List<Country> getAllCountries() throws SQLException;
}
