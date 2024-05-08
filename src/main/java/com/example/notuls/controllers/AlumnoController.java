package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.AssignAlumnoToMateriaRequest;
import com.example.notuls.controllers.dtos.requests.AssignTutorToAlumnoRequest;
import com.example.notuls.controllers.dtos.requests.CreateAlumnoRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alumno")
public class AlumnoController {

    @Autowired
    private IAlumnoService service;

    @GetMapping
    public ResponseEntity<BaseResponse> getAlumnosList(){
        BaseResponse baseResponse = service.getAlumnosList();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getAlumnoById(@PathVariable Long id) {
        BaseResponse baseResponse = service.getAlumnoById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/by-tutor/{id}")
    public ResponseEntity<BaseResponse> getAlumnosByTutorId(@PathVariable Long id){
        BaseResponse baseResponse = service.getAlumnosByTutorId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAlumno(@RequestBody CreateAlumnoRequest request){
        BaseResponse baseResponse = service.createAlumno(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("/{id}/to-tutor")
    public ResponseEntity<BaseResponse> assignTutorToAlumno(@PathVariable Long id, @RequestBody AssignTutorToAlumnoRequest request){
        BaseResponse baseResponse =  service.assignTutorToAlumno(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PatchMapping("/{id}/to-materia")
    public ResponseEntity<BaseResponse> assignAlumnoToMateria(@PathVariable Long id, @RequestBody AssignAlumnoToMateriaRequest request){
        BaseResponse baseResponse = service.assignAlumnoToMateria(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/by-materia/{id}")
    public ResponseEntity<BaseResponse> getAlumnosByMateriaId(@PathVariable Long id) {
        BaseResponse baseResponse = service.getAlumnosByMateriaId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }




}
