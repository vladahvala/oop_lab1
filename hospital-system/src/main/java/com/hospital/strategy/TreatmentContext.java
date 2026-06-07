package com.hospital.strategy;

import com.hospital.model.Treatment;

public class TreatmentContext {

    private TreatmentExecutionStrategy strategy;

    public void setStrategy(TreatmentExecutionStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(Treatment treatment) {
        if (strategy == null) {
            throw new IllegalStateException("Strategy is not set");
        }
        strategy.execute(treatment);
    }
}