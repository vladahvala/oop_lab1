package com.hospital.strategy;

import com.hospital.model.Treatment;

public interface TreatmentExecutionStrategy {
    void execute(Treatment treatment);
}