package com.pvt.app.dao;

import com.pvt.app.entities.Board;

import java.sql.SQLException;
import java.util.List;

/**
 * Class BoardDao
 *
 * Created by ykrasko on 15/08/2017.
 */
public interface BoardDao extends DAO<Board> {
    List<Board> getAllBoards() throws SQLException;
    List<Board> getByHotel(long hotelId) throws SQLException;
}
