package com.pvt.app.web.command.impl;

import com.google.gson.Gson;
import com.pvt.app.entities.Flight;
import com.pvt.app.services.TourService;
import com.pvt.app.services.impl.TourServiceImpl;
import com.pvt.app.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class GetFlightsController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class GetFlightsController implements Controller {
    private TourService tourService = TourServiceImpl.getInstance();
    List<Flight> flightList = new ArrayList<>();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("countryId") != null){

            long countryId = Long.parseLong(req.getParameter("countryId"));
            flightList = tourService.getFlightsByCountryId(countryId);

            String flightListJson = new Gson().toJson(flightList);
            writer.print(flightListJson);
        } else {
            writer.print("Error: null countryId");
        }

    }
}
