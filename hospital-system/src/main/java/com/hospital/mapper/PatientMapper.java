package com.hospital.mapper;

import com.hospital.dto.PatientDto;
import com.hospital.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "default")
public interface PatientMapper {

    PatientDto toDto(Patient patient);

    Patient toEntity(PatientDto dto);
}