package com.hospital.service;

import com.hospital.dao.DiagnosisDAO;
import com.hospital.model.Diagnosis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DiagnosisService {

    private final DiagnosisDAO dao = new DiagnosisDAO();
    private static final Logger logger = LogManager.getLogger(DiagnosisService.class);

    public List<Diagnosis> getAll() {
        logger.info("Service: fetching all diagnoses");
        return dao.getAll();
    }

    public void add(Diagnosis d) {

        logger.info("Service: creating diagnosis for patientId=" + d.getPatientId());

        try {
            dao.add(d);
            logger.info("Service: diagnosis created successfully");

        } catch (Exception e) {
            logger.error("Service error in add diagnosis: " + e.getMessage());
            throw e;
        }
    }
}