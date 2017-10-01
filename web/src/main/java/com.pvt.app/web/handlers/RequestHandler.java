package com.pvt.app.web.handlers;

import com.pvt.app.web.command.enums.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.pvt.app.web.command.enums.CommandType.TOURS;

/**
 * Class RequestHandler
 *
 * Created by ykrasko on 15/08/2017.
 */
public class RequestHandler {
    public static CommandType getCommand(HttpServletRequest req){
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "tours";
        }


        CommandType type = CommandType.getByPageName(param);
        req.setAttribute("title", type.getPageName());
        HttpSession session = req.getSession();
        String pageName = (String)session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", TOURS.getPageName());
            session.setAttribute("pagePath", TOURS.getPagePath());
        }

        return type;
    }
}
