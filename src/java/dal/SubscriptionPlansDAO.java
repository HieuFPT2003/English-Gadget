package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SubscriptionPlans;

public class SubscriptionPlansDAO extends DBContext {

    public List<SubscriptionPlans> getAll() {
        List<SubscriptionPlans> list = new ArrayList<>();
        String sql = "SELECT * FROM SubscriptionPlans";
        try (PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                SubscriptionPlans plan = new SubscriptionPlans(
                        rs.getInt("planID"),
                        rs.getString("plan_name"),
                        rs.getDouble("plan_price"),
                        rs.getInt("duration_days"),
                        rs.getDate("created_at")
                );
                list.add(plan);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAll: " + e.getMessage());
        }
        return list;
    }

    public SubscriptionPlans getById(int id) {
        String sql = "SELECT * FROM SubscriptionPlans WHERE planID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new SubscriptionPlans(
                            rs.getInt("planID"),
                            rs.getString("plan_name"),
                            rs.getDouble("plan_price"),
                            rs.getInt("duration_days"),
                            rs.getDate("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getById: " + e.getMessage());
        }
        return null;
    }

    public void insert(SubscriptionPlans plan) {
        String sql = """
                     INSERT INTO SubscriptionPlans
                                (planID, plan_name, plan_price, duration_days, created_at)
                          VALUES (?, ?, ?, ?, ?)""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, plan.getPlanID());
            st.setString(2, plan.getPlanName());
            st.setDouble(3, plan.getPlanPrice());
            st.setInt(4, plan.getDurationDays());
            st.setDate(5, new java.sql.Date(plan.getCreatedAt().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert: " + e.getMessage());
        }
    }

    public void update(SubscriptionPlans plan) {
        String sql = """
                     UPDATE SubscriptionPlans
                        SET plan_name = ?, plan_price = ?, duration_days = ?, created_at = ?
                        WHERE planID = ?""";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, plan.getPlanName());
            st.setDouble(2, plan.getPlanPrice());
            st.setInt(3, plan.getDurationDays());
            st.setDate(4, new java.sql.Date(plan.getCreatedAt().getTime()));
            st.setInt(5, plan.getPlanID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in update: " + e.getMessage());
        }
    }

    public void delete(int userID) {
        String sql = "DELETE FROM SubscriptionPlans WHERE planID = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, userID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in delete: " + e.getMessage());
        }
    }
}
