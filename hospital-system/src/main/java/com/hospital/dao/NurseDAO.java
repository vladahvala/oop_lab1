package com.hospital.dao;

import com.hospital.model.Nurse;
import com.hospital.util.DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {

    private static final Logger logger = LogManager.getLogger(NurseDAO.class);

    public List<Nurse> getAll() {

        List<Nurse> list = new ArrayList<>();
        String sql = "SELECT * FROM nurses";

        logger.info("Fetching all nurses from DB");

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Nurse n = new Nurse(
                        rs.getInt("id"),
                        rs.getString("full_name"));
                list.add(n);
            }

            logger.info("Fetched " + list.size() + " nurses");

        } catch (Exception e) {
            logger.error("DB error in getAll nurses: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Nurse n) {

        String sql = "INSERT INTO nurses(full_name) VALUES (?)";

        logger.info("Inserting nurse: " + n.getFullName());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, n.getFullName());

            ps.executeUpdate();

            logger.info("Nurse inserted successfully");

        } catch (Exception e) {
            logger.error("DB error in add nurse: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}