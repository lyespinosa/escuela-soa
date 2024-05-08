package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.AssignAlumnoToMateriaRequest;
import com.example.notuls.controllers.dtos.requests.AssignTutorToAlumnoRequest;
import com.example.notuls.controllers.dtos.requests.CreateAlumnoRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Alumno;

import java.util.List;

public interface IAlumnoService {

    BaseResponse getAlumnosByMateriaId(Long id);

    BaseResponse getAlumnoById(Long id);

    BaseResponse getAlumnosList();

    BaseResponse getAlumnosByTutorId(Long id);

    BaseResponse createAlumno(CreateAlumnoRequest request);

    BaseResponse assignTutorToAlumno(Long id, AssignTutorToAlumnoRequest request);

    BaseResponse assignAlumnoToMateria(Long id, AssignAlumnoToMateriaRequest request);
}
