package com.hospital.dao;

import com.hospital.model.Doctor;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getAll() {
        List<Doctor> list = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Doctor d) {
        String sql = "INSERT INTO doctors(full_name, specialization) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getFullName());
            ps.setString(2, d.getSpecialization());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}