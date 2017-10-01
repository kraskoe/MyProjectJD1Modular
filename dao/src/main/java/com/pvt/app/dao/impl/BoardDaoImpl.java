package com.pvt.app.dao.impl;

import com.pvt.app.dao.BoardDao;
import com.pvt.app.entities.Board;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BoardDaoImpl
 *
 * Created by ykrasko on 15/08/2017.
 */
public class BoardDaoImpl extends AbstractDao implements BoardDao {
    private static volatile BoardDao INSTANCE = null;

    private static final String getAllQuery = "SELECT * FROM boards";
    private static final String getByHotelQuery = "SELECT * FROM boards, hotels WHERE hotels.b_id=boards.board_id AND hotels.hotel_id=?";
    private static final String saveQuery = "INSERT INTO boards (board_type) VALUES (?)";
    private static final String getQuery = "SELECT * FROM boards WHERE board_id=?";
    private static final String updateQuery = "UPDATE boards SET board_type=? WHERE board_id=?";
    private static final String deleteQuery = "DELETE FROM boards WHERE board_id=?";

    private PreparedStatement psGetAll;
    private PreparedStatement psGetAllByHotel;
    private PreparedStatement psSave;
    private PreparedStatement psGet;
    private PreparedStatement psUpdate;
    private PreparedStatement psDelete;


    public static BoardDao getInstance() {
        BoardDao boardDao = INSTANCE;
        if (boardDao == null) {
            synchronized (BoardDaoImpl.class) {
                boardDao = INSTANCE;
                if (boardDao == null) {
                    INSTANCE = boardDao = new BoardDaoImpl();
                }
            }
        }
        return boardDao;
    }

    @Override
    public List<Board> getAllBoards() throws SQLException {
        psGetAll = prepareStatement(getAllQuery);
        psGetAll.executeQuery();
        List<Board> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @Override
    public List<Board> getByHotel(long hotelId) throws SQLException {
        psGetAllByHotel = prepareStatement(getByHotelQuery);
        psGetAllByHotel.setLong(1, (long)hotelId);
        psGetAllByHotel.executeQuery();
        ResultSet rs = psGetAllByHotel.getResultSet();
        List<Board> list = new ArrayList<>();
        while (rs.next()) {
            list.add(fillEntity(rs));
        }
        close(rs);
        return list;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Board save(Board board) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, board.getName());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            board.setId(rs.getLong(1));
        }
        close(rs);
        return board;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Board get(long id) throws SQLException {
        psGet = prepareStatement(getQuery);
        psGet.setLong(1, id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return fillEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(Board board) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setString(1, board.getName());
        psUpdate.setLong(2, board.getId());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(long id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, id);
        return psDelete.executeUpdate();
    }

    private Board fillEntity(ResultSet rs) throws SQLException {
        Board entity = new Board();
            entity.setId(rs.getLong(1));
            entity.setName(rs.getString(2));
        return entity;
    }
}