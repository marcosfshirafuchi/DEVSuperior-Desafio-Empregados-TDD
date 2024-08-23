package com.devsuperior.demo.controllers;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.services.DepartmentService;
import com.devsuperior.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//Definindo a classe de Controller
@RestController
//Definindo a rota classe controller no Postman
@RequestMapping(value = "/employees")
public class EmployeeController {
    //Injetar automáticamente a dependencia
    @Autowired
    private EmployeeService service;

    @GetMapping
    //O ResponseEntity vai encapsular uma resposta HTTP
    public ResponseEntity<Page<EmployeeDTO>> findAll(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
        Page<EmployeeDTO> list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
