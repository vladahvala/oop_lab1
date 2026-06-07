package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("birth_date"),
                        rs.getString("status"));
                list.add(p);
            }

            logger.info("Successfully fetched " + list.size() + " patients");

        } catch (Exception e) {
            logger.error("DB error in getAll(): " + e.getMessage());
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

            logger.info("BirthDate value: " + p.getBirthDate());

            ps.setString(1, p.getFullName());
            ps.setDate(2, java.sql.Date.valueOf(p.getBirthDate().trim()));
            ps.setString(3, p.getStatus());

            ps.executeUpdate();

            logger.info("Patient inserted successfully: " + p.getFullName());

        } catch (Exception e) {
            logger.error("DB error in add(): " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}