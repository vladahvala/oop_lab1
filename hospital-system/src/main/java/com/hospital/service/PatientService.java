package com.hospital.service;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

import java.util.List;

public class PatientService {

    private PatientDAO dao = new PatientDAO();

    public List<Patient> getAll() {
        return dao.getAll();
    }

    public void add(Patient p) {
        dao.add(p);
    }
}