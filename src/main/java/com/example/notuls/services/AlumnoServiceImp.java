package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.AssignAlumnoToMateriaRequest;
import com.example.notuls.controllers.dtos.requests.AssignTutorToAlumnoRequest;
import com.example.notuls.controllers.dtos.requests.CreateAlumnoRequest;
import com.example.notuls.controllers.dtos.responses.AlumnoResponse;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Alumno;
import com.example.notuls.entities.Materia;
import com.example.notuls.repositories.IAlumnoRepository;
import com.example.notuls.services.interfaces.IAlumnoService;
import com.example.notuls.services.interfaces.IMateriaService;
import com.example.notuls.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class AlumnoServiceImp implements IAlumnoService {

    @Autowired
    private IAlumnoRepository repository;

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private IMateriaService materiaService;

    @Override
    public BaseResponse getAlumnosByMateriaId(Long id) {
        List<AlumnoResponse> alumnoResponses = repository.findByMateriasId(id).stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(alumnoResponses)
                .message("Lista de alumnos encontrada")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getAlumnoById(Long id) {
        AlumnoResponse alumnoResponse = from(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado")));
        return BaseResponse.builder()
                .data(alumnoResponse)
                .message("Alumnos encontrado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getAlumnosList() {
        List<AlumnoResponse> alumnosResponse = repository.findAll().stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(alumnosResponse)
                .message("Lista de alumnos encontrados")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getAlumnosByTutorId(Long id) {
        List<AlumnoResponse> alumnoResponses = repository.findAllByTutorId(id).stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(alumnoResponses)
                .message("Lista de alumnos por ID")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse createAlumno(CreateAlumnoRequest request) {
        AlumnoResponse alumnoResponse = from(create(request));
        return BaseResponse.builder()
                .data(alumnoResponse)
                .message("Alumno creado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse assignTutorToAlumno(Long id, AssignTutorToAlumnoRequest request) {
        Alumno alumno = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
        if(request.getTutorId() != null){
            alumno.setTutor(tutorService.findTutotById(request.getTutorId()));
        }
        AlumnoResponse response = from(repository.save(alumno));
        return BaseResponse.builder()
                .data(response)
                .message("Alumno asignado a tutor")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse assignAlumnoToMateria(Long id, AssignAlumnoToMateriaRequest request) {
        Alumno alumno = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
        AlumnoResponse response = new AlumnoResponse();
        if(request.getMateriaId() != null){
            List<Materia> materias = materiaService.findMateriasByAlumnoId(request.getMateriaId());
            materias.add(materiaService.findMateriaById(request.getMateriaId()));
            alumno.setMaterias(materias);
            response = from(repository.save(alumno));
        }
        return BaseResponse.builder()
                .data(response)
                .message("Alumnos asignado a una materia")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private AlumnoResponse from(Alumno alumno) {
        AlumnoResponse response = new AlumnoResponse();
        response.setId(alumno.getId());
        response.setName(alumno.getName());
        response.setEmail(alumno.getEmail());
        return response;
    }

    private Alumno create(CreateAlumnoRequest request) {
        Alumno alumno = new Alumno();
        alumno.setName(request.getName());
        alumno.setEmail(request.getEmail());
        return repository.save(alumno);
    }
}
