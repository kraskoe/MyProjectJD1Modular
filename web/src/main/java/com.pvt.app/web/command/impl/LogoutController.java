package com.pvt.app.web.command.impl;

import com.pvt.app.web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class LogoutController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class LogoutController implements Controller {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        String contextPath = req.getContextPath();
        resp.sendRedirect(contextPath+ "/frontController?command=tours");
    }
}
