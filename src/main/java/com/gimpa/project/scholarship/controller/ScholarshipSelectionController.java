package com.gimpa.project.scholarship.controller;

import com.gimpa.project.scholarship.model.FinalGradePoints;
import com.gimpa.project.scholarship.service.ScholarshipSelectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@Slf4j
@RequestMapping("/selection")
public class ScholarshipSelectionController {
    @Autowired
    ScholarshipSelectionService scholarshipSelectionService;

    @GetMapping("/all")
    public String listAllScholarships (Model model){
        model.addAttribute("scholarships", scholarshipSelectionService.getAllScholarshipApplication());
        return "admin_dashboard";
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