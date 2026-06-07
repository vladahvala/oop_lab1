package com.hospital.service;

import com.hospital.dao.TreatmentDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.DiagnosisDAO;
import com.hospital.model.Patient;
import com.hospital.model.Treatment;
import com.hospital.model.Diagnosis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TreatmentService {

    private final TreatmentDAO dao = new TreatmentDAO();
    private final PatientDAO patientDAO = new PatientDAO();
    private final DiagnosisDAO diagnosisDAO = new DiagnosisDAO();

    private static final Logger logger = LogManager.getLogger(TreatmentService.class);

    public List<Treatment> getAll() {
        logger.info("Service: fetching all treatments");
        return dao.getAll();
    }

    public void add(Treatment t) {

        logger.info("Service: creating treatment for diagnosisId=" + t.getDiagnosisId());

        // 🔥 1. діагноз
        Diagnosis diagnosis = diagnosisDAO.getById(t.getDiagnosisId());

        if (diagnosis == null) {
            logger.warn("Diagnosis not found");
            throw new RuntimeException("Diagnosis not found");
        }

        // 🔥 2. пацієнт через діагноз
        Patient patient = patientDAO.getById(diagnosis.getPatientId());

        if (patient == null) {
            logger.warn("Patient not found");
            throw new RuntimeException("Patient not found");
        }

        // 🔥 3. бізнес-правило: не можна після виписки
        if ("DISCHARGED".equals(patient.getStatus())) {
            logger.warn("Cannot create treatment for discharged patient");
            throw new RuntimeException("Patient already discharged");
        }

        // 🔥 4. роль (додатковий safety check)
        if (!"DOCTOR".equals(t.getAssignedByRole())) {
            logger.warn("Only doctor can create treatment");
            throw new RuntimeException("Access denied");
        }

        try {
            dao.add(t);
            logger.info("Service: treatment created successfully");

        } catch (Exception e) {
            logger.error("Service error in add treatment: " + e.getMessage());
            throw e;
        }
    }
}