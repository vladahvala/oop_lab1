package com.hospital.dao;

import com.hospital.model.Diagnosis;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAO {

    public List<Diagnosis> getAll() {
        List<Diagnosis> list = new ArrayList<>();

        String sql = "SELECT * FROM diagnoses";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Diagnosis d = new Diagnosis(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("description"),
                        rs.getBoolean("final_diagnosis"));
                list.add(d);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Diagnosis d) {
        String sql = "INSERT INTO diagnoses(patient_id, doctor_id, description, final_diagnosis) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getPatientId());
            ps.setInt(2, d.getDoctorId());
            ps.setString(3, d.getDescription());
            ps.setBoolean(4, d.isFinalDiagnosis());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}