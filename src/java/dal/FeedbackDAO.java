package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Feedback;

public class FeedbackDAO extends DBContext {

    public FeedbackDAO() {
        super();
    }
    
    // Get feedback by ID
    public Feedback getFeedbackById(int id) {
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, u.username, f.rating, f.status, f.role "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE f.feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"), // Ensure username is selected
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete feedback by ID
    public void deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM Feedback WHERE feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, feedbackID);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No feedback found with ID " + feedbackID + " to delete.");
            } else {
                System.out.println("Feedback with ID " + feedbackID + " deleted successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting feedback with ID " + feedbackID + ": " + e.getMessage());
        }
    }

    // Insert new feedback
    public void insert(Feedback f) {
        String sql = "INSERT INTO Feedback (userID, feedbackTopic, feedbackText, created_at, rating) "
                + "VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, f.getUserID());
            st.setString(2, f.getFeedbackTopic());
            st.setString(3, f.getFeedbackText());
            st.setInt(4, f.getRating());
            st.executeUpdate();
            System.out.println("New feedback inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sort feedback entries by a specified column and order
    public List<Feedback> sortFeedback(String sortBy, String order, int offset, int limit) {
        List<Feedback> list = new ArrayList<>();
        List<String> validColumns = Arrays.asList("created_at", "rating");

        if (!validColumns.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort column: " + sortBy);
        }

        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, f.rating, f.status, f.role, u.username "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE f.status = 1 "
                + "ORDER BY " + sortBy + " " + order + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);  // Set offset
            ps.setInt(2, limit);   // Set limit

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"), // Ensure username is selected
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get all feedback entries
    public List<Feedback> getAllFeedback(int offset, int limit) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, f.rating, f.status, f.role, u.username "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "ORDER BY f.feedbackID DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);  // Set offset
            ps.setInt(2, limit);   // Set limit

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"), // Ensure username is selected
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Search feedback by keyword (in feedback text)
    public List<Feedback> searchFeedback(String keyword, String sortBy, String order, int offset, int limit) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, u.username, f.rating, f.status, f.role "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE (f.feedbackText LIKE ? OR f.feedbackTopic LIKE ? OR u.username LIKE ?) AND f.status = 1 "
                + "ORDER BY " + (sortBy != null ? sortBy : "f.created_at") + " " + (order != null ? order : "DESC")
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ps.setInt(4, offset);
            ps.setInt(5, limit);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"),
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public int countSearchFeedback(String keyword) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE (f.feedbackText LIKE ? OR f.feedbackTopic LIKE ? OR u.username LIKE ?) AND f.status = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalFeedback() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM Feedback WHERE status = 1";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Feedback> getPaginatedFeedback(int offset, int limit) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, u.username, f.rating, f.status, f.role "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "ORDER BY f.feedbackID DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"), // Ensure username is selected
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public void approveFeedback(int feedbackID) {
        String sql = "UPDATE Feedback SET status = 1 WHERE feedbackID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, feedbackID);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No feedback found with ID " + feedbackID + " to approve.");
            } else {
                System.out.println("Feedback with ID " + feedbackID + " approved successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error approving feedback with ID " + feedbackID + ": " + e.getMessage());
        }
    }

    public List<Feedback> getApprovedFeedback(int offset, int limit) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, u.username, f.rating, f.status, f.role "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE f.status = 1 "
                + "ORDER BY f.feedbackID DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"), // Ensure username is selected
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    list.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getFilteredFeedback(String keyword, String status, String rating, String sortBy, String order, int offset, int limit) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT f.feedbackID, f.userID, f.feedbackTopic, f.feedbackText, f.created_at, u.username, f.rating, f.status, f.role "
                + "FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE (f.feedbackTopic LIKE ? OR f.feedbackText LIKE ? OR u.username LIKE ?) "
                + (status != null && !status.isEmpty() ? " AND f.status = ? " : " AND f.status = 1 ")
                + (rating != null && !rating.isEmpty() ? " AND f.rating = ? " : "")
                + "ORDER BY " + (sortBy != null && !sortBy.isEmpty() ? sortBy : "f.created_at") + " " + (order != null && !order.isEmpty() ? order : "DESC")
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            ps.setString(paramIndex++, "%" + keyword + "%");
            ps.setString(paramIndex++, "%" + keyword + "%");
            ps.setString(paramIndex++, "%" + keyword + "%");

            if (status != null && !status.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(status));
            }
            if (rating != null && !rating.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(rating));
            }

            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, limit);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback feedback = new Feedback(
                            rs.getInt("feedbackID"),
                            rs.getInt("userID"),
                            rs.getString("feedbackTopic"),
                            rs.getString("feedbackText"),
                            rs.getTimestamp("created_at"),
                            rs.getString("username"),
                            rs.getInt("rating"),
                            rs.getInt("status"),
                            rs.getInt("role")
                    );
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    // Get total feedback count with filters
    public int getTotalFilteredFeedback(String keyword, String status, String rating) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM Feedback f "
                + "JOIN Users u ON f.userID = u.userID "
                + "WHERE (f.feedbackTopic LIKE ? OR f.feedbackText LIKE ? OR u.username LIKE ?) "
                + (status != null && !status.isEmpty() ? " AND f.status = ? " : " AND f.status = 1 ")
                + (rating != null && !rating.isEmpty() ? " AND f.rating = ? " : "");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int paramIndex = 1;
            ps.setString(paramIndex++, "%" + keyword + "%");
            ps.setString(paramIndex++, "%" + keyword + "%");
            ps.setString(paramIndex++, "%" + keyword + "%");

            if (status != null && !status.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(status));
            }
            if (rating != null && !rating.isEmpty()) {
                ps.setInt(paramIndex++, Integer.parseInt(rating));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}