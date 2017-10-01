package com.pvt.app.services;

import com.pvt.app.dao.OrderDto;
import com.pvt.app.entities.Order;

import java.util.List;

/**
 * Class TourDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface OrderService {

    Order createOrder(long userId, long hotelId, long boardId, int quantity, long flightId);
    List<OrderDto> getOrders(long id);
    void updateOrder(Order order);
    int deleteOrder(long id);

}
