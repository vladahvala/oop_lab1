package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private final PatientService service = new PatientService();
    private final ObjectMapper mapper = new ObjectMapper();

    // GET /patients
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String json = mapper.writeValueAsString(service.getAll());

        resp.getWriter().write(json);
    }

    // POST /patients
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            // читаємо JSON з запиту
            Patient patient = mapper.readValue(req.getReader(), Patient.class);

            // дефолтний статус
            patient.setStatus("ADMITTED");

            // зберігаємо в БД
            service.add(patient);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write("{\"message\":\"Patient added!\"}");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}