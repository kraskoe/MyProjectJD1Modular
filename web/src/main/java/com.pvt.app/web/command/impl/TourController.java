package com.pvt.app.web.command.impl;

import com.pvt.app.services.TourService;
import com.pvt.app.services.impl.TourServiceImpl;
import com.pvt.app.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class TourController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class TourController implements Controller {
    private TourService tourService = TourServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("countries", tourService.getAllCountries());
        req.getSession().setAttribute("cities", tourService.getAllCities());
        req.getSession().setAttribute("hotels", tourService.getAllHotels());
        req.getSession().setAttribute("flights", tourService.getAllFlights());
        req.getSession().setAttribute("boards", tourService.getAllBoards());

        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
