/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO extends DBUtils {
   public User getUserByUP(String email, String password) {
    try {
        String sql = "SELECT * FROM [dbo].[Users] where email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setUserID(rs.getString("userID"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setAddress(rs.getString("address"));
            user.setAge(rs.getString("age"));
           

        }
        return user;
    } catch (SQLException ex) {
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        String email = "son2k3@gmail.com";
        String password = "123456";
        User s = dao.getUserByUP(email, password);
        System.out.println(s);
    }

    public void changePassword(String email, String newPassword) {
        try {
            String sql = "UPDATE [dbo].[Users] SET password = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void updatePasswordByEmail(String email, String newPassword) {
        try {
           String sql = "UPDATE users\n"
                    + "SET password = '" + newPassword
                    + "'WHERE email = '" + email + "'";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
         public User updatePasswordByEmail() {
        throw new UnsupportedOperationException("Not supported yet."); 
}  

    private static class connection {

        private static PreparedStatement prepareStatement(String sql) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public connection() {
        }
    }
 
}
