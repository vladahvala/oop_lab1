package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Nurse;
import com.hospital.service.NurseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/nurses")
public class NurseServlet extends HttpServlet {

    private final NurseService service = new NurseService();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(NurseServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        logger.info("GET /nurses called");

        String json = mapper.writeValueAsString(service.getAll());
        resp.getWriter().write(json);

        logger.info("GET /nurses success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        logger.info("POST /nurses called");

        Nurse n = mapper.readValue(req.getReader(), Nurse.class);

        service.add(n);

        logger.info("Nurse added: " + n.getFullName());

        resp.setContentType("text/plain");
        resp.getWriter().write("Nurse added!");
    }
}