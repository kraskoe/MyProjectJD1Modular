package com.pvt.app.web.command.impl;

import com.pvt.app.entities.User;
import com.pvt.app.services.OrderService;
import com.pvt.app.services.impl.OrderServiceImpl;
import com.pvt.app.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class MakeOrderController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class MakeOrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user")==null){
            req.setAttribute("errorMsg", "Please, log in!");
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+ "/frontController?command=login");
            return;
        } else {
            if (req.getParameter("hotelForm") != null && req.getParameter("boardValue") != null && req.getParameter("quantityForm") != null &&
                    req.getParameter("flightForm") != null) {
                User user = (User)req.getSession().getAttribute("user");
                String hotelForm = req.getParameter("hotelForm");
                String boardForm = req.getParameter("boardValue");
                String quantityForm = req.getParameter("quantityForm");
                String flightForm = req.getParameter("flightForm");
                orderService.createOrder(user.getId(), Long.parseLong(hotelForm), Long.parseLong(boardForm),
                        Integer.parseInt(quantityForm), Long.parseLong(flightForm));
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath+ "/frontController?command=orders");
            } else {
                req.setAttribute("message", "Please, fill in all forms");
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath+ "/frontController?command=tours");
            }
        }
    }
}
