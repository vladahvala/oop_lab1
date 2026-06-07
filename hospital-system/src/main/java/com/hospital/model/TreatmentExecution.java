package com.hospital.model;

import java.time.LocalDateTime;

public class TreatmentExecution {
    private int id;
    private int treatmentId;
    private Integer nurseId;
    private Integer doctorId;
    private LocalDateTime executedAt;

    public TreatmentExecution() {
    }

    public TreatmentExecution(int id, int treatmentId, Integer nurseId, Integer doctorId, LocalDateTime executedAt) {
        this.id = id;
        this.treatmentId = treatmentId;
        this.nurseId = nurseId;
        this.doctorId = doctorId;
        this.executedAt = executedAt;
    }

    public int getId() {
        return id;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public Integer getNurseId() {
        return nurseId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setNurseId(Integer nurseId) {
        this.nurseId = nurseId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }
}