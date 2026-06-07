package com.hospital.strategy;

import com.hospital.model.Treatment;

public class DoctorExecutionStrategy implements TreatmentExecutionStrategy {

    @Override
    public void execute(Treatment treatment) {
        System.out.println("DOCTOR executes full treatment: "
                + treatment.getType() + " - " + treatment.getDescription());
    }
}