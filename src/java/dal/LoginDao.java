package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Users;

public class LoginDao extends DBContext {

    public Users login(String user, String pass) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE username = ? AND [password] = ?";
        try {
            // Assuming 'connection' is a valid Connection object
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
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
        String sql = "select * from [dbo].[Users] where  email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);

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
                        rs.getBoolean(9));
                rs.getBoolean(10);
                return a;
            }
        } catch (Exception e) {
            //nothing
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
