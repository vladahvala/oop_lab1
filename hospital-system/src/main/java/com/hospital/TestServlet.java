package com.hospital;

import com.hospital.util.DBConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");

        try {
            Connection conn = DBConnection.getConnection();
            resp.getWriter().write("DB connected!");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("DB error: " + e.getMessage());
        }
    }
}