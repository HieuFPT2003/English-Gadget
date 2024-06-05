package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Users;
import model.Feedbacks;

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
                        rs.getBoolean("premiumID")
                );
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
        }
        return list;
    }

    public void insert(Users u) {
        String sql = """
                     INSERT INTO [dbo].[Users]
                                ([userID], [username], [email], [password], [phone], [address], [age], [created_at], [premiumID])
                          VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, u.getUserID());
            st.setString(2, u.getUsername());
            st.setString(3, u.getEmail());
            st.setString(4, u.getPassword());
            st.setString(5, u.getPhone());
            st.setString(6, u.getAddress());
            st.setInt(7, u.getAge());
            st.setDate(8, new java.sql.Date(u.getCreatedAt().getTime()));
            st.setBoolean(9, u.isPremiumID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
        }
    }

    public Users getUsersById(int id) {
        String sql = "SELECT * FROM Users WHERE userID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
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
                            rs.getBoolean("premiumID")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
        }
        return null;
    }

    public void delete(int userID) throws SQLException {
        String sql
                = "DELETE FROM Payments WHERE userID = ?; "
                + "DELETE FROM Comment WHERE postID IN (SELECT postID FROM UserPost WHERE userID = ?); "
                + "DELETE FROM UserPost WHERE userID = ?; "
                + "DELETE FROM Users WHERE userID = ?;";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            st.setInt(2, userID);
            st.setInt(3, userID);
            st.setInt(4, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // hoặc log lỗi
            throw new SQLException("Error deleting user: " + e.getMessage(), e);
        }
    }

    public void update(Users u) {
        String sql = """
                     UPDATE [dbo].[Users]
                        SET [username] = ?, [email] = ?, [password] = ?, [phone] = ?, [address] = ?, [age] = ?, [created_at] = ?, [premiumID] = ?
                        WHERE [userID] = ?""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, u.getUsername());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPassword());
            st.setString(4, u.getPhone());
            st.setString(5, u.getAddress());
            st.setInt(6, u.getAge());
            st.setDate(7, new java.sql.Date(u.getCreatedAt().getTime()));
            st.setBoolean(8, u.isPremiumID());
            st.setInt(9, u.getUserID());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
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
                        rs.getBoolean("premiumID")
                );
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
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
                            rs.getBoolean("premiumID")
                    );
                    list.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
        }
        return list;
    }

    public List<Feedbacks> getAllFeedback() {
        List<Feedbacks> listfb = new ArrayList<>();
        String sql = "select * from userFeedback";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Feedbacks f = new Feedbacks(
                        rs.getInt("feedbackID"),
                        rs.getInt("userID"),
                        rs.getString("feedbackText"),
                        rs.getString("userImage")
                );
                listfb.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better exception handling
        }
        return listfb;
    }

    public static void main(String[] args) {
        UsersDAO dao = new UsersDAO();
        List<Users> list = dao.getAll();
        for (Users user : list) {
            System.out.println(user.getUsername());
        }
        List<Feedbacks> listfb = dao.getAllFeedback();
        for (Feedbacks fb : listfb) {
            System.out.println(fb);

    }
}
}