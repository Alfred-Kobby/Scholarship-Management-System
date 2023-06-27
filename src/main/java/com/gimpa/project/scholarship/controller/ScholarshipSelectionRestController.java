package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.entity.ApprovedScholarship;
import com.gimpa.project.scholarship.model.FinalGradePoints;
import com.gimpa.project.scholarship.service.ScholarshipSelectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/selection")
public class ScholarshipSelectionRestController {
    @Autowired
    ScholarshipSelectionService scholarshipSelectionService;

    @GetMapping("/api-total-grades")
    public List<FinalGradePoints> getScholarshipTotalGrades(){
        return scholarshipSelectionService.rankScholarshipApplications();
    }

    @GetMapping("/api-process-scholarship")
    public Iterable<ApprovedScholarship> processScholarship(){
        return scholarshipSelectionService.processFinalScholarshipApplication();
    }
}