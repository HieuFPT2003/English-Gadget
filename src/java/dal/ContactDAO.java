/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Advertise;
import model.Contact;
import model.ReportPost;

/**
 *
 * @author Admin
 */
public class ContactDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Contact> getAllContact() {
        String sql = "select * from ContactUs\n"
                + "ORDER BY CreatedAt DESC ";

        List<Contact> contacts = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactID(rs.getInt("contactID"));
                    contact.setName(rs.getString("userName"));
                    contact.setEmail(rs.getString("email"));
                    contact.setSubject(rs.getString("subject"));
                    contact.setMessage(rs.getString("message"));
                    contact.setStatus(rs.getBoolean("status"));
                    contact.setCreate_at(rs.getTimestamp("CreatedAt"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return contacts;
    }

    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();
        Timestamp create_at = new Timestamp(System.currentTimeMillis());

//        boolean result = dao.insertContact("admin1", "admin1@example.com", "General Inquiry", "This is a general inquiry message.", true, create_at);
////
//        if (result) {
//            System.out.println("Advertise inserted successfully.");
//        } else {
//            System.out.println("Failed to insert advertise.");
//        }
        boolean a = dao.updateContact(2, true, create_at);
        System.out.println(a);
//        List<Contact> posts = dao.filterContactByType("1");
//        for (Contact p : posts) {
//            System.out.println(p);

//        boolean a = dao.deletePost(5);
//        System.out.println(a);
    }

    public List<Contact> filterContactByType(String status) {
        String sql = "select * from ContactUs\n"
                + "where status = ?";

        List<Contact> contacts = new ArrayList<>();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setContactID(rs.getInt("contactID"));
                    contact.setName(rs.getString("userName"));
                    contact.setEmail(rs.getString("email"));
                    contact.setSubject(rs.getString("subject"));
                    contact.setMessage(rs.getString("message"));
                    contact.setStatus(rs.getBoolean("status"));
                    contact.setCreate_at(rs.getTimestamp("CreatedAt"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return contacts;
    }

    public boolean insertContact(String name, String email, String subject, String message, boolean status, Timestamp CreatedAt) {
        String sql = "INSERT INTO ContactUs (UserName, Email, Subject, Message, status, CreatedAt)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?)";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, subject);
            ps.setString(4, message);
            ps.setBoolean(5, status);
            ps.setTimestamp(6, CreatedAt);

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

    public Contact getContactByID(int ctID) {
        String sql = "select * from ContactUs\n"
                + "where contactID = ?";

        Contact contact = new Contact();

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ctID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    contact.setContactID(rs.getInt("contactID"));
                    contact.setName(rs.getString("userName"));
                    contact.setEmail(rs.getString("email"));
                    contact.setSubject(rs.getString("subject"));
                    contact.setMessage(rs.getString("message"));
                    contact.setStatus(rs.getBoolean("status"));
                    contact.setCreate_at(rs.getTimestamp("CreatedAt"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and re-throwing as a custom exception
        }
        return contact;
    }

    public boolean updateContact(int ctID, boolean status, Timestamp CreatedAt) {
        String sql = "UPDATE ContactUs\n"
                + "SET  CreatedAt = ?,\n"
                + "    status = ?\n"
                + "WHERE contactID = ?;";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(3, ctID);
            ps.setBoolean(2, status);
            ps.setTimestamp(1, CreatedAt);

            int row = ps.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
