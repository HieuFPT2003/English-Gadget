/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Users;

/**
 *
 * @author khanh
 */
public class ChangePasswordDao extends DBContext{
    public Users getUsersById(int userID) {
        String sql = "SELECT * FROM Users WHERE userID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Users(
                            rs.getInt("userID"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getInt("age"),
                            rs.getDate("created_at"),
                            rs.getBoolean("premiumID"),
                            rs.getBoolean("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
    
    public void updatePasswordUser(int userId, String password) {
        String sql = "UPDATE Users SET password = ? WHERE userID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, password);
            st.setInt(2, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
