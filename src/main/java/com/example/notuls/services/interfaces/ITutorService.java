package com.example.notuls.services.interfaces;

import com.example.notuls.controllers.dtos.requests.CreateTutorRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.entities.Tutor;

public interface ITutorService {

    Tutor findTutotById(Long id);

    BaseResponse getTutoresList();

    BaseResponse createTutor(CreateTutorRequest request);
}
