package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Treatment;
import com.hospital.service.TreatmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/treatments")
public class TreatmentServlet extends HttpServlet {

    private final TreatmentService service = new TreatmentService();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(TreatmentServlet.class);

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("GET /treatments called");

        try {
            String json = mapper.writeValueAsString(service.getAll());
            resp.getWriter().write(json);

            logger.info("GET /treatments success");

        } catch (Exception e) {
            logger.error("Error in GET treatments: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("POST /treatments called");

        try {
            Treatment t = mapper.readValue(req.getReader(), Treatment.class);

            t.setStatus("PENDING");

            logger.info("Incoming treatment for diagnosisId=" + t.getDiagnosisId());

            service.add(t);

            logger.info("Treatment saved");

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Treatment added!\"}");

        } catch (Exception e) {
            logger.error("Error while adding treatment: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}