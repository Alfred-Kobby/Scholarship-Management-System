package com.gimpa.project.scholarship.service;

import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.model.GeneralResponse;
import com.gimpa.project.scholarship.model.LoginRequest;
import com.gimpa.project.scholarship.model.RegistrationRequest;
import com.gimpa.project.scholarship.model.ServiceResponse;
import com.gimpa.project.scholarship.repository.ApprovedScholarshipRepository;
import com.gimpa.project.scholarship.repository.ResultsRepository;
import com.gimpa.project.scholarship.repository.ScholarshipApplicationRepository;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScholarshipDashboardService {
    @Autowired
    ScholarshipApplicationRepository scholarshipApplicationRepository;
    @Autowired
    ApprovedScholarshipRepository approvedScholarshipRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    ResultsRepository resultsRepository;

    public ScholarshipApplication getScholarshipApplication(Long id){
        return scholarshipApplicationRepository.findById(id).get();
    }

    public List<ScholarshipApplication> getScholarshipsAppliedFor(LoginRequest loginRequest){
        List<ScholarshipApplication> scholarshipApplication = new ArrayList<>();
        try{
            scholarshipApplication = scholarshipApplicationRepository.findByEmail(loginRequest.getEmail());
            log.info("Retrieved scholarship Application: {}", JsonUtility.toJson(scholarshipApplication));
        }
        catch (Exception e){
            log.error("An exception occrred in getScholarshipsAppliedFor: {}", e);
        }
        return scholarshipApplication;
    }

    public List<ScholarshipApplication> getScholarshipsAppliedFor(String email){
        List<ScholarshipApplication> scholarshipApplication = new ArrayList<>();
        try{
            scholarshipApplication = scholarshipApplicationRepository.findByEmail(email);
            log.info("Retrieved scholarship Application: {}", JsonUtility.toJson(scholarshipApplication));
        }
        catch (Exception e){
            log.error("An exception occrred in getScholarshipsAppliedFor: {}", e);
        }
        return scholarshipApplication;
    }
}
