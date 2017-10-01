package com.pvt.app.dao.impl;

import com.pvt.app.dao.CityDao;
import com.pvt.app.entities.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CityDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class CityDaoImpl extends AbstractDao implements CityDao {
    private static volatile CityDao INSTANCE = null;

    private static final String getAllQuery = "SELECT * FROM cities";
    private static final String getByCountryQuery = "SELECT * FROM cities WHERE c_id=?";
    private static final String saveQuery = "INSERT INTO cities (city_name, c_id) VALUES (?, ?)";
    private static final String getQuery = "SELECT * FROM cities WHERE city_id=?";
    private static final String updateQuery = "UPDATE cities SET city_name=?, c_id=? WHERE city_id=?";
    private static final String deleteQuery = "DELETE FROM cities WHERE city_id=?";

    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllByCountry;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;


    public static CityDao getInstance() {
        CityDao cityDao = INSTANCE;
        if (cityDao == null) {
            synchronized (CityDaoImpl.class) {
                cityDao = INSTANCE;
                if (cityDao == null) {
                    INSTANCE = cityDao = new CityDaoImpl();
                }
            }
        }
        return cityDao;
    }

    @Override
    public List<City> getAllCities() throws SQLException {
        psGetAll = prepareStatement(getAllQuery);
        psGetAll.executeQuery();
        List<City> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public List<City> getByCountry(long countryId) throws SQLException{
        psGetAllByCountry = prepareStatement(getByCountryQuery);
        psGetAllByCountry.setInt(1, (int)countryId);
        psGetAllByCountry.executeQuery();
        ResultSet rs = psGetAllByCountry.getResultSet();
        List<City> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public City save(City city) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, city.getName());
        psSave.setLong(2, city.getCountryId());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            city.setId(rs.getLong(1));
        }
        close(rs);
        return city;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public City get(long id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(City city) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setString(1, city.getName());
        psUpdate.setLong(2, city.getCountryId());
        psUpdate.setLong(3, city.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    private City fillEntity(ResultSet rs) throws SQLException {
        City entity = new City();
        entity.setId(rs.getLong(1));
        entity.setName(rs.getString(2));
        entity.setCountryId(rs.getLong(3));
        return entity;
    }
}