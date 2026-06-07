package com.hospital.service;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PatientService {

    private final PatientDAO dao = new PatientDAO();
    private static final Logger logger = LogManager.getLogger(PatientService.class);

    // GET ALL
    public List<Patient> getAll() {
        logger.info("Service: fetching all patients");
        return dao.getAll();
    }

    // ADD
    public void add(Patient p) {

        logger.info("Service: adding patient " + p.getFullName());

        try {
            dao.add(p);
            logger.info("Service: patient added successfully " + p.getFullName());

        } catch (Exception e) {
            logger.error("Service error while adding patient: " + e.getMessage());
            throw e;
        }
    }
}