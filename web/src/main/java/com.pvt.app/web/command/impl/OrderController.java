package com.pvt.app.web.command.impl;

import com.pvt.app.dao.OrderDto;
import com.pvt.app.entities.User;
import com.pvt.app.services.OrderService;
import com.pvt.app.services.impl.OrderServiceImpl;
import com.pvt.app.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class OrderController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class OrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<OrderDto> orders = orderService.getOrders(user.getId());

        req.setAttribute("orders", orders);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
