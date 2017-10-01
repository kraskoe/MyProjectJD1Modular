package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Item
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class Tour {
    private long id;
    private long hotelID;
    private int duration;
    private long boardID;
    private int quantity;
    private double fullCost;
    private long flightID;

    public Tour(long id, long hotelID, int duration, long boardID, int quantity, double fullCost, long flightID) {
        this.id = id;
        this.hotelID = hotelID;
        this.duration = duration;
        this.boardID = boardID;
        this.quantity = quantity;
        this.fullCost = fullCost;
        this.flightID = flightID;
    }
}
