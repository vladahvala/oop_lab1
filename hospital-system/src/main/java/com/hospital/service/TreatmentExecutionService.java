package com.hospital.service;

import com.hospital.dao.TreatmentExecutionDAO;
import com.hospital.model.TreatmentExecution;
import java.util.List;

public class TreatmentExecutionService {

    private final TreatmentExecutionDAO dao = new TreatmentExecutionDAO();

    public List<TreatmentExecution> getAll() {
        return dao.getAll();
    }

    public void executeTreatment(TreatmentExecution t, String role) {
        dao.add(t);
    }
}