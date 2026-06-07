package com.hospital.dao;

import com.hospital.model.Treatment;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    public List<Treatment> getAll() {
        List<Treatment> list = new ArrayList<>();

        String sql = "SELECT * FROM treatments";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Treatment t = new Treatment(
                        rs.getInt("id"),
                        rs.getInt("diagnosis_id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getString("assigned_by_role"),
                        rs.getString("status"));
                list.add(t);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Treatment t) {
        String sql = "INSERT INTO treatments(diagnosis_id, type, description, assigned_by_role, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getDiagnosisId());
            ps.setString(2, t.getType());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getAssignedByRole());
            ps.setString(5, t.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}