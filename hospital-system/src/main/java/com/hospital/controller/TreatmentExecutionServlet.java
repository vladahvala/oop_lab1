package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hospital.model.TreatmentExecution;
import com.hospital.service.TreatmentExecutionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/executions")
public class TreatmentExecutionServlet extends HttpServlet {

    private final TreatmentExecutionService service = new TreatmentExecutionService();
    private static final Logger logger = LogManager.getLogger(TreatmentExecutionServlet.class);

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        logger.info("GET /executions called");

        String json = mapper.writeValueAsString(service.getAll());

        resp.getWriter().write(json);

        logger.info("GET /executions success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        logger.info("POST /executions called");

        TreatmentExecution t = mapper.readValue(req.getReader(), TreatmentExecution.class);

        logger.info("Incoming execution for treatmentId=" + t.getTreatmentId());

        String role = req.getHeader("Role");

        service.executeTreatment(t, role);

        logger.info("Execution saved");

        resp.setContentType("text/plain");
        resp.getWriter().write("Execution added!");
    }
}