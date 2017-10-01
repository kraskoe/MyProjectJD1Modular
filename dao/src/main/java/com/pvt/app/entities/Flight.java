package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Class Flight
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class Flight {
    private long id;
    Date departure;
    Date arrival;
    long countryId;
    double flightCost;
}
