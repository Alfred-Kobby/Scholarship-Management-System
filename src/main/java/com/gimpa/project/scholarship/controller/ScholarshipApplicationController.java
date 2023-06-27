package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.entity.Results;
import com.gimpa.project.scholarship.entity.User;
import com.gimpa.project.scholarship.model.*;
import com.gimpa.project.scholarship.service.ScholarshipApplicationService;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/scholarship")
public class ScholarshipApplicationController {
    @Autowired
    ScholarshipApplicationService scholarshipApplicationService;

    @GetMapping("/apply-form")
    public String registerUser(){
        return "apply_for_scholarship";
    }

    @PostMapping("/apply")
    public String processScholarshipApplication(@ModelAttribute("scholarship") ScholarshipApplicationRequest scholarshipApplicationRequest,
                                                BindingResult result, Model model){
        ScholarshipApplicationResponse response = scholarshipApplicationService.processScholarshipApplication(scholarshipApplicationRequest);
        if(response.getResponseCode().equalsIgnoreCase("01")){
            return "redirect:/scholarship/upload-result-form?id=".concat(response.getScholarshipApplicationId().toString());
        }
        else {
            return "redirect:/scholarship/apply-form?error";
        }
    }

    @GetMapping("/upload-result-form")
    public String uploadResults(){
        return "add_results";
    }

    @PostMapping("/upload-result")
    public String processUploadResult(@ModelAttribute("result") AddResultRequest addResultRequest,
                                                BindingResult resultSet, Model model){
        log.info("Incoming addResultRequest: {}", JsonUtility.toJson(addResultRequest));
        Result result = new Result(addResultRequest);
        ScholarshipApplicationResponse response = scholarshipApplicationService.addResults(result,Long.parseLong(addResultRequest.getScholarshipId()));
        if(response.getResponseCode().equalsIgnoreCase("01")){
            return "redirect:/scholarship/upload-result-form?success&id=".concat(response.getScholarshipApplicationId().toString());
        }
        else {
            return "redirect:/scholarship/upload-result-form?error";
        }
    }
}
