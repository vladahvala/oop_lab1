package com.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hospital.model.TreatmentExecution;
import com.hospital.service.TreatmentExecutionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/executions")
public class TreatmentExecutionServlet extends HttpServlet {

    private TreatmentExecutionService service = new TreatmentExecutionService();

    // створюємо один нормальний mapper
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        String json = mapper.writeValueAsString(service.getAll());

        resp.getWriter().write(json);
    }

    // POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        TreatmentExecution t = mapper.readValue(req.getReader(), TreatmentExecution.class);

        service.add(t);

        resp.setContentType("text/plain");
        resp.getWriter().write("Execution added!");
    }
}