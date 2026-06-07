package com.hospital.service;

import com.hospital.dao.DiagnosisDAO;
import com.hospital.model.Diagnosis;

import java.util.List;

public class DiagnosisService {

    private DiagnosisDAO dao = new DiagnosisDAO();

    public List<Diagnosis> getAll() {
        return dao.getAll();
    }

    public void add(Diagnosis d) {
        dao.add(d);
    }
}