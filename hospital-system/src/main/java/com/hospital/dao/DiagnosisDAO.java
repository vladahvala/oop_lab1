package com.hospital.dao;

import com.hospital.model.Diagnosis;
import com.hospital.util.DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisDAO {

    private static final Logger logger = LogManager.getLogger(DiagnosisDAO.class);

    public List<Diagnosis> getAll() {

        List<Diagnosis> list = new ArrayList<>();
        String sql = "SELECT * FROM diagnoses";

        logger.info("Fetching all diagnoses from DB");

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

            logger.info("Fetched " + list.size() + " diagnoses");

        } catch (Exception e) {
            logger.error("DB error in getAll diagnoses: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Diagnosis d) {

        String sql = "INSERT INTO diagnoses(patient_id, doctor_id, description, final_diagnosis) VALUES (?, ?, ?, ?)";

        logger.info("Inserting diagnosis for patientId=" + d.getPatientId());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getPatientId());
            ps.setInt(2, d.getDoctorId());
            ps.setString(3, d.getDescription());
            ps.setBoolean(4, d.isFinalDiagnosis());

            ps.executeUpdate();

            logger.info("Diagnosis inserted successfully");

        } catch (Exception e) {
            logger.error("DB error in add diagnosis: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Diagnosis getById(int id) {

        String sql = "SELECT * FROM diagnoses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Diagnosis(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("description"),
                        rs.getBoolean("final_diagnosis"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}