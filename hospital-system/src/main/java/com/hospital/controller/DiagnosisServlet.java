package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Diagnosis;
import com.hospital.service.DiagnosisService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/diagnoses")
public class DiagnosisServlet extends HttpServlet {

    private final DiagnosisService service = new DiagnosisService();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(DiagnosisServlet.class);

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("GET /diagnoses called");

        try {
            String json = mapper.writeValueAsString(service.getAll());
            resp.getWriter().write(json);

            logger.info("GET /diagnoses success");

        } catch (Exception e) {
            logger.error("Error in GET /diagnoses: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("POST /diagnoses called");

        try {
            Diagnosis d = mapper.readValue(req.getReader(), Diagnosis.class);

            logger.info("Incoming diagnosis for patientId=" + d.getPatientId());

            service.add(d);

            logger.info("Diagnosis saved");

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Diagnosis added!\"}");

        } catch (Exception e) {
            logger.error("Error while adding diagnosis: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}