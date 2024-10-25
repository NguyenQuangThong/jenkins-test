package com.example.StudyJava.Services;

import com.example.StudyJava.Repositories.ManufactureRepository;
import com.example.StudyJava.Models.Manufacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureService {
    @Autowired
    private ManufactureRepository manufactureRepository;

    public List<Manufacture> getAllManufactures() {
        return manufactureRepository.findAll();
    }

    public Manufacture getManufactureById(long id) {
        return manufactureRepository.findById(id).get();
    }
}
