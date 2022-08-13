package model.dao;

import util.JDBCUtilities;

import java.sql.*;
import java.util.ArrayList;


public class InformesDAO {
    public static ArrayList<String> querys = new ArrayList<String>();
    public static ResultSet listar(int indexQuery){
        ResultSet set;
        try {
            Connection bConn = JDBCUtilities.getConnection();
            PreparedStatement statement = bConn.prepareStatement(querys.get(indexQuery));
            set = statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return set;
    }

}
