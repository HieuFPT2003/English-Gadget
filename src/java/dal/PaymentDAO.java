/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Payments;
import model.Users;

/**
 *
 * @author khanh
 */
public class PaymentDAO extends DBContext {

    public Payments signup(Payments c) {
        String sql = "INSERT INTO Payments (userId, username, content,payment_date,status) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, c.getUserId());
            st.setString(2, c.getUsername());
            st.setString(3, c.getContent());
            st.setDate(4, new java.sql.Date(c.getDate().getTime()));
            st.setBoolean(5, c.isStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Log error or handle it appropriately
        }
        return c;
    }
 public void deletePaymentByContent(String content) throws SQLException {
    String sql = "DELETE FROM Payments WHERE content = ?";
    try (PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, content);
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        throw e; // Rethrow the exception to handle it appropriately
    }
}

    public List<Payments> getAll() {
        String sql = "SELECT * FROM Payments";
        List<Payments> paymentsList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Payments payment = new Payments(rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("content"),
                        rs.getDate("payment_date"),
                        rs.getBoolean("status"));
                paymentsList.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paymentsList;
    }
    public Payments getUsersById(int userID) {
        String sql = "SELECT * FROM Payments WHERE userId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Payments(rs.getInt(1),
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getDate(4), 
                            rs.getBoolean(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     public Payments getContent(String content) {
        String sql = "SELECT * FROM Payments WHERE content = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, content);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Payments(rs.getInt(1),
                            rs.getString(2), 
                            rs.getString(3), 
                            rs.getDate(4), 
                            rs.getBoolean(5));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Payments> getPaymentsByUserId(int userID) {
        String sql = "SELECT * FROM Payments WHERE userId = ?";
        List<Payments> paymentsList = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Payments payment = new Payments(rs.getInt("userId"),
                            rs.getString("username"),
                            rs.getString("content"),
                            rs.getDate("payment_date"),
                            rs.getBoolean("status"));
                    paymentsList.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log error or handle it appropriately
        }
        return paymentsList;
    }
   

    public List<Payments> searchPaymentsByContent(String content) {
        String sql = "SELECT * FROM Payments WHERE content LIKE ?";
        List<Payments> paymentsList = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, content);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Payments payment = new Payments(rs.getInt("userId"),
                            rs.getString("username"),
                            rs.getString("content"),
                            rs.getDate("payment_date"),
                            rs.getBoolean("status"));
                    paymentsList.add(payment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log error or handle it appropriately
        }
        return paymentsList;
    }
   


}
