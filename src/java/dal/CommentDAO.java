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
import model.Comment;

public class CommentDAO extends DBContext {

    public List<Comment> getAll() {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM Comment";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("commentID"),
                        rs.getInt("userID"),
                        rs.getInt("postID"),
                        rs.getString("commentText"),
                        rs.getDate("dateCommented")
                );
                list.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public Comment getById(int id) {
        String sql = "SELECT * FROM Comment WHERE commentID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Comment(
                            rs.getInt("commentID"),
                            rs.getInt("userID"),
                            rs.getInt("postID"),
                            rs.getString("commentText"),
                            rs.getDate("dateCommented")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getById: " + e.getMessage());
        }
        return null;
    }

    public void insert(Comment comment) {
        String sql = "INSERT INTO Comment (userID, postID, commentText) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, comment.getUserID());
            st.setInt(2, comment.getPostID());
            st.setString(3, comment.getCommentText());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert: " + e.getMessage());
        }
    }

    public void update(Comment comment) {
        String sql = "UPDATE Comment SET userID = ?, postID = ?, commentText = ? WHERE commentID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, comment.getUserID());
            st.setInt(2, comment.getPostID());
            st.setString(3, comment.getCommentText());
            st.setInt(4, comment.getCommentID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
        }
    }

    public void delete(int postID) {
        String sql = "DELETE FROM Comment WHERE commentID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, postID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
    }
}


