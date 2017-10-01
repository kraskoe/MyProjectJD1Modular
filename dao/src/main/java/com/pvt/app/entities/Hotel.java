package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Hotel
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class Hotel {
    private long id;
    String name;
    int stars;
    long cityId;
    int boardId;
    double roomPrice;
    double boardPrice;
}
