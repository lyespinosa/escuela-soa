package com.example.notuls.controllers;

import com.example.notuls.controllers.dtos.requests.CreateTutorRequest;
import com.example.notuls.controllers.dtos.responses.BaseResponse;
import com.example.notuls.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tutor")
public class TutorController {

    @Autowired
    private ITutorService service;

    @GetMapping
    public ResponseEntity<BaseResponse> getTutoresList(){
        BaseResponse baseResponse = service.getTutoresList();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createTutor(@RequestBody CreateTutorRequest request){
        BaseResponse baseResponse = service.createTutor(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}
