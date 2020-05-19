package dataaccess.impl.mysql;

import com.google.inject.Inject;
import dataaccess.domain.Contact;

import java.sql.*;
import java.util.HashMap;

public class MySqlConnectionHelper {

    private MySqlConnectionInfo connectionInfo;
    private Connection conn;

    @Inject
    public MySqlConnectionHelper(MySqlConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public Contact execute(String sql, HashMap<Integer, Object> parameters) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Contact contact = null;
        try {

            stmt = getConnection().prepareStatement(sql);

            for (int key : parameters.keySet()) {
                Object value = parameters.get(key);
                if (value instanceof String) {
                    stmt.setString(key, (String)value);
                } else if (value instanceof Integer) {
                    stmt.setInt(key, (Integer)value);
                } else if (value instanceof Boolean) {
                    stmt.setBoolean(key, (Boolean)value);
                }
            }

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                contact = new Contact();
                while (rs.next()) {
                    contact.setId(rs.getInt("id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setAddress(rs.getString("address"));
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return contact;
    }


    private Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName(connectionInfo.getDriver()).newInstance();
                conn = DriverManager.getConnection(
                        connectionInfo.getUrl(),
                        connectionInfo.getUser(),
                        connectionInfo.getPassword());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
