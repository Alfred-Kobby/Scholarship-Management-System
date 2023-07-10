package com.gimpa.project.scholarship.service;

import com.gimpa.project.scholarship.entity.Admin;
import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.GeneralResponse;
import com.gimpa.project.scholarship.model.LoginRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.repository.AdminsRepository;
import com.gimpa.project.scholarship.repository.ScholarshipApplicationRepository;
import com.gimpa.project.scholarship.repository.UsersRepository;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class LoginService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    AdminsRepository adminsRepository;

    public ServiceResponse processLogin(LoginRequest loginRequest){
        log.info("Incoming login request: {}", JsonUtility.toJson(loginRequest));
        try {
            User foundUser = usersRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
            log.info("Found user: {}", JsonUtility.toJson(foundUser));
            if(Objects.nonNull(foundUser)){
                return new ServiceResponse("01","Login successful");
            }
            else{
                return new ServiceResponse("100","Incorrect email/password.");
            }
        }
        catch (Exception e){
            log.error("An exception occurred in processLogin: {}", e);
            return new ServiceResponse("100","Internal Error");
        }
    }

    public ServiceResponse processAdminLogin(LoginRequest loginRequest){
        log.info("Incoming Admin login request: {}", JsonUtility.toJson(loginRequest));
        try {
            Admin foundUser = adminsRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
            log.info("Found user: {}", JsonUtility.toJson(foundUser));
            if(Objects.nonNull(foundUser)){
                return new ServiceResponse("01","Login successful");
            }
            else{
                return new ServiceResponse("100","Incorrect email/password.");
            }
        }
        catch (Exception e){
            log.error("An exception occurred in processLogin: {}", e);
            return new ServiceResponse("100","Internal Error");
        }
    }
}