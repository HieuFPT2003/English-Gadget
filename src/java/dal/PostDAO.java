package dal;

import model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
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
                + "       ISNULL(likeCountTable.likeCount, 0) AS likeCount, "
                + "       ISNULL(dislikeCountTable.dislikeCount, 0) AS dislikeCount, "
                + "       ISNULL(likeUsers.userIDs, '') AS likeUserIDs, "
                + "       ISNULL(dislikeUsers.userIDs, '') AS dislikeUserIDs "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "LEFT JOIN ( "
                + "    SELECT postID, COUNT(*) AS likeCount "
                + "    FROM Emotion "
                + "    WHERE emotionType = 'like' "
                + "    GROUP BY postID "
                + ") AS likeCountTable ON p.postID = likeCountTable.postID "
                + "LEFT JOIN ( "
                + "    SELECT postID, COUNT(*) AS dislikeCount "
                + "    FROM Emotion "
                + "    WHERE emotionType = 'dislike' "
                + "    GROUP BY postID "
                + ") AS dislikeCountTable ON p.postID = dislikeCountTable.postID "
                + "LEFT JOIN ( "
                + "    SELECT postID, STRING_AGG(userID, ', ') AS userIDs "
                + "    FROM Emotion "
                + "    WHERE emotionType = 'like' "
                + "    GROUP BY postID "
                + ") AS likeUsers ON p.postID = likeUsers.postID "
                + "LEFT JOIN ( "
                + "    SELECT postID, STRING_AGG(userID, ', ') AS userIDs "
                + "    FROM Emotion "
                + "    WHERE emotionType = 'dislike' "
                + "    GROUP BY postID "
                + ") AS dislikeUsers ON p.postID = dislikeUsers.postID;";

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
                int likeCount = rs.getInt("likeCount");
                int dislikeCount = rs.getInt("dislikeCount");

                List<Integer> likeUserIDs = new ArrayList<>();
                List<Integer> dislikeUserIDs = new ArrayList<>();

                String likeUserIDsStr = rs.getString("likeUserIDs");
                if (!likeUserIDsStr.isEmpty()) {
                    Arrays.stream(likeUserIDsStr.split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .forEach(likeUserIDs::add);
                }

                String dislikeUserIDsStr = rs.getString("dislikeUserIDs");
                if (!dislikeUserIDsStr.isEmpty()) {
                    Arrays.stream(dislikeUserIDsStr.split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .forEach(dislikeUserIDs::add);
                }

                Post post = new Post(postID, userID, customerName, postText, datePosted, likeCount, dislikeCount);
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

    public List<Post> getAllPostExceptUserID(int userIDToExclude) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.likeCount, p.dislikeCount, p.Edited, u.username AS customerName "
                + "FROM UserPost p "
                + "JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID <> ? "
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
                    post.setLikeCount(rs.getInt("likeCount"));
                    post.setDislikeCount(rs.getInt("dislikeCount"));
                    post.setEdited(rs.getBoolean("Edited"));
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

    public List getPost(int userID) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.Edited, u.username AS customerName "
                + "FROM UserPost p JOIN Users u ON p.userID = u.userID "
                + "WHERE p.userID = " + userID;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
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
                posts.add(post);
            }
            return posts;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Post getPostByPostID(int postID) {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, p.Edited, u.username AS customerName "
                + "FROM UserPost p JOIN Users u ON p.userID = u.userID "
                + "WHERE p.postID = " + postID;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Post post = new Post();

            while (rs.next()) {
                post.setPostID(rs.getInt("postID"));
                post.setUserID(rs.getInt("userID"));
                post.setPostText(rs.getString("postText"));
                post.setDatePosted(rs.getTimestamp("datePosted"));
                post.setEdited(rs.getBoolean("Edited"));
                post.setCustomerName(rs.getString("customerName"));
            }
            return post;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePost(int userID, int postID, String postText) {
        String sql = "UPDATE UserPost SET postText = ?, Edited = 1 Where postID = ? AND userID = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, postText);
            ps.setInt(2, postID);
            ps.setInt(3, userID);

            int row = ps.executeUpdate();

            if (row > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getSimilarity(String text1, String text2) {
        text1 = text1.toLowerCase();
        text2 = text2.toLowerCase();

        int distance = StringUtils.getLevenshteinDistance(text1, text2);

        double similarity = 1.0 - ((double) distance / (double) Math.max(text1.length(), text2.length()));

        return similarity;
    }

    public boolean insertUserPost(int userID, String postText) {
        String sql = "INSERT INTO UserPost (userID, postText) VALUES (?, ?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, postText);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
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

    public boolean deletePost(int userID, int postID) {
        String sql = "DELETE FROM UserPost WHERE postID = ? AND userID = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, postID);
            ps.setInt(2, userID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        PostDAO dao = new PostDAO();
//        Post post = dao.getPostByPostID(1);
//        List<Post> posts = dao.getAllPostExceptUserID(1);
//        boolean test = dao.updatePost(1, 1, "Hieu Test updata");
//
//        for (Post p : posts) {
//            System.out.println(p);
//        }

        boolean test = dao.deletePost(1, 11);
        System.out.println(test);
    }
}
