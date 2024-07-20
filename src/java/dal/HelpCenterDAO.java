package dal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import dal.DBContext;
import dal.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HelpCenter;
import model.TopicHelpCenter;
import model.Users;

/**
 *
 * @author Admin
 */
public class HelpCenterDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<TopicHelpCenter> getAllTopic() {
        List<TopicHelpCenter> list = new ArrayList<>();
        String sql = "select * from [dbo].[TopicHelpCenter]";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                TopicHelpCenter topic = new TopicHelpCenter(
                        rs.getInt("topicID"),
                        rs.getString("topicName"),
                        rs.getString("topicDetail"),
                        rs.getString("topicPics")
                );
                list.add(topic);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public List<HelpCenter> getAllQaA() {
        List<HelpCenter> list = new ArrayList<>();
        String sql = "select * from [dbo].[HelpCenter]";
        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                HelpCenter help = new HelpCenter(
                        rs.getInt("heID"),
                        rs.getString("questionContent"),
                        rs.getString("answerContent"),
                        rs.getInt("topicID")
                );
                list.add(help);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        HelpCenterDAO dao = new HelpCenterDAO();
        List<TopicHelpCenter> list = dao.getAllTopic();
        for (TopicHelpCenter o : list) {
            System.out.println(o);
        }

//        }
//        HelpCenterDAO dao1 = new HelpCenterDAO();
//        List<HelpCenter> list1 = dao1.getAllQaA();
//        for (HelpCenter o : list1) {
//            System.out.println(o);
//
//        }
//        HelpCenterDAO dao2 = new HelpCenterDAO();
//        System.out.println("break");
//        List<HelpCenter> list2 = dao2.searchAns("click");
//        for (HelpCenter o : list2) {
//            System.out.println(o);
//
//        }
    }

//    public List<HelpCenter> searchAns(String ans) {
//        List<HelpCenter> list = new ArrayList<>();
//        String sql = "select * from HelpCenter where [answerContent] like ? OR [questionContent] LIKE ?";
//        try (PreparedStatement st = connection.prepareStatement("%" + sql + "%"); ResultSet rs = st.executeQuery()) {
//            while (rs.next()) {
//                HelpCenter help = new HelpCenter(
//                        rs.getInt("heID"),
//                        rs.getString("questionContent"),
//                        rs.getString("answerContent"),
//                        rs.getInt("topicID")
//                );
//                list.add(help);
//
//            }
//        } catch ( Exception e) {
//        }
//
//        return list;
//    }
    public List<HelpCenter> searchAns(String ans) {

        List<HelpCenter> listAns = new ArrayList<>();
        String sql = "select * from HelpCenter where [answerContent] like ? OR [questionContent] like ?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + ans + "%");
            ps.setString(2, "%" + ans + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                listAns.add(new HelpCenter(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return listAns;
    }

    public List<HelpCenter> searchQAByiD(int Id) {
        List<HelpCenter> listId = new ArrayList<>();
        String sql = "select * from HelpCenter \n"
                + "  where topicID =?";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(new HelpCenter(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }

        return listId;

    }

}
