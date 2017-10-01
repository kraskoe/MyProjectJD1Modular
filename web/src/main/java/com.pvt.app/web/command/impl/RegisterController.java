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
public class RegisterController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String passwordConfirmation = req.getParameter("passwordConfirmation");
        if (login==null || password==null) {
            req.setAttribute("errorMsg", "&nbsp");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "register");
            dispatcher.forward(req, resp);
            return;
        }
        if (login.trim().length() == 0 || password.trim().length() == 0 || !(password.equals(passwordConfirmation))) {
            req.setAttribute("errorMsg", "Wrong data input");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "register");
            dispatcher.forward(req, resp);
            return;
        }
        User user = userService.getByLogin(login);
        if (user == null) {
            user = userService.registerUser(login, Encoder.encode(password));
            req.getSession().setAttribute("user", user);
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+ "/frontController?command=tours");
            return;
        } else {
            req.setAttribute("errorMsg", "User already exists");
            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
            req.setAttribute("title", "register");
            dispatcher.forward(req, resp);
            return;
        }
    }
}
