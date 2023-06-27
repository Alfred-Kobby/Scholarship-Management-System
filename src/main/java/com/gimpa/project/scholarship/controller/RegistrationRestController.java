package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.model.RegistrationRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationRestController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/api-register")
    public ServiceResponse registerUser(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.processUserRegistration(registrationRequest);
    }
}