package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public Department getById(String id){
        return departmentRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Could not found this department"
                )
        );
    }
}
