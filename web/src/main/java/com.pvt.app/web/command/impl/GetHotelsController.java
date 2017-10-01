package com.pvt.app.web.command.impl;

import com.google.gson.Gson;
import com.pvt.app.entities.Hotel;
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
 * Class GetHotelsController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class GetHotelsController implements Controller {
    private TourService tourService = TourServiceImpl.getInstance();
    List<Hotel> hotelList = new ArrayList<>();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("cityId") != null){

            long cityId = Long.parseLong(req.getParameter("cityId"));
            hotelList = tourService.getHotelsByCityId(cityId);

            String hotelListJson = new Gson().toJson(hotelList);
            writer.print(hotelListJson);
        } else {
            writer.print("Error: null cityID");
        }

    }
}
