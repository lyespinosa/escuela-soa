package com.example.notuls.services;

import com.example.notuls.controllers.dtos.requests.CreateTutorRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.controllers.dtos.responses.TutorResponse;
import com.example.notuls.controllers.excepcion.Excepcion;
import com.example.notuls.entities.Tutor;
import com.example.notuls.repositories.ITutorRepository;
import com.example.notuls.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TutorServiceImp implements ITutorService {

    @Autowired
    private ITutorRepository repository;

    @Override
    public Tutor findTutotById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor no encontrado"));
    }

    @Override
    public BaseResponse getTutoresList() {
        List<TutorResponse> tutoresResponse = repository.findAll().stream().map(this::from).toList();
        return BaseResponse.builder()
                .data(tutoresResponse)
                .message("Lista de tutores encontrada")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse createTutor(CreateTutorRequest request) {
        Tutor tutor = new Tutor();
        create(tutor, request);
        TutorResponse tutorResponse = from(repository.save(tutor));
        return BaseResponse.builder()
                .data(tutorResponse)
                .message("Tutor creado")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    private TutorResponse from(Tutor tutor) {
        TutorResponse tutorResponse = new TutorResponse();
        tutorResponse.setId(tutor.getId());
        tutorResponse.setName(tutor.getName());
        tutorResponse.setEmail(tutor.getEmail());
        return tutorResponse;
    }

    private void create(Tutor tutor, CreateTutorRequest request ) {
        tutor.setName(request.getName());
        tutor.setEmail(request.getEmail());
    }
}
