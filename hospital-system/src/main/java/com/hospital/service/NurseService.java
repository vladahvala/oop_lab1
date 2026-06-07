package com.hospital.service;

import com.hospital.dao.NurseDAO;
import com.hospital.model.Nurse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class NurseService {

    private final NurseDAO dao = new NurseDAO();
    private static final Logger logger = LogManager.getLogger(NurseService.class);

    public List<Nurse> getAll() {
        logger.info("Service: fetching nurses");
        return dao.getAll();
    }

    public void add(Nurse n) {
        logger.info("Service: adding nurse " + n.getFullName());

        try {
            dao.add(n);
            logger.info("Service: nurse added successfully");

        } catch (Exception e) {
            logger.error("Service error: " + e.getMessage());
            throw e;
        }
    }
}