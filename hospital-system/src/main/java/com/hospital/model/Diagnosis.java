package com.hospital.model;

public class Diagnosis {
    private int id;
    private int patientId;
    private int doctorId;
    private String description;
    private boolean finalDiagnosis;

    public Diagnosis() {
    }

    public Diagnosis(int id, int patientId, int doctorId, String description, boolean finalDiagnosis) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.finalDiagnosis = finalDiagnosis;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinalDiagnosis(boolean finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }
}