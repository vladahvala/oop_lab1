package com.hospital.dao;

import com.hospital.model.TreatmentExecution;
import com.hospital.util.DBConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TreatmentExecutionDAO {

    private static final Logger logger = LogManager.getLogger(TreatmentExecutionDAO.class);

    public List<TreatmentExecution> getAll() {

        List<TreatmentExecution> list = new ArrayList<>();
        String sql = "SELECT * FROM treatment_executions";

        logger.info("Fetching all executions from DB");

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TreatmentExecution t = new TreatmentExecution(
                        rs.getInt("id"),
                        rs.getInt("treatment_id"),
                        (Integer) rs.getObject("nurse_id"),
                        (Integer) rs.getObject("doctor_id"),
                        rs.getTimestamp("executed_at").toLocalDateTime());
                list.add(t);
            }

            logger.info("Fetched " + list.size() + " executions");

        } catch (Exception e) {
            logger.error("DB error in getAll executions: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(TreatmentExecution t) {

        String sql = "INSERT INTO treatment_executions(treatment_id, nurse_id, doctor_id, executed_at) VALUES (?, ?, ?, ?)";

        logger.info("Inserting execution for treatmentId=" + t.getTreatmentId());

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getTreatmentId());

            if (t.getNurseId() != null)
                ps.setInt(2, t.getNurseId());
            else
                ps.setNull(2, Types.INTEGER);

            if (t.getDoctorId() != null)
                ps.setInt(3, t.getDoctorId());
            else
                ps.setNull(3, Types.INTEGER);

            ps.setTimestamp(4, Timestamp.valueOf(
                    t.getExecutedAt() != null ? t.getExecutedAt() : LocalDateTime.now()));

            ps.executeUpdate();

            logger.info("Execution inserted successfully");

        } catch (Exception e) {
            logger.error("DB error in add execution: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}