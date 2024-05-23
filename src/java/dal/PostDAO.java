package dal;

import model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dal.DBUtils;
import java.sql.SQLException;
import java.util.Date;
import java.util.Arrays;


public class PostDAO {
    Connection conn = DBUtils.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

       public List<Post> getAllPost() {
        String sql = "SELECT p.postID, p.userID, p.postText, p.datePosted, " +
                     "ISNULL(likeCountTable.likeCount, 0) AS likeCount, " +
                     "ISNULL(dislikeCountTable.dislikeCount, 0) AS dislikeCount, " +
                     "ISNULL(likeUsers.userIDs, '') AS likeUserIDs, " +
                     "ISNULL(dislikeUsers.userIDs, '') AS dislikeUserIDs " +
                     "FROM UserPost p " +
                     "LEFT JOIN ( " +
                     "    SELECT postID, COUNT(*) AS likeCount " +
                     "    FROM Emotion " +
                     "    WHERE emotionType = 'like' " +
                     "    GROUP BY postID " +
                     ") AS likeCountTable ON p.postID = likeCountTable.postID " +
                     "LEFT JOIN ( " +
                     "    SELECT postID, COUNT(*) AS dislikeCount " +
                     "    FROM Emotion " +
                     "    WHERE emotionType = 'dislike' " +
                     "    GROUP BY postID " +
                     ") AS dislikeCountTable ON p.postID = dislikeCountTable.postID " +
                     "LEFT JOIN ( " +
                     "    SELECT postID, STRING_AGG(userID, ', ') AS userIDs " +
                     "    FROM Emotion " +
                     "    WHERE emotionType = 'like' " +
                     "    GROUP BY postID " +
                     ") AS likeUsers ON p.postID = likeUsers.postID " +
                     "LEFT JOIN ( " +
                     "    SELECT postID, STRING_AGG(userID, ', ') AS userIDs " +
                     "    FROM Emotion " +
                     "    WHERE emotionType = 'dislike' " +
                     "    GROUP BY postID " +
                     ") AS dislikeUsers ON p.postID = dislikeUsers.postID;";

        List<Post> posts = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int postID = rs.getInt("postID");
                int userID = rs.getInt("userID");
                String postText = rs.getString("postText");
                Date datePosted = rs.getTimestamp("datePosted");
                int likeCount = rs.getInt("likeCount");
                int dislikeCount = rs.getInt("dislikeCount");

                List<Integer> likeUserIDs = new ArrayList<>();
                List<Integer> dislikeUserIDs = new ArrayList<>();

                String likeUserIDsStr = rs.getString("likeUserIDs");
                if (!likeUserIDsStr.isEmpty()) {
                    Arrays.stream(likeUserIDsStr.split(", "))
                            .map(Integer::parseInt)
                            .forEach(likeUserIDs::add);
                }

                String dislikeUserIDsStr = rs.getString("dislikeUserIDs");
                if (!dislikeUserIDsStr.isEmpty()) {
                    Arrays.stream(dislikeUserIDsStr.split(", "))
                            .map(Integer::parseInt)
                            .forEach(dislikeUserIDs::add);
                }

                Post post = new Post(postID, userID, postText, datePosted, likeCount, dislikeCount);
                post.setLikeUserIDs(likeUserIDs);
                post.setDislikeUserIDs(dislikeUserIDs);
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return posts;
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
        }
        return false;
    }
}