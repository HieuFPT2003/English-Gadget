package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Users;

public class UsersDAO extends DBContext {

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
        String sql = "SELECT * FROM Users WHERE userID = ? AND role = 0";
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

    public void delete(int userID) {
        String sql = """
                     DELETE FROM Payments WHERE userID = ?;
                     DELETE FROM Comment WHERE postID IN (SELECT postID FROM UserPost WHERE userID = ?);
                     DELETE FROM Emotion WHERE postID IN (SELECT postID FROM UserPost WHERE userID = ?);
                     DELETE FROM UserPost WHERE userID = ?;
                     DELETE FROM Emotion WHERE userID = ?;
                     DELETE FROM CheckHistory WHERE userID = ?;
                     DELETE FROM Feedback WHERE userID = ?;
                     DELETE FROM Users WHERE userID = ? AND role = 0;""";
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
        String sql = "UPDATE Users SET username = ?, email = ?, password = ?, phone = ?, address = ?, age = ?, created_at = ?, premiumID = ?, role = ? WHERE userID = ? AND role = 0";
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
            e.printStackTrace(); // Replace with proper logging
        }
    }

    public List<Users> sortUsers(String sortBy, int offset, int limit) {
        List<Users> list = new ArrayList<>();
        List<String> allowedColumns = List.of("username", "email", "created_at", "age");

        if (!allowedColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort column: " + sortBy);
        }

        String sql = "SELECT * FROM Users WHERE role = 0 ORDER BY " + sortBy + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, offset);
            st.setInt(2, limit);
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

    public List<Users> searchUsers(String keyword, int offset, int limit) {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE role = 0 AND (userID = ? OR username LIKE ? OR email LIKE ? OR phone LIKE ?) ORDER BY userID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

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
            st.setInt(5, offset);
            st.setInt(6, limit);

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

    public int getTotalUserCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Users WHERE role = 0";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return count;
    }

    public List<Users> getPaginatedUsers(int offset, int limit) {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE role = 0 ORDER BY userID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, offset);
            st.setInt(2, limit);
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
