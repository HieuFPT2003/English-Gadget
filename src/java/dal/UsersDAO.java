package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Users;

public class UsersDAO extends DBContext {

    public List<Users> getAll() {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Users u = new Users(
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
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return list;
    }

    public void insert(Users u) {
        String sql = """
                     INSERT INTO Users
                                (username, email, password, phone, address, age, created_at, premiumID, role)
                          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getPhone());
            st.setString(5, u.getAddress());
            st.setInt(6, u.getAge());
            st.setDate(7, new java.sql.Date(u.getCreatedAt().getTime()));
            st.setBoolean(8, u.isPremiumID());
            st.setBoolean(9, u.isRole());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

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
            e.printStackTrace(); // Replace with proper logging
        }
        return null;
    }

    public void delete(int userID) {
        String sql = """
                     DELETE FROM Payments WHERE userID = ?;
                     DELETE FROM Comment WHERE postID IN (SELECT postID FROM UserPost WHERE userID = ?);
                     DELETE FROM Emotion WHERE postID IN (SELECT postID FROM UserPost WHERE userID = ?);
                     DELETE FROM UserPost WHERE userID = ?;
                     DELETE FROM Emotion WHERE userID = ?;
                     DELETE FROM CheckHistory WHERE userID = ?;
                     DELETE FROM Feedback WHERE userID = ?;
                     DELETE FROM Users WHERE userID = ?;""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 8; i++) {
                st.setInt(i, userID);
            }
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

    public void update(Users u) {
        String sql = "UPDATE Users SET username = ?, email = ?, password = ?, phone = ?, address = ?, age = ?, created_at = ?, premiumID = ?, role = ? WHERE userID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getPhone());
            st.setString(5, u.getAddress());
            st.setInt(6, u.getAge());
            st.setDate(7, new java.sql.Date(u.getCreatedAt().getTime()));
            st.setBoolean(8, u.isPremiumID());
            st.setBoolean(9, u.isRole());
            st.setInt(10, u.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Đảm bảo rằng có xử lý ngoại lệ và ghi log phù hợp
        }
    }

    public List<Users> sortUsers(String sortBy) {
        List<Users> list = new ArrayList<>();
        List<String> allowedColumns = List.of("username", "email", "created_at", "age");

        if (!allowedColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort column: " + sortBy);
        }

        String sql = "SELECT * FROM Users ORDER BY " + sortBy;

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Users u = new Users(
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
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return list;
    }

    public List<Users> searchUsers(String keyword) {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE userID = ? OR username LIKE ? OR email LIKE ? OR phone LIKE ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try {
                st.setInt(1, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                st.setInt(1, -1);
            }
            String searchPattern = "%" + keyword + "%";
            st.setString(2, searchPattern);
            st.setString(3, searchPattern);
            st.setString(4, searchPattern);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Users u = new Users(
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
                    list.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return list;
    }
}
