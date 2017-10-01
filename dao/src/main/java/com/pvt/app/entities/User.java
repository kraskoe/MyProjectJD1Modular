package com.pvt.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class User
 *
 * Created by ykrasko on 15/08/2017.
 */
@Data
@NoArgsConstructor
public class User {
    private long id;
    String login;
    String password;
    String role;
}
