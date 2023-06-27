package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.model.ScholarshipApplicationWithResultsRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.service.ScholarshipApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/scholarship")
public class ScholarshipApplicationRestController {
    @Autowired
    ScholarshipApplicationService scholarshipApplicationService;

    @PostMapping("/api-apply")
    public ServiceResponse processScholarshipApplication(@RequestBody ScholarshipApplicationWithResultsRequest scholarshipApplicationWithResultsRequest){
        return scholarshipApplicationService.processScholarshipApplication(scholarshipApplicationWithResultsRequest);
    }
}
