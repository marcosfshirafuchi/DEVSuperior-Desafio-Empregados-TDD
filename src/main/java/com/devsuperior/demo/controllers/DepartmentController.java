package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Definindo a classe de Controller
@RestController
//Definindo a rota classe controller no Postman
@RequestMapping(value = "/departments")
public class DepartmentController {
    //Injetar automáticamente a dependencia
    @Autowired
    private DepartmentService service;

    @GetMapping
    //O ResponseEntity vai encapsular uma resposta HTTP
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        List<DepartmentDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
