package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private final DoctorService service = new DoctorService();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(DoctorServlet.class);

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("GET /doctors called");

        try {
            String json = mapper.writeValueAsString(service.getAll());
            resp.getWriter().write(json);

            logger.info("GET /doctors success");

        } catch (Exception e) {
            logger.error("Error in GET /doctors: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"server error\"}");
        }
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        logger.info("POST /doctors called");

        try {
            Doctor d = mapper.readValue(req.getReader(), Doctor.class);

            logger.info("Incoming doctor: " + d.getFullName());

            service.add(d);

            logger.info("Doctor saved: " + d.getFullName());

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Doctor added!\"}");

        } catch (Exception e) {
            logger.error("Error while adding doctor: " + e.getMessage());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}