package dal;

import static dal.DBUtils.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Users;


public class LoginDao extends DBContext {

    public Users login(String username, String password) {
    String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
    try (Connection connection = getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {

        st.setString(1, username);
        st.setString(2, password);

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
        e.printStackTrace(); // Properly handle exceptions
    }
    return null;
}


    public Users checkAccountExist(String user) {
        String sql = "select * from [dbo].[Users] where  username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users a = new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        rs.getBoolean(10));
                return a;
            }
        } catch (Exception e) {
            //nothing
        }
        return null;
    }

    public Users signup(Users c) {
        String sql = "INSERT INTO Users (username, email, [password], phone, [address], age) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, c.getUsername());
            st.setString(2, c.getEmail());
            st.setString(3, c.getPassword());
            st.setString(4, c.getPhone());
            st.setString(5, c.getAddress());
            st.setInt(6, c.getAge());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Log error or handle it appropriately
        }
        return c;
    }

    public Users getEmail(String email) {
    String sql = "SELECT * FROM [dbo].[Users] WHERE email = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, email);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Users user = new Users(
                    rs.getInt(1),          // userID
                    rs.getString(2),       // username
                    rs.getString(3),       // email
                    rs.getString(4),       // password
                    rs.getString(5),       // phone
                    rs.getString(6),       // address
                    rs.getInt(7),          // age
                    rs.getDate(8),         // createdAt
                    rs.getBoolean(9),      // premiumID
                    rs.getBoolean(10));    // role
            return user;
        }
    } catch (SQLException e) {
        e.printStackTrace();  // handle exception properly
    }
    return null;
}


    public static void main(String[] args) {
        LoginDao d = new LoginDao();
//        Users c = new Users(1,"Hieu","Hieu2003@","123","123","HN",12,false);
//        d.signup(c);
//
//        System.out.println(d.login("Hieu", "123"));
    }
}
