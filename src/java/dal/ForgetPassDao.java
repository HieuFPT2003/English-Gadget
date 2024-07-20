/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Users;

/**
 *
 * @author khanh
 */
public class ForgetPassDao extends DBContext{
     public Users CheckMail(String user) {
        String sql = "select * from [dbo].[Users] where  email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users a = new Users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(9),
                        rs.getBoolean(10));
                return a;
            }
        } catch (Exception e) {
            //nothing
        }
        return null;
     }
     
      public void updatePassword(String email, String password) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [password] = ?\n"
                + " WHERE [email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
