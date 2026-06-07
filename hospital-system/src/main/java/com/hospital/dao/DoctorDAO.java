package com.hospital.dao;

import com.hospital.model.Doctor;
import com.hospital.util.DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    private static final Logger logger = LogManager.getLogger(DoctorDAO.class);

    public List<Doctor> getAll() {

        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        logger.info("Fetching all doctors from DB");

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("specialization"));
                list.add(d);
            }

            logger.info("Fetched " + list.size() + " doctors");

        } catch (Exception e) {
            logger.error("DB error in getAll doctors: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Doctor d) {

        String sql = "INSERT INTO doctors(full_name, specialization) VALUES (?, ?)";

        logger.info("Inserting doctor: " + d.getFullName());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getFullName());
            ps.setString(2, d.getSpecialization());

            ps.executeUpdate();

            logger.info("Doctor inserted successfully: " + d.getFullName());

        } catch (Exception e) {
            logger.error("DB error in add doctor: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}