package com.pvt.app.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/**
 * Class OrderDto
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class OrderDto {
    private long id;
    private String country;
    private String city;
    private String hotel;
    private String board;
    private Date departure;
    private Date arrival;
    private int duration;
    private int quantity;
    private double fullCost;
    private Date orderDate;
}
