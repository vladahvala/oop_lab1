package com.hospital.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    private static final String SECRET = "secret123";
    private final ObjectMapper mapper = new ObjectMapper();

    static class LoginRequest {
        public String role;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // читаємо JSON
        LoginRequest body = mapper.readValue(req.getReader(), LoginRequest.class);

        if (body.role == null) {
            resp.setStatus(400);
            resp.getWriter().write("{\"error\":\"role is required\"}");
            return;
        }

        String token = JWT.create()
                .withClaim("role", body.role)
                .sign(Algorithm.HMAC256(SECRET));

        resp.getWriter().write("{\"token\":\"" + token + "\"}");
    }
}