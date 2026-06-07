package com.hospital.dao;

import com.hospital.model.Nurse;
import com.hospital.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NurseDAO {

    public List<Nurse> getAll() {
        List<Nurse> list = new ArrayList<>();

        String sql = "SELECT * FROM nurses";

        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Nurse n = new Nurse(
                        rs.getInt("id"),
                        rs.getString("full_name"));
                list.add(n);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void add(Nurse n) {
        String sql = "INSERT INTO nurses(full_name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, n.getFullName());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}