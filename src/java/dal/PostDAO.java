package dal;

import model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class PostDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Post> getAllPost() {
        String sql = "SELECT p.postID, "
                + "       p.userID, "
                + "       u.username AS customerName, "
                + "       p.postText, "
                + "       p.datePosted, "
                + "       p.category, "
                + "       p.status "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.status = 1";  // Fetch posts with status = 0 (pending)

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (rs.next()) {
                int postID = rs.getInt("postID");
                int userID = rs.getInt("userID");
                String customerName = rs.getString("customerName");
                String postText = rs.getString("postText");
                Date datePosted = rs.getTimestamp("datePosted");
                String category = rs.getString("category");
                boolean status = rs.getBoolean("status");

                // Create a Post object with likeCount and dislikeCount as 0
                Post post = new Post(postID, userID, customerName, postText, datePosted, 0, 0, false, category, status);
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Post> getAllPostsForAdmin() {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.likeCount, p.dislikeCount, p.Edited, p.category, p.status, u.username AS customerName "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.status = 0"
                + "ORDER BY p.datePosted DESC";

        List<Post> posts = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setPostID(rs.getInt("postID"));
                    post.setUserID(rs.getInt("userID"));
                    post.setPostText(rs.getString("postText"));
                    post.setDatePosted(rs.getTimestamp("datePosted"));
                    post.setLikeCount(rs.getInt("likeCount"));
                    post.setDislikeCount(rs.getInt("dislikeCount"));
                    post.setEdited(rs.getBoolean("Edited"));
                    post.setCategory(rs.getString("category"));
                    post.setStatus(rs.getBoolean("status"));
                    post.setCustomerName(rs.getString("customerName"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return posts;
    }

    public List<Post> getAllPostExceptUserID(int userIDToExclude) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.Edited, p.category, p.status, u.username AS customerName "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID <> ? AND p.status = 0 "
                + "ORDER BY p.datePosted DESC";

        List<Post> posts = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userIDToExclude);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setPostID(rs.getInt("postID"));
                    post.setUserID(rs.getInt("userID"));
                    post.setPostText(rs.getString("postText"));
                    post.setDatePosted(rs.getTimestamp("datePosted"));
                    post.setEdited(rs.getBoolean("Edited"));
                    post.setCustomerName(rs.getString("customerName"));
                    post.setCategory(rs.getString("category"));
                    post.setStatus(rs.getBoolean("status"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public boolean insertPost(Post post) {
        String sql = "INSERT INTO UserPost (userID, postText, datePosted, category, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, post.getUserID());
            ps.setString(2, post.getPostText());
            ps.setTimestamp(3, new java.sql.Timestamp(post.getDatePosted().getTime()));
            ps.setString(4, post.getCategory());
            ps.setBoolean(5, post.isStatus()); // Chèn trạng thái (pending approval)

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Post> getPost(int userID) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.Edited, p.category, p.status, u.username AS customerName "
                + "FROM UserPost p JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID = ? AND p.status = 0";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            List<Post> posts = new ArrayList<>();

            while (rs.next()) {
                Post post = new Post();
                post.setPostID(rs.getInt("postID"));
                post.setUserID(rs.getInt("userID"));
                post.setPostText(rs.getString("postText"));
                post.setDatePosted(rs.getTimestamp("datePosted"));
                post.setEdited(rs.getBoolean("Edited"));
                post.setCustomerName(rs.getString("customerName"));
                post.setCategory(rs.getString("category"));
                post.setStatus(rs.getBoolean("status"));
                posts.add(post);
            }
            return posts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPostByPostID(int postID) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.Edited, p.category, p.status, u.username AS customerName "
                + "FROM UserPost p JOIN Users u ON p.userID = u.userID "
                + "WHERE p.postID = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, postID);
            rs = ps.executeQuery();
            Post post = new Post();

            while (rs.next()) {
                post.setPostID(rs.getInt("postID"));
                post.setUserID(rs.getInt("userID"));
                post.setPostText(rs.getString("postText"));
                post.setDatePosted(rs.getTimestamp("datePosted"));
                post.setEdited(rs.getBoolean("Edited"));
                post.setCustomerName(rs.getString("customerName"));
                post.setCategory(rs.getString("category"));
                post.setStatus(rs.getBoolean("status"));
            }
            return post;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertUserPost(int userID, String postText, String category) {
        String sql = "INSERT INTO UserPost (userID, postText, category, status) VALUES (?, ?, ?, 0)";  // Default status is 0 (pending)

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, postText);
            ps.setString(3, category);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updatePostStatus(int postID, int status) {
        String sql = "UPDATE UserPost SET status = ? WHERE postID = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, postID);

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Post> searchPostByText(String txtSearch) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT p.postID, p.userID, u.username AS customerName, p.postText, p.datePosted, p.category, p.status "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.postText LIKE ? AND p.status = 0";  // Fetch posts with status = 0 (pending)

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("postID");
                int userID = rs.getInt("userID");
                String customerName = rs.getString("customerName");
                String postText = rs.getString("postText");
                Date datePosted = rs.getTimestamp("datePosted");
                String category = rs.getString("category");
                boolean status = rs.getBoolean("status");

                Post post = new Post(postID, userID, customerName, postText, datePosted, 0, 0, false, category, status);
                list.add(post);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean deletePost(int postID) {
        String sql = "DELETE FROM UserPost WHERE postID = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, postID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Post> getPostByUserID(int userID) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.likeCount, p.dislikeCount, p.Edited,p.category,p.status, u.username AS customerName "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID = ? "
                + "ORDER BY p.datePosted DESC";

        List<Post> posts = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setPostID(rs.getInt("postID"));
                    post.setUserID(rs.getInt("userID"));
                    post.setPostText(rs.getString("postText"));
                    post.setDatePosted(rs.getTimestamp("datePosted"));
                    post.setLikeCount(rs.getInt("likeCount"));
                    post.setDislikeCount(rs.getInt("dislikeCount"));
                    post.setEdited(rs.getBoolean("Edited"));
                    post.setStatus(rs.getBoolean("status"));
                    post.setCategory(rs.getString("category"));
                    post.setCustomerName(rs.getString("customerName"));
                    posts.add(post);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return posts;
    }

    public double getSimilarity(String text1, String text2) {
        text1 = text1.toLowerCase();
        text2 = text2.toLowerCase();

        int distance = StringUtils.getLevenshteinDistance(text1, text2);

        double similarity = 1.0 - ((double) distance / (double) Math.max(text1.length(), text2.length()));

        return similarity;
    }

    public boolean updatePost(int postID, String category, String postText) {
        String sql = "UPDATE UserPost  SET category = ?, postText = ? WHERE postID = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, postText);
            ps.setInt(3, postID);
            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        PostDAO dao = new PostDAO();

//        List<Post> posts = dao.getPostByUserID(19);
//        for (Post p : posts) {
//            System.out.println(p);
//        }
        boolean test = dao.updatePostStatus(1009, 1);
        System.out.println(test);
    }
}
