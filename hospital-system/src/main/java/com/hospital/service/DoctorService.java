package com.hospital.service;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;

import java.util.List;

public class DoctorService {

    private DoctorDAO dao = new DoctorDAO();

    public List<Doctor> getAll() {
        return dao.getAll();
    }

    public void add(Doctor d) {
        dao.add(d);
    }
}