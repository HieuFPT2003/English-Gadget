package dal;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import model.History;

public class HistoryCheck extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<History> getHistoryCheck(int id) {
        String sql = "select * from CheckHistory Where userID = ?";
        List<History> historys = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int checkID = rs.getInt("checkID");
                int userID = rs.getInt("userID");
                String text = rs.getString("text");
                String result = rs.getString("result");
                Date checkDate = rs.getDate("checkDate");
                boolean type = rs.getBoolean("type");

                historys.add(new History(checkID, userID, text, result, checkDate, type));
            }

            return historys;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveGrammarCheckHistory(History newHistory) {
        String sql = "INSERT INTO CheckHistory (userID, text, result, checkDate,type) VALUES (?, ?, ?, ?,?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, newHistory.getUserID());
            ps.setString(2, newHistory.getText());
            ps.setString(3, newHistory.getResult());
            ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            ps.setBoolean(5, true);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveSpellingCheckHistory(History newHistory) {
        String sql = "INSERT INTO GrammarCheckHistory (userID, text, result, checkDate,type) VALUES (?, ?, ?, ?,?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, newHistory.getUserID());
            ps.setString(2, newHistory.getText());
            ps.setString(3, "This is Check Spelling");
            ps.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
            ps.setBoolean(5, false);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HistoryCheck dao = new HistoryCheck();
        History newH = new History(1, "Test", "Test111", true);
        dao.saveGrammarCheckHistory(newH);
    }
}
