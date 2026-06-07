package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.dto.PatientDto;
import com.hospital.mapper.PatientMapper;
import com.hospital.mapper.PatientMapperImpl;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private final PatientService service = new PatientService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final PatientMapper mapper = new PatientMapperImpl();

    private static final Logger logger = LogManager.getLogger(PatientServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("GET /patients called");

        String json = objectMapper.writeValueAsString(
                service.getAll()
                        .stream()
                        .map(mapper::toDto)
                        .toList());

        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("POST /patients called");

        PatientDto dto = objectMapper.readValue(req.getReader(), PatientDto.class);

        Patient patient = mapper.toEntity(dto);

        // 🔥 головне: ти САМ задаєш статус
        patient.setStatus("ADMITTED");

        service.add(patient);

        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("{\"message\":\"Patient added\"}");
    }
}