package com.pvt.app.web.command.impl;

import com.google.gson.Gson;
import com.pvt.app.services.TourService;
import com.pvt.app.services.impl.TourServiceImpl;
import com.pvt.app.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class GetDurationController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class GetDurationController implements Controller {
    private TourService tourService = TourServiceImpl.getInstance();
    int duration = 0;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        if (req.getParameter("flightId") != null){

            long flightId = Long.parseLong(req.getParameter("flightId"));
            duration = tourService.getDuration(flightId);

            writer.print(new Gson().toJson(duration));
        } else {
            writer.print("Error: null cityID");
        }

    }

}
