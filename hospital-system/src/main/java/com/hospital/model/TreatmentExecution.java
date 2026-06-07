package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentExecution {
    private int id;
    private int treatmentId;
    private Integer nurseId;
    private Integer doctorId;
    private LocalDateTime executedAt;
}