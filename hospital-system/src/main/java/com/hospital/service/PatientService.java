package com.hospital.service;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PatientService {

    private final PatientDAO dao = new PatientDAO();
    private static final Logger logger = LogManager.getLogger(PatientService.class);

    public List<Patient> getAll() {
        return dao.getAll();
    }

    public void add(Patient p) {
        dao.add(p);
    }

    // ✅ BUSINESS LOGIC: discharge patient
    public void discharge(int patientId) {

        logger.info("Discharging patient id=" + patientId);

        Patient p = dao.getById(patientId);

        if (p == null) {
            logger.warn("Patient not found id=" + patientId);
            throw new RuntimeException("Patient not found");
        }

        if ("DISCHARGED".equals(p.getStatus())) {
            logger.warn("Patient already discharged id=" + patientId);
            throw new RuntimeException("Already discharged");
        }

        dao.updateStatus(patientId, "DISCHARGED");

        logger.info("Patient discharged successfully id=" + patientId);
    }
}