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
import model.UserPost;

public class UserPostDAO extends DBContext {

    public List<UserPost> getAll() {
        List<UserPost> list = new ArrayList<>();
        String sql = "SELECT * FROM UserPost";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                UserPost post = new UserPost(
                        rs.getInt("postID"),
                        rs.getInt("userID"),
                        rs.getString("postText"),
                        rs.getDate("datePosted")
                );
                list.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public UserPost getById(int id) {
        String sql = "SELECT * FROM UserPost WHERE postID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new UserPost(
                            rs.getInt("postID"),
                            rs.getInt("userID"),
                            rs.getString("postText"),
                            rs.getDate("datePosted")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getById: " + e.getMessage());
        }
        return null;
    }

    public void insert(UserPost post) {
        String sql = "INSERT INTO UserPost (userID, postText) VALUES (?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, post.getUserID());
            st.setString(2, post.getPostText());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert: " + e.getMessage());
        }
    }

    public void update(UserPost post) {
        String sql = "UPDATE UserPost SET userID = ?, postText = ? WHERE postID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, post.getUserID());
            st.setString(2, post.getPostText());
            st.setInt(3, post.getPostID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
        }
    }

    public void delete(int userID) {
        String sql = "DELETE FROM UserPost WHERE postID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
    }
}
