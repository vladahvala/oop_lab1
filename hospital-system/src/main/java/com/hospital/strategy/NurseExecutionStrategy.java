package com.hospital.strategy;

import com.hospital.model.Treatment;

public class NurseExecutionStrategy implements TreatmentExecutionStrategy {

    @Override
    public void execute(Treatment treatment) {
        System.out.println("NURSE executes allowed treatment: "
                + treatment.getType() + " - " + treatment.getDescription());
    }
}