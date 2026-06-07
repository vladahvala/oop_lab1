package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis {
    private int id;
    private int patientId;
    private int doctorId;
    private String description;
    private boolean finalDiagnosis;
}