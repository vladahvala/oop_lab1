package com.hospital.service;

import com.hospital.dao.TreatmentDAO;
import com.hospital.model.Treatment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TreatmentService {

    private final TreatmentDAO dao = new TreatmentDAO();
    private static final Logger logger = LogManager.getLogger(TreatmentService.class);

    public List<Treatment> getAll() {
        logger.info("Service: fetching all treatments");
        return dao.getAll();
    }

    public void add(Treatment t) {

        logger.info("Service: creating treatment for diagnosisId=" + t.getDiagnosisId());

        try {
            dao.add(t);
            logger.info("Service: treatment created successfully");

        } catch (Exception e) {
            logger.error("Service error in add treatment: " + e.getMessage());
            throw e;
        }
    }
}