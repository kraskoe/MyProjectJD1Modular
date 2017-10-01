package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Order
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class Order {
    private long id;
    private long tourId;
    private long userId;
    private java.sql.Date orderDate;
}
