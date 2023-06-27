package com.gimpa.project.scholarship.service;

import com.gimpa.project.scholarship.entity.Results;
import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.model.*;
import com.gimpa.project.scholarship.repository.ResultsRepository;
import com.gimpa.project.scholarship.repository.ScholarshipApplicationRepository;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ScholarshipApplicationService {
    @Autowired
    ScholarshipApplicationRepository scholarshipApplicationRepository;
    @Autowired
    ResultsRepository resultsRepository;

    public ServiceResponse processScholarshipApplication(ScholarshipApplicationWithResultsRequest scholarshipApplicationWithResultsRequest){
        log.info("Incoming scholarship application request: {}", JsonUtility.toJson(scholarshipApplicationWithResultsRequest));
        try{
            List<Results> results = new ArrayList<>();
            ScholarshipApplication scholarshipApplication = new ScholarshipApplication(scholarshipApplicationWithResultsRequest);
            ScholarshipApplication savedScholarshipApplication = scholarshipApplicationRepository.save(scholarshipApplication);
            log.info("Saved scholarship application: {}", JsonUtility.toJson(savedScholarshipApplication));

            scholarshipApplicationWithResultsRequest.getResults().forEach(x->{
                Results toBeSaved = new Results(x, savedScholarshipApplication.getId());
                results.add(toBeSaved);
            });

            Iterable<Results> savedResults = resultsRepository.saveAll(results);
            log.info("Saved Results for scholarshipId: {} are; {}", savedScholarshipApplication.getId(), JsonUtility.toJson(savedResults));
            return new ServiceResponse("01","Your scholarship application has been received and status updated");
        }
        catch (Exception e){
            log.error("An exception occurred in processScholarshipApplication: {}", e);
            return new ServiceResponse("100","Internal Error");
        }
    }

    public ScholarshipApplicationResponse processScholarshipApplication(ScholarshipApplicationRequest scholarshipApplicationRequest){
        log.info("Incoming scholarship application request: {}", JsonUtility.toJson(scholarshipApplicationRequest));
        try{
            ScholarshipApplication scholarshipApplication = new ScholarshipApplication(scholarshipApplicationRequest);
            ScholarshipApplication savedScholarshipApplication = scholarshipApplicationRepository.save(scholarshipApplication);
            log.info("Saved scholarship application: {}", JsonUtility.toJson(savedScholarshipApplication));
            return new ScholarshipApplicationResponse("01","Your scholarship application has been received with incomplete status", savedScholarshipApplication.getId());
        }
        catch (Exception e){
            log.error("An exception occurred in processScholarshipApplication: {}", e);
            return new ScholarshipApplicationResponse("100","Internal Error", null);
        }
    }

    public ScholarshipApplicationResponse addResults(Result result, Long scholarshipApplicationId){
        log.info("Incoming scholarship application request: {}", JsonUtility.toJson(result));
        try{
            Results results = new Results(result, scholarshipApplicationId);
            Results savedResults = resultsRepository.save(results);
            log.info("Saved results : {}", JsonUtility.toJson(savedResults));
            return new ScholarshipApplicationResponse("01","Successfully added result", scholarshipApplicationId);
        }
        catch (Exception e){
            log.error("An exception occurred in processScholarshipApplication: {}", e);
            return new ScholarshipApplicationResponse("100","Internal Error", null);
        }
    }
}
