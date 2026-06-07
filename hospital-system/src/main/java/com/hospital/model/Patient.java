package com.hospital.model;

public class Patient {
    private int id;
    private String fullName;
    private String birthDate;
    private String status;

    public Patient() {
    }

    public Patient(int id, String fullName, String birthDate, String status) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
