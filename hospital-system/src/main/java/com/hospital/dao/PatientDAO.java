package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    private static final Logger logger = LogManager.getLogger(PatientDAO.class);

    // GET ALL
    public List<Patient> getAll() {
        List<Patient> list = new ArrayList<>();

        String sql = "SELECT * FROM patients";

        logger.info("Fetching all patients from DB");

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("birth_date"),
                        rs.getString("status")));
            }

            logger.info("Fetched " + list.size() + " patients");

        } catch (Exception e) {
            logger.error("getAll error: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }

    // INSERT
    public void add(Patient p) {

        String sql = "INSERT INTO patients(full_name, birth_date, status) VALUES (?, ?, ?)";

        logger.info("Inserting patient: " + p.getFullName());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getFullName());
            ps.setDate(2, Date.valueOf(p.getBirthDate().trim()));
            ps.setString(3, p.getStatus());

            ps.executeUpdate();

            logger.info("Inserted patient: " + p.getFullName());

        } catch (Exception e) {
            logger.error("add error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // GET BY ID
    public Patient getById(int id) {

        String sql = "SELECT * FROM patients WHERE id = ?";

        logger.info("Fetching patient id=" + id);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("birth_date"),
                        rs.getString("status"));
            }

        } catch (Exception e) {
            logger.error("getById error: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    // UPDATE STATUS
    public void updateStatus(int id, String status) {

        String sql = "UPDATE patients SET status = ? WHERE id = ?";

        logger.info("Updating patient id=" + id + " status=" + status);

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();

            logger.info("Status updated");

        } catch (Exception e) {
            logger.error("updateStatus error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setFinalDiagnosis(int id) {

        String sql = "UPDATE diagnoses SET final_diagnosis = true WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}