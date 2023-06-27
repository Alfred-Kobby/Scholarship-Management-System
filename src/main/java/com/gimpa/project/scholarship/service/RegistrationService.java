package com.gimpa.project.scholarship.service;

import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.RegistrationRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.repository.UsersRepository;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationService {
    @Autowired
    UsersRepository usersRepository;

    public ServiceResponse processUserRegistration(RegistrationRequest registrationRequest){
        log.info("Incoming registration request: {}", JsonUtility.toJson(registrationRequest));
        try{
            User user = new User(registrationRequest);
            User savedUser = usersRepository.save(user);
            log.info("Successfully saved User: {}", JsonUtility.toJson(savedUser));
            return new ServiceResponse("01", "Registration Successful");
        }
        catch (Exception e){
            log.error("An exception occurred in saving user: {}", e);
            return new ServiceResponse("100", "Registration Failed");
        }
    }
}
