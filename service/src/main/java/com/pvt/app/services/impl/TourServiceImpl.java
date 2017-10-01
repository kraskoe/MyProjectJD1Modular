package com.pvt.app.services.impl;

import com.pvt.app.dao.*;
import com.pvt.app.dao.impl.*;
import com.pvt.app.entities.*;
import com.pvt.app.services.ServiceException;
import com.pvt.app.services.TourService;

import java.sql.SQLException;
import java.util.List;

/**
 * Class TourDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public class TourServiceImpl extends AbstractService implements TourService {
    private static volatile TourService INSTANCE = null;

    private CountryDao countryDao = CountryDaoImpl.getInstance();
    private FlightDao flightDao = FlightDaoImpl.getInstance();
    private CityDao cityDao = CityDaoImpl.getInstance();
    private HotelDao hotelDao = HotelDaoImpl.getInstance();
    private BoardDao boardDao = BoardDaoImpl.getInstance();

    public static TourService getInstance() {
        TourService tourService = INSTANCE;
        if (tourService == null) {
            synchronized (TourServiceImpl.class) {
                tourService = INSTANCE;
                if (tourService == null) {
                    INSTANCE = tourService = new TourServiceImpl();
                }
            }
        }
        return tourService;
    }

    @Override
    public int getDuration(long flightId){
        try {
            return flightDao.getDuration(flightId);
        } catch (SQLException se){
            throw new ServiceException("Error getting duration");
        }
    }

    @Override
    public List<Country> getAllCountries() {
        try {
            return countryDao.getAllCountries();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all Countries");
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        try {
            return flightDao.getAllFlights();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all Flights");
        }
    }

    @Override
    public List<City> getAllCities() {
        try {
            return cityDao.getAllCities();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all Cities");
        }
    }

    @Override
    public List<Hotel> getAllHotels() {
        try {
            return hotelDao.getAllHotels();
        } catch (SQLException e) {
            throw new ServiceException("Error getting all Hotels");
        }
    }

    @Override
    public List<Board> getAllBoards() {
        try {
            return boardDao.getAllBoards();
        } catch (SQLException e) {
            throw new ServiceException("Error getting AllBoards");
        }
    }

    @Override
    public List<Flight> getFlightsByCountryId(long id) {
        try {
            return flightDao.getByCountry(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Flights by id" + id);
        }
    }

    @Override
    public List<City> getCitiesByCountryId(long id) {
        try {
            return cityDao.getByCountry(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Cities by id" + id);
        }
    }

    @Override
    public List<Hotel> getHotelsByCityId(long id) {
        try {
            return hotelDao.getByCity(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Hotels by id" + id);
        }
    }

    @Override
    public List<Board> getBoardsByHotelId(long id) {
        try {
            return boardDao.getByHotel(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Boards by id" + id);
        }
    }

}
