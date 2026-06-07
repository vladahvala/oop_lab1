package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.service.PatientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/patients/discharge")
public class PatientDischargeServlet extends HttpServlet {

    private final PatientService service = new PatientService();
    private final ObjectMapper mapper = new ObjectMapper();

    static class Request {
        public int patientId;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Request r = mapper.readValue(req.getReader(), Request.class);

        service.discharge(r.patientId);

        resp.setContentType("application/json");
        resp.getWriter().write("{\"message\":\"Patient discharged\"}");
    }
}