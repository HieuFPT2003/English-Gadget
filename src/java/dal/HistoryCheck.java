package dal;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.History;

public class HistoryCheck extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<History> getHistoryList(int id) {
        String sql = "select * from CheckHistory Where userID = ?";
        List<History> histories = new ArrayList<>();

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

                histories.add(new History(checkID, userID, text, result, checkDate, type));
            }

            return histories;
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
        String sql = "INSERT INTO CheckHistory (userID, text, result, checkDate,type) VALUES (?, ?, ?, ?,?)";

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

    public JsonArray getHistory(int userID) {
        JsonArray historyArray = new JsonArray();
        String sql = "SELECT * FROM CheckHistory WHERE userID = ?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                JsonObject historyItem = new JsonObject();
                historyItem.addProperty("checkID", rs.getInt("checkID"));
                historyItem.addProperty("userID", rs.getInt("userID"));
                historyItem.addProperty("text", rs.getString("text"));
                historyItem.addProperty("result", rs.getString("result"));
                historyItem.addProperty("checkDate", rs.getTimestamp("checkDate").toString());
                historyItem.addProperty("type", rs.getBoolean("type") ? "grammar" : "spelling");

                historyArray.add(historyItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

        return historyArray;
    }

    public static void main(String[] args) {
        HistoryCheck dao = new HistoryCheck();

        // Test getHistory
        JsonArray history = dao.getHistory(1);
        System.out.println(history);
    }
}
