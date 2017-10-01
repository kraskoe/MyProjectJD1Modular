package com.pvt.app.services;

import com.pvt.app.entities.*;

import java.util.List;

/**
 * Class TourDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface TourService {

    List<Country> getAllCountries();
    List<Flight> getAllFlights();
    List<City> getAllCities();
    List<Hotel> getAllHotels();
    List<Board> getAllBoards();

    List<Flight> getFlightsByCountryId(long id);
    List<City> getCitiesByCountryId(long id);
    List<Hotel> getHotelsByCityId(long id);
    List<Board> getBoardsByHotelId(long id);

    int getDuration(long id);
}
