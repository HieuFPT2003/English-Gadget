/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Advertise;
import model.Post;
import model.ReportPost;

/**
 *
 * @author Admin
 */
public class AdvertiseDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Advertise> getAllAd() {
        String sql = "select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at\n"
                + "from Advertise a join Users u ON a.userID=u.userID\n"
                + "ORDER BY a.adID DESC";

        List<Advertise> ads = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Advertise advertise = new Advertise();
                    advertise.setAdID(rs.getInt("adID"));
                    advertise.setUserName(rs.getString("adminName"));
                    advertise.setTitle(rs.getString("title"));
                    advertise.setCreated_at(rs.getDate("created_at"));
                    advertise.setDescription(rs.getString("description"));
                    advertise.setImageAd(rs.getString("imageAd"));
                    advertise.setIsActive(rs.getBoolean("isActive"));
                    ads.add(advertise);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return ads;
    }

    public boolean deleteAdvertise(int adID) {
        String sql = "delete from Advertise\n"
                + "  where adID = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, adID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAllAdvertise() {
        String sql = "delete from Advertise";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Advertise> filterAdvertisesByStatus(String status) {
        String sql = "select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at\n"
                + "from Advertise a join Users u ON a.userID=u.userID\n"
                + "where a.isActive = ?";

        List<Advertise> ads = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Advertise advertise = new Advertise();
                    advertise.setAdID(rs.getInt("adID"));
                    advertise.setUserName(rs.getString("adminName"));
                    advertise.setTitle(rs.getString("title"));
                    advertise.setCreated_at(rs.getDate("created_at"));
                    advertise.setDescription(rs.getString("description"));
                    advertise.setImageAd(rs.getString("imageAd"));
                    advertise.setIsActive(rs.getBoolean("isActive"));
                    ads.add(advertise);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return ads;
    }

    public List<Advertise> searchAdsByTitle(String title) {
        String sql = "select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at\n"
                + "from Advertise a join Users u ON a.userID=u.userID\n"
                + "where a.title LIKE ?";

        List<Advertise> ads = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + title + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Advertise advertise = new Advertise();
                    advertise.setAdID(rs.getInt("adID"));
                    advertise.setUserName(rs.getString("adminName"));
                    advertise.setTitle(rs.getString("title"));
                    advertise.setCreated_at(rs.getDate("created_at"));
                    advertise.setDescription(rs.getString("description"));
                    advertise.setImageAd(rs.getString("imageAd"));
                    advertise.setIsActive(rs.getBoolean("isActive"));
                    ads.add(advertise);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return ads;
    }

    public boolean insertAdvertise(String title, String description, String imageAd, boolean isActive, int adminID, Timestamp create_at) {
        String sql = "INSERT INTO Advertise (title, description, imageAd, isActive, userID, created_at) VALUES\n"
                + "(?, ?, ?, ?, ?,?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, imageAd);
            ps.setBoolean(4, isActive);
            ps.setInt(5, adminID);
            ps.setTimestamp(6, create_at);

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

    public Advertise getAdsByadID(int adID) {
        String sql = "select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at\n"
                + "from Advertise a join Users u ON a.userID=u.userID\n"
                + "where adID = ?";

        Advertise advertise = new Advertise();
        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, adID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    advertise.setAdID(rs.getInt("adID"));
                    advertise.setUserName(rs.getString("adminName"));
                    advertise.setTitle(rs.getString("title"));
                    advertise.setCreated_at(rs.getDate("created_at"));
                    advertise.setDescription(rs.getString("description"));
                    advertise.setImageAd(rs.getString("imageAd"));
                    advertise.setIsActive(rs.getBoolean("isActive"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return advertise;
    }

    public List<Advertise> getAdsByStatus(int status) {
        String sql = "select a.adID,a.title,a.description,a.imageAd,a.isActive, u.username as adminName, a.created_at\n"
                + "from Advertise a join Users u ON a.userID=u.userID\n"
                + "where a.isActive = ?";

        List<Advertise> ads = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Advertise advertise = new Advertise();
                    advertise.setAdID(rs.getInt("adID"));
                    advertise.setUserName(rs.getString("adminName"));
                    advertise.setTitle(rs.getString("title"));
                    advertise.setCreated_at(rs.getDate("created_at"));
                    advertise.setDescription(rs.getString("description"));
                    advertise.setImageAd(rs.getString("imageAd"));
                    advertise.setIsActive(rs.getBoolean("isActive"));
                    ads.add(advertise);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return ads;
    }

    public boolean updateAds(int adID, String title, String description, String imageAd, boolean isActive, int adminID, Timestamp create_at) {
        String sql = "Update Advertise \n"
                + "  set title = ?, description = ?, imageAd= ?, isActive = ?, userID = ?, created_at= ?\n"
                + "  where adID = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, imageAd);
            ps.setBoolean(4, isActive);
            ps.setInt(5, adminID);
            ps.setTimestamp(6, create_at);
            ps.setInt(7, adID);

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        AdvertiseDAO advertiseDAO = new AdvertiseDAO();
//        Timestamp created_at = new Timestamp(System.currentTimeMillis());
//        advertiseDAO.updateAds(154, "MINH", "MINH", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Golden_tabby_and_white_kitten_n01.jpg/1200px-Golden_tabby_and_white_kitten_n01.jpg", true, 6, created_at);
//        // Các giá trị mẫu để chèn vào cơ sở dữ liệu
//        String title = "Sample Title";
//        String description = "Sample Description";
//        String imageAd = "http://example.com/sample.jpg"; // URL hình ảnh
//        boolean isActive = true;
//        int adminID = 1;
        Timestamp create_at = new Timestamp(System.currentTimeMillis());
//
//        boolean result = advertiseDAO.insertAdvertise("something", "something", "sômething", true, 6, create_at);
////
//        if (result) {
//            System.out.println("Advertise inserted successfully.");
//        } else {
//            System.out.println("Failed to insert advertise.");
//        }
        boolean a =advertiseDAO.updateAds(221, "some", "some", "sôme", false, 6, create_at);
        System.out.println(a);
    }

}
