package com.hospital.service;

import com.hospital.dao.TreatmentDAO;
import com.hospital.model.Treatment;

import java.util.List;

public class TreatmentService {

    private TreatmentDAO dao = new TreatmentDAO();

    public List<Treatment> getAll() {
        return dao.getAll();
    }

    public void add(Treatment t) {
        dao.add(t);
    }
}