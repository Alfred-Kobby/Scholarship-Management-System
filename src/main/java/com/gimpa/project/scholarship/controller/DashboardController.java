package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.LoginRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.repository.UsersRepository;
import com.gimpa.project.scholarship.service.LoginService;
import com.gimpa.project.scholarship.service.ScholarshipDashboardService;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class DashboardController {
    @Autowired
    ScholarshipDashboardService scholarshipDashboardService;
    @Autowired
    LoginService loginService;

    @PostMapping("/dashboard")
    public String getScholarshipsAppliedFor(@ModelAttribute("user") User user,
                                            BindingResult result,
                                            Model model){
        LoginRequest loginRequest = new LoginRequest(user.getEmail(), user.getPassword());
        ServiceResponse loginResponse = loginService.processLogin(loginRequest);
        log.info("LoginResponse: {}", JsonUtility.toJson(loginResponse));
        if(loginResponse.getResponseCode().equalsIgnoreCase("01")){
            List<ScholarshipApplication> scholarshipApplication = scholarshipDashboardService.getScholarshipsAppliedFor(loginRequest);
            model.addAttribute("scholarships", scholarshipApplication);
            return "applicant_dashboard";
        }
        else{
            return "redirect:/?error";
        }
    }

    @GetMapping("/dashboard/{id}")
    public String returnToDashboard(@PathVariable String id, Model model){
        Long scholarshipId = Long.parseLong(id);
        ScholarshipApplication scholarshipApplication = scholarshipDashboardService.getScholarshipApplication(scholarshipId);
        List<ScholarshipApplication> scholarshipApplications = scholarshipDashboardService.getScholarshipsAppliedFor(scholarshipApplication.getEmail());
        model.addAttribute("scholarships", scholarshipApplications);
        return "applicant_dashboard";
    }
}
