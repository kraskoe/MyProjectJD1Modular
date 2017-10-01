package com.pvt.app.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class Controller
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface Controller {
    String MAIN_PAGE ="/WEB-INF/view/layouts/default.jspx";

    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
