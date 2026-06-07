package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // GET ALL
    public List<Patient> getAll() {
        List<Patient> list = new ArrayList<>();

        String sql = "SELECT * FROM patients";

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // INSERT
    public void add(Patient p) {
        String sql = "INSERT INTO patients(full_name, birth_date, status) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("DEBUG birthDate = " + p.getBirthDate());

            ps.setString(1, p.getFullName());
            ps.setDate(2, java.sql.Date.valueOf(p.getBirthDate().trim()));
            ps.setString(3, p.getStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}