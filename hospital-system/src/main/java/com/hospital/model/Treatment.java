package com.hospital.model;

public class Treatment {
    private int id;
    private int diagnosisId;
    private String type;
    private String description;
    private String assignedByRole;
    private String status;

    public Treatment() {
    }

    public Treatment(int id, int diagnosisId, String type, String description,
            String assignedByRole, String status) {
        this.id = id;
        this.diagnosisId = diagnosisId;
        this.type = type;
        this.description = description;
        this.assignedByRole = assignedByRole;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedByRole() {
        return assignedByRole;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedByRole(String assignedByRole) {
        this.assignedByRole = assignedByRole;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}