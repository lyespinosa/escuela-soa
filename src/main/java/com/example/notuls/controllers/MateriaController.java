package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateMateriaRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Materia;
import com.example.notuls.services.interfaces.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    private IMateriaService service;



    @PostMapping
    public ResponseEntity<BaseResponse> createMateria(@RequestBody CreateMateriaRequest request) {
        BaseResponse baseResponse = service.createMateria(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
