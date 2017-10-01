package com.pvt.app.web.command.impl;

import com.pvt.app.entities.User;
import com.pvt.app.services.UserService;
import com.pvt.app.services.impl.UserServiceImpl;
import com.pvt.app.web.auth.Encoder;
import com.pvt.app.web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class LoginController
 *
 * Created by ykrasko on 15/08/2017.
 */
public class LoginController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login==null || password==null) {
            req.setAttribute("errorMsg", "&nbsp");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "login");
            dispatcher.forward(req, resp);
            return;
        }
        if (login.trim().length() == 0 || password.trim().length() == 0) {
            req.setAttribute("errorMsg", "Missing login or password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "login");
            dispatcher.forward(req, resp);
            return;
        }
        User user = userService.getByLogin(login);
        if (user != null && user.getPassword().equals(Encoder.encode(password))) {
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+ "/frontController?command=tours");
            return;
        } else {
            req.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "login");
            dispatcher.forward(req, resp);
            return;
        }
    }
}
