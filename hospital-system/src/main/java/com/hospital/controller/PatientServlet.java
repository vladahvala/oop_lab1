package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private final PatientService service = new PatientService();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(PatientServlet.class);

    // GET /patients
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("GET /patients called");

        try {
            String json = mapper.writeValueAsString(service.getAll());
            resp.getWriter().write(json);

            logger.info("GET /patients success");

        } catch (Exception e) {
            logger.error("Error in GET /patients: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Server error\"}");
        }
    }

    // POST /patients
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("POST /patients called");

        try {
            // читаємо JSON
            Patient patient = mapper.readValue(req.getReader(), Patient.class);

            logger.info("Incoming patient: " + patient.getFullName());

            // дефолтний статус
            patient.setStatus("ADMITTED");

            // збереження в БД
            service.add(patient);

            logger.info("Patient saved: " + patient.getFullName());

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Patient added!\"}");

        } catch (Exception e) {

            logger.error("Error while adding patient: " + e.getMessage());

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}