package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Diagnosis;
import com.hospital.service.DiagnosisService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/diagnoses")
public class DiagnosisServlet extends HttpServlet {

    private DiagnosisService service = new DiagnosisService();

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(service.getAll());

        resp.getWriter().write(json);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        Diagnosis d = mapper.readValue(req.getReader(), Diagnosis.class);

        service.add(d);

        resp.setContentType("text/plain");
        resp.getWriter().write("Diagnosis added!");
    }
}