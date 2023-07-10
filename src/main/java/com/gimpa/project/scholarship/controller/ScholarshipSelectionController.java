package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.FinalGradePoints;
import com.gimpa.project.scholarship.model.LoginRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.service.LoginService;
import com.gimpa.project.scholarship.service.ScholarshipSelectionService;
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
@RequestMapping("/selection")
public class ScholarshipSelectionController {
    @Autowired
    LoginService loginService;
    @Autowired
    ScholarshipSelectionService scholarshipSelectionService;

    @GetMapping("/all")
    public String listAllScholarships (Model model){
        model.addAttribute("scholarships", scholarshipSelectionService.getAllScholarshipApplication());
        return "admin_dashboard";
    }

    @PostMapping("/admin")
    public String listAllScholarships (@ModelAttribute("user") User user,
                                       BindingResult result,
                                       Model model){
        LoginRequest loginRequest = new LoginRequest(user.getEmail(), user.getPassword());
        log.info("LoginRequest: {}", JsonUtility.toJson(loginRequest));
        ServiceResponse loginResponse = loginService.processAdminLogin(loginRequest);
        log.info("LoginResponse: {}", JsonUtility.toJson(loginResponse));
        if(loginResponse.getResponseCode().equalsIgnoreCase("01")){
            model.addAttribute("scholarships", scholarshipSelectionService.getAllScholarshipApplication());
            return "admin_dashboard";
        }
        else{
            return "redirect:/admin/login?error";
        }
    }

    @GetMapping("/results/{id}")
    public String listResultsForApplicant(@PathVariable String id, Model model){
        model.addAttribute("results", scholarshipSelectionService.getResultsForApplicant(id));
        return "view_results";
    }

    @GetMapping("/total-grades")
    public List<FinalGradePoints> getScholarshipTotalGrades(){
        return scholarshipSelectionService.rankScholarshipApplications();
    }

    @GetMapping("/process-scholarship")
    public String listResultsForApplicant(Model model){
        model.addAttribute("approvedScholarships", scholarshipSelectionService.processFinalScholarshipApplication());
        return "approved_scholarships";
    }
}