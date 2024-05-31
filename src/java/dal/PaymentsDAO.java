/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author nookh
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Payments;

public class PaymentsDAO extends DBContext {

    public List<Payments> getAll() {
        List<Payments> list = new ArrayList<>();
        String sql = "SELECT * FROM Payments";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Payments payment = new Payments(
                        rs.getInt("paymentID"),
                        rs.getInt("userID"),
                        rs.getInt("planID"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date"),
                        rs.getDate("expiry_date")
                );
                list.add(payment);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public Payments getById(int id) {
        String sql = "SELECT * FROM Payments WHERE paymentID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Payments(
                            rs.getInt("paymentID"),
                            rs.getInt("userID"),
                            rs.getInt("planID"),
                            rs.getDouble("amount"),
                            rs.getDate("payment_date"),
                            rs.getDate("expiry_date")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getById: " + e.getMessage());
        }
        return null;
    }

    public void insert(Payments payment) {
        String sql = """
                     INSERT INTO Payments
                                (paymentID, userID, planID, amount, payment_date, expiry_date)
                          VALUES (?, ?, ?, ?, ?, ?)""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, payment.getPaymentID());
            st.setInt(2, payment.getUserID());
            st.setInt(3, payment.getPlanID());
            st.setDouble(4, payment.getAmount());
            st.setDate(5, new java.sql.Date(payment.getPaymentDate().getTime()));
            st.setDate(6, new java.sql.Date(payment.getExpiryDate().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert: " + e.getMessage());
        }
    }

    public void update(Payments payment) {
        String sql = """
                     UPDATE Payments
                        SET userID = ?, planID = ?, amount = ?, payment_date = ?, expiry_date = ?
                        WHERE paymentID = ?""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, payment.getUserID());
            st.setInt(2, payment.getPlanID());
            st.setDouble(3, payment.getAmount());
            st.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            st.setDate(5, new java.sql.Date(payment.getExpiryDate().getTime()));
            st.setInt(6, payment.getPaymentID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
        }
    }

    public void delete(int userID) {
        String sql = "DELETE FROM Payments WHERE paymentID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PaymentsDAO dao = new PaymentsDAO();
        List<Payments> list = dao.getAll();
        for (Payments user : list) {
            System.out.println(user.getPaymentID());
}
    }
}

