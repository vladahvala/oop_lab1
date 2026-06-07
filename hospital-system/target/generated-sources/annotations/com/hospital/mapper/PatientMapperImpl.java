package com.hospital.mapper;

import com.hospital.dto.PatientDto;
import com.hospital.model.Patient;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-07T22:01:42+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Eclipse Adoptium)"
)
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDto toDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDto patientDto = new PatientDto();

        patientDto.setId( patient.getId() );
        patientDto.setFullName( patient.getFullName() );
        patientDto.setBirthDate( patient.getBirthDate() );
        patientDto.setStatus( patient.getStatus() );

        return patientDto;
    }

    @Override
    public Patient toEntity(PatientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Patient.PatientBuilder patient = Patient.builder();

        patient.id( dto.getId() );
        patient.fullName( dto.getFullName() );
        patient.birthDate( dto.getBirthDate() );
        patient.status( dto.getStatus() );

        return patient.build();
    }
}
