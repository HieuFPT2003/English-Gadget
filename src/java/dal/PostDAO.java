package dal;

import model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dal.DBUtils;
import java.util.Date;


public class PostDAO {
    Connection conn = DBUtils.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
    public List<Post> getAllPost(){
        String sql = "SELECT userID, postText FROM UserPost;";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String postText = rs.getString("postText");
                Post post = new Post(userID, postText);
                posts.add(post);
            }
            return posts;
        } catch(Exception e) {
                e.printStackTrace();
            return null;
        }
    }   
    
    public boolean inseartPost(int userID,String content){
        String sql = "INSERT INTO UserPost ( userID, postText) VALUES (" +  userID + ", N'" + content + "')";
        try{
            ps = conn.prepareStatement(sql);
            int result = ps.executeUpdate();
            if(result > 0){
                return true;
            }else{
                return false;
            }
                 
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
