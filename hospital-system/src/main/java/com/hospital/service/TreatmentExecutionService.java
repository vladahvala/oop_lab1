package com.hospital.service;

import com.hospital.dao.TreatmentExecutionDAO;
import com.hospital.model.TreatmentExecution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TreatmentExecutionService {

    private final TreatmentExecutionDAO dao = new TreatmentExecutionDAO();
    private static final Logger logger = LogManager.getLogger(TreatmentExecutionService.class);

    public List<TreatmentExecution> getAll() {
        logger.info("Service: fetching all executions");
        return dao.getAll();
    }

    public void add(TreatmentExecution t) {
        logger.info("Service: adding execution for treatmentId=" + t.getTreatmentId());

        try {
            dao.add(t);
            logger.info("Service: execution added successfully");
        } catch (Exception e) {
            logger.error("Service error: " + e.getMessage());
            throw e;
        }
    }
}