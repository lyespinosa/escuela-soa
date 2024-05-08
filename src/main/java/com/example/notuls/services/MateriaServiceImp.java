package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateMateriaRequest;
import com.example.notuls.controllers.dtos.responses.AlumnoResponse;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.MateriaResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Materia;
import com.example.notuls.repositories.IMateriaRepository;
import com.example.notuls.services.interfaces.IAlumnoService;
import com.example.notuls.services.interfaces.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaServiceImp implements IMateriaService {

    @Autowired
    private IMateriaRepository repository;

    @Override
    public Materia findMateriaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new Excepcion("Tutor no encontrado"));
    }

    @Override
    public List<Materia> findMateriasByAlumnoId(Long alumnoId) {
        return repository.findMateriaByAlumnosId(alumnoId);
    }

    @Override
    public BaseResponse createMateria(CreateMateriaRequest request) {
        MateriaResponse response = from(create(request));
        return BaseResponse.builder()
                .data(response)
                .message("Materia creada")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    private MateriaResponse from(Materia materia) {
        MateriaResponse materiaResponse = new MateriaResponse();
        materiaResponse.setId(materia.getId());
        materiaResponse.setName(materia.getName());
        return materiaResponse;
    }

    private Materia create(CreateMateriaRequest request){
        Materia materia = new Materia();
        materia.setName(request.getName());
        return repository.save(materia);
    }

}
