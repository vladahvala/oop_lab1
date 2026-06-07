package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment {
    private int id;
    private int diagnosisId;
    private String type;
    private String description;
    private String assignedByRole;
    private String status;
}