package com.pvt.app.web.command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.pvt.app.web.command.Controller;
import com.pvt.app.web.command.impl.*;

/**
 * Enum CommandType
 *
 * Created by ykrasko on 15/08/2017.
 */
@Getter
@AllArgsConstructor
public enum CommandType {
    LOGIN("login.jsp", "login", new LoginController()),
    LOGOUT("login.jsp", "logout", new LogoutController()),
    REGISTER("registration.jsp", "register", new RegisterController()),
    ORDERS("orders/main.jsp", "orders", new OrderController()),
    TOURS("tours/main.jsp", "tours", new TourController()),
    MAKE_ORDER("", "MakeOrder", new MakeOrderController()),
    GET_CITIES_AJAX("", "getCities", new GetCitiesController()),
    GET_FLIGHTS_AJAX("", "getFlights", new GetFlightsController()),
    GET_HOTELS_AJAX("", "getHotels", new GetHotelsController()),
    GET_DURATION_AJAX("", "getDuration", new GetDurationController()),
    DELETE_ORDER_AJAX("", "deleteOrder", new DeleteOrderController()),
    GET_BOARDS_AJAX("", "getBoards", new GetBoardsController());

    private String pagePath;
    private String pageName;
    private Controller controller;

    public static CommandType getByPageName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
// Если ничего не найдено, то на главную страницу с турами
        return TOURS;
    }
}
