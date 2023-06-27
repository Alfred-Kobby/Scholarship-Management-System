package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.RegistrationRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.model.UserDto;
import com.gimpa.project.scholarship.service.RegistrationService;
import com.gimpa.project.scholarship.service.ScholarshipDashboardService;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    ScholarshipDashboardService scholarshipDashboardService;

    @GetMapping("/register")
    public String registerUser(){
        return "register";
    }

    @PostMapping("/save-register")
    public String registerUser(@ModelAttribute("user") UserDto user,
                                        BindingResult result,
                                        Model model){
        if(user.getPassword().equalsIgnoreCase(user.getConfirmPassword())){
            RegistrationRequest registrationRequest = new RegistrationRequest(user);
            ServiceResponse response = registrationService.processUserRegistration(registrationRequest);
            log.info("Registration Response: {}", JsonUtility.toJson(response));
            if(response.getResponseCode().equalsIgnoreCase("01")){
                List<ScholarshipApplication> scholarshipApplication = scholarshipDashboardService.getScholarshipsAppliedFor(registrationRequest.getEmail());
                model.addAttribute("scholarships", scholarshipApplication);
                return "applicant_dashboard";
            }
            else{
                return "redirect:/register?error";
            }
        }
        else {
            return "redirect:/register?error";
        }
    }
}