package com.integrador.controllers;

import com.integrador.models.DepartmentModel;
import com.integrador.repository.DepartmentRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/departamentos")
    public List<DepartmentModel> getDepartamentos() {
        return departmentRepository.findAll();
    }

    @PostMapping("/adddepart")
    public String addDepartamento(@RequestBody DepartmentModel departmentModel) {
        if (departmentModel.getName_depart() == null) {
            return "Bad Request. Please fill all fields.";
        }

        departmentRepository.save(departmentModel);
        return "Departamento registrado correctamente";
    }
}
