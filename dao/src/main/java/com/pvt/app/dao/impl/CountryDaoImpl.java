package com.pvt.app.dao.impl;

import com.pvt.app.dao.CountryDao;
import com.pvt.app.entities.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class CountryDaoImpl
 * <p>
 * Created by ykrasko on 15/08/2017.
 */
public class CountryDaoImpl extends AbstractDao implements CountryDao {
    private static volatile CountryDao INSTANCE = null;

    private static final String getAllQuery = "SELECT * FROM countries";
    private static final String saveQuery = "INSERT INTO countries (country_name) VALUES (?)";
    private static final String getQuery = "SELECT * FROM countries WHERE country_id=?";
    private static final String updateQuery = "UPDATE countries SET country_name=? WHERE country_id=?";
    private static final String deleteQuery = "DELETE FROM countries WHERE country_id=?";

    private PreparedStatement psGetAll;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;


    public static CountryDao getInstance() {
        CountryDao countryDao = INSTANCE;
        if (countryDao == null) {
            synchronized (CountryDaoImpl.class) {
                countryDao = INSTANCE;
                if (countryDao == null) {
                    INSTANCE = countryDao = new CountryDaoImpl();
                }
            }
        }
        return countryDao;
    }

    @Override
    public List<Country> getAllCountries() throws SQLException {
        psGetAll = prepareStatement(getAllQuery);
        psGetAll.executeQuery();
        List<Country> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }


    @SuppressWarnings("Duplicates")
    @Override
    public Country save(Country country) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, country.getName());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            country.setId(rs.getLong(1));
        }
        close(rs);
        return country;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Country get(long id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Country country) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setString(1, country.getName());
        psUpdate.setLong(2, country.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private Country fillEntity(ResultSet rs) throws SQLException {
        Country entity = new Country();
        entity.setId(rs.getLong(1));
        entity.setName(rs.getString(2));
        return entity;
    }
}