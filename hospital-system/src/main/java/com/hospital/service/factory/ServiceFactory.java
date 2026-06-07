package com.hospital.service.factory;

import com.hospital.service.*;

public class ServiceFactory {

    public static Object getService(String type) {

        if ("patient".equalsIgnoreCase(type)) {
            return new PatientService();
        }

        if ("doctor".equalsIgnoreCase(type)) {
            return new DoctorService();
        }

        if ("diagnosis".equalsIgnoreCase(type)) {
            return new DiagnosisService();
        }

        if ("treatment".equalsIgnoreCase(type)) {
            return new TreatmentService();
        }

        throw new IllegalArgumentException("Unknown service type: " + type);
    }
}