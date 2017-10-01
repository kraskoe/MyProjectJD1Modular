package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class City
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class City {
    private long id;
    private long countryId;
    String name;
}
