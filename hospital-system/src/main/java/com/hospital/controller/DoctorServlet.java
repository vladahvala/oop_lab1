package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;
import com.hospital.service.DoctorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {

    private DoctorService service = new DoctorService();

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

        Doctor d = mapper.readValue(req.getReader(), Doctor.class);

        service.add(d);

        resp.setContentType("text/plain");
        resp.getWriter().write("Doctor added!");
    }
}