package com.hospital.service;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DoctorService {

    private final DoctorDAO dao = new DoctorDAO();
    private static final Logger logger = LogManager.getLogger(DoctorService.class);

    public List<Doctor> getAll() {
        logger.info("Service: fetching all doctors");
        return dao.getAll();
    }

    public void add(Doctor d) {
        logger.info("Service: adding doctor " + d.getFullName());

        try {
            dao.add(d);
            logger.info("Service: doctor added successfully " + d.getFullName());

        } catch (Exception e) {
            logger.error("Service error while adding doctor: " + e.getMessage());
            throw e;
        }
    }
}