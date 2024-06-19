package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;

public class FeedbackDAO extends DBContext {


    public List<Feedback> getAll() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getInt("userID"),
                        rs.getString("feedbackTopic"),
                        rs.getString("feedbackText"),
                        rs.getTimestamp("created_at")
                );
                list.add(f);
            }
        } catch (SQLException e) {
            // Consider better error handling (logging, custom exceptions)
            e.printStackTrace();
        }
        return list;
    }

    // Retrieve feedback by ID
    public Feedback getFeedbackById(int id) {
        String sql = "SELECT * FROM Feedback WHERE feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {

        }
        return null;
    }

    // Delete feedback by ID
    public void deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM Feedback WHERE feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, feedbackID);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // Search feedback by keyword
    public List<Feedback> searchFeedback(String keyword) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE feedbackID = ? OR feedbackTopic LIKE ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try {
                st.setInt(1, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                st.setInt(1, -1); // Handle non-integer input gracefully
            }
            String searchPattern = "%" + keyword + "%";
            st.setString(2, searchPattern);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at")
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Feedback f) {
        String sql = "INSERT INTO [dbo].[Feedback]\n"
                + "           ([userID]\n"
                + "           ,[feedbackTopic]\n"
                + "           ,[feedbackText]\n"
                + "           ,[created_at])\n"
                + "     VALUES(?,?,?,GETDATE())";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, f.getUserID());
            st.setString(2, f.getFeedbackTopic());
            st.setString(3, f.getFeedbackText());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Feedback> sortFeedback (String sortBy){
        List<Feedback> list = new ArrayList<>();
        List<String> allowedColumns = List.of("userID", "feedbackTopic", "created_at");

        if (!allowedColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort column: " + sortBy);
        }

        String sql = "SELECT * FROM Feedback ORDER BY " + sortBy;

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at")
                    );
                    list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return list;
    }
    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getAll();
        for (Feedback user : list) {
            System.out.println(user.getFeedbackText());
        }
    }
}
