package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateMateriaRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Materia;

import java.util.List;

public interface IMateriaService {

    Materia findMateriaById(Long id);

    List<Materia> findMateriasByAlumnoId(Long alumnoId);

    BaseResponse createMateria(CreateMateriaRequest request);
}
