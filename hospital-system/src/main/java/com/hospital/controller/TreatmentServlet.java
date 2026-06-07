package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Treatment;
import com.hospital.service.TreatmentService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/treatments")
public class TreatmentServlet extends HttpServlet {

    private TreatmentService service = new TreatmentService();

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

        Treatment t = mapper.readValue(req.getReader(), Treatment.class);

        t.setStatus("PENDING");

        service.add(t);

        resp.setContentType("text/plain");
        resp.getWriter().write("Treatment added!");
    }
}