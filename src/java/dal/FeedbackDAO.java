package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;

public class FeedbackDAO extends DBContext {

    public FeedbackDAO() { 
        super();
    }

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
                        rs.getDate("created_at"),
                        rs.getString("username"),
                        rs.getString("role") 
                );
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

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
                            rs.getDate("created_at"),
                            rs.getString("username"),
                            rs.getString("role") // Get role from ResultSet
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM Feedback WHERE feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, feedbackID);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> searchFeedback(String keyword) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM Feedback WHERE feedbackID = ? OR feedbackTopic LIKE ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            try {
                st.setInt(1, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                st.setInt(1, -1);
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
                            rs.getDate("created_at"),
                            rs.getString("username"),
                            rs.getString("role") // Get role from ResultSet
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
        String sql = "INSERT INTO Feedback (userID, feedbackTopic, feedbackText, created_at, role) "
                + "VALUES (?, ?, ?, GETDATE(), ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, f.getUserID());
            st.setString(2, f.getFeedbackTopic());
            st.setString(3, f.getFeedbackText());
            st.setString(4, f.getRole()); // Set role
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> sortFeedback(String sortBy) {
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
                        rs.getDate("created_at"),
                        rs.getString("username"),
                        rs.getString("role") // Get role from ResultSet
                );
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getAllHaveName() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, u.username, f.feedbackTopic, f.feedbackText, f.created_at, u.role " +
                     "FROM Feedback f " +
                     "JOIN Users u ON f.userID = u.userID";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getInt("userID"),
                        rs.getString("feedbackTopic"),
                        rs.getString("feedbackText"),
                        rs.getDate("created_at"),
                        rs.getString("username"),
                        rs.getString("role") 
                );
                list.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return list;
    }

    public List<Feedback> searchUserFeedback(String keyword) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, u.username, f.feedbackTopic, f.feedbackText, f.created_at, u.role " +
                     "FROM Feedback f " +
                     "JOIN Users u ON f.userID = u.userID " +
                     "WHERE u.username = ? OR f.feedbackTopic LIKE ? ";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, keyword);
            String searchPattern = "%" + keyword + "%";
            st.setString(2, searchPattern);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getDate("created_at"),
                            rs.getString("username"),
                            rs.getString("role") // Get role from ResultSet
                    );
                    list.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
