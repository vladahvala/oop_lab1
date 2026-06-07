package com.hospital.service;

import com.hospital.dao.NurseDAO;
import com.hospital.model.Nurse;

import java.util.List;

public class NurseService {

    private NurseDAO dao = new NurseDAO();

    public List<Nurse> getAll() {
        return dao.getAll();
    }

    public void add(Nurse n) {
        dao.add(n);
    }
}