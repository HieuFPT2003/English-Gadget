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
import model.Administrator;

public class AdministratorDAO extends DBContext {

    public List<Administrator> getAll() {
        List<Administrator> list = new ArrayList<>();
        String sql = "SELECT * FROM Administrator";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Administrator admin = new Administrator(
                        rs.getInt("adminID"),
                        rs.getString("adminName"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("userID")
                );
                list.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public Administrator getById(int id) {
        String sql = "SELECT * FROM Administrator WHERE adminID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Administrator(
                            rs.getInt("adminID"),
                            rs.getString("adminName"),
                            rs.getInt("age"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("userID")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getById: " + e.getMessage());
        }
        return null;
    }

    public void insert(Administrator admin) {
        String sql = "INSERT INTO Administrator (adminName, age, email, phone, userID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, admin.getAdminName());
            st.setInt(2, admin.getAge());
            st.setString(3, admin.getEmail());
            st.setString(4, admin.getPhone());
            st.setInt(5, admin.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert: " + e.getMessage());
        }
    }

    public void update(Administrator admin) {
        String sql = "UPDATE Administrator SET adminName = ?, age = ?, email = ?, phone = ?, userID = ? WHERE adminID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, admin.getAdminName());
            st.setInt(2, admin.getAge());
            st.setString(3, admin.getEmail());
            st.setString(4, admin.getPhone());
            st.setInt(5, admin.getUserID());
            st.setInt(6, admin.getAdminID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
        }
    }

    public void delete(int adminID) {
        String sql = "DELETE FROM Administrator WHERE adminID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, adminID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
    }
}
