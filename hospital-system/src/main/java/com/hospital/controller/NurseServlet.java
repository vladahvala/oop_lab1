package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Nurse;
import com.hospital.service.NurseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/nurses")
public class NurseServlet extends HttpServlet {

    private NurseService service = new NurseService();

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

        Nurse n = mapper.readValue(req.getReader(), Nurse.class);

        service.add(n);

        resp.setContentType("text/plain");
        resp.getWriter().write("Nurse added!");
    }
}