package com.gimpa.project.scholarship.service;

import com.gimpa.project.scholarship.entity.ApprovedScholarship;
import com.gimpa.project.scholarship.entity.Results;
import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import com.gimpa.project.scholarship.model.FinalGradePoints;
import com.gimpa.project.scholarship.model.ScholarshipStatus;
import com.gimpa.project.scholarship.model.ScholarshipType;
import com.gimpa.project.scholarship.repository.ApprovedScholarshipRepository;
import com.gimpa.project.scholarship.repository.ResultsRepository;
import com.gimpa.project.scholarship.repository.ScholarshipApplicationRepository;
import com.gimpa.project.scholarship.utils.JsonUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScholarshipSelectionService {
    @Autowired
    ScholarshipApplicationRepository scholarshipApplicationRepository;
    @Autowired
    ApprovedScholarshipRepository approvedScholarshipRepository;
    @Autowired
    ResultsRepository resultsRepository;

    public List<ScholarshipApplication> getAllScholarshipApplication(){
        List<ScholarshipApplication> scholarships = new ArrayList<>();
        try{
            scholarships = scholarshipApplicationRepository.findAll();
            log.info("Retrieved Scholarships: {}", scholarships);
        }
        catch (Exception e){
            log.error("An exception occurred in getAllScholarshipApplication: {}", e);
        }
        return scholarships;
    }

    public List<Results> getResultsForApplicant(String scholarshipId){
        List<Results> results = new ArrayList<>();
        try{
            Long id = Long.parseLong(scholarshipId);
            results = resultsRepository.findByScholarshipApplicationId(id);
        }
        catch (Exception e){
            log.error("An exception occurred in getResultsForApplicant: {}", e);
        }
        return results;
    }

    public Iterable<ApprovedScholarship> processFinalScholarshipApplication(){
//        Ranking scholarships
        List<FinalGradePoints> finalGradePoints = rankScholarshipApplications();
        log.info("Final Grade point: {}", JsonUtility.toJson(finalGradePoints));
//        Getting refused scholarships
        List<FinalGradePoints> refusedScholarships = getRefusedScholarships(finalGradePoints);
//        Updating status of refused scholarships to refused
        updateStatusOfRefusedScholarships(refusedScholarships);

//        Getting approved/accepted scholarships
        List<FinalGradePoints> acceptedScholarships = getAcceptedScholarships(finalGradePoints);
//        Updating status of approved/accepted scholarships
        updateStatusOfAcceptedScholarships(acceptedScholarships);
        groupApprovedScholarshipType(acceptedScholarships);
        Iterable<ApprovedScholarship> allApprovedScholarships = approvedScholarshipRepository.findAll();
        return allApprovedScholarships;
    }

    public List<FinalGradePoints> getAcceptedScholarships(List<FinalGradePoints> finalGradePointsList){
        List<FinalGradePoints> acceptedScholarships = new ArrayList<>();
        try{
            acceptedScholarships = finalGradePointsList.stream().filter(x -> x.getTotalFinalGradePoints() <= 10 && x.getNumOfResults() >= 6).collect(Collectors.toList());
        }
        catch (Exception e){
            log.error("An exception occurred in getAcceptedScholarships: {}", e);
        }
        return acceptedScholarships;
    }

    public void updateStatusOfAcceptedScholarships(List<FinalGradePoints> acceptedScholarships){
        List<ScholarshipApplication> acceptedScholarshipApplicationsToUpdateStatus = new ArrayList<>();
        try{
            for (FinalGradePoints finalGradePoints: acceptedScholarships){
                ScholarshipApplication scholarshipApplication = scholarshipApplicationRepository.findById(finalGradePoints.getScholarshipApplicationId()).get();
                scholarshipApplication.setStatus(ScholarshipStatus.approved.toString());
                acceptedScholarshipApplicationsToUpdateStatus.add(scholarshipApplication);
            }
            Iterable<ScholarshipApplication> updatedRefusedScholarships = scholarshipApplicationRepository.saveAll(acceptedScholarshipApplicationsToUpdateStatus);
            log.info("Updated Approved Scholarship Application: {}", updatedRefusedScholarships);
        }
        catch (Exception e){
            log.error("An exception occurred in updateStatusOfAcceptedScholarships: {}", e);
        }
    }

    public List<ApprovedScholarship> groupApprovedScholarshipType(List<FinalGradePoints> acceptedScholarships){
        List<ApprovedScholarship> approvedScholarships = new ArrayList<>();
        try{
            acceptedScholarships.forEach(x->{
                ApprovedScholarship approvedScholarship;
                if(x.getNumOfAs() > 3){
                    approvedScholarship = new ApprovedScholarship(x, ScholarshipType.fully_funded.toString());
                }
                else {
                    approvedScholarship = new ApprovedScholarship(x, ScholarshipType.partially_funded.toString());
                }
                approvedScholarships.add(approvedScholarship);
            });
            Iterable<ApprovedScholarship> savedApprovedScholarships = approvedScholarshipRepository.saveAll(approvedScholarships);
            log.info("Saved Approved Scholarships: {}", JsonUtility.toJson(savedApprovedScholarships));
        }
        catch (Exception e){
            log.error("An exception occurred in groupApprovedScholarshipType: {}", e);
        }
        return approvedScholarships;
    }

    public List<FinalGradePoints> getRefusedScholarships(List<FinalGradePoints> finalGradePointsList){
        List<FinalGradePoints> refusedScholarships = new ArrayList<>();
        try{
            refusedScholarships = finalGradePointsList.stream().filter(x -> x.getTotalFinalGradePoints() > 10 || x.getNumOfResults() < 6).collect(Collectors.toList());
            log.info("Refused Scholarships: {}", JsonUtility.toJson(refusedScholarships));
        }
        catch (Exception e){
            log.error("An exception occurred in getRefusedScholarships: {}", e);
        }
        return refusedScholarships;
    }

    public void updateStatusOfRefusedScholarships(List<FinalGradePoints> refusedScholarships){
        List<ScholarshipApplication> refusedScholarshipApplicationsToUpdateStatus = new ArrayList<>();
        try{
            for (FinalGradePoints finalGradePoints: refusedScholarships){
                ScholarshipApplication scholarshipApplication = scholarshipApplicationRepository.findById(finalGradePoints.getScholarshipApplicationId()).get();
                scholarshipApplication.setStatus(ScholarshipStatus.refused.toString());
                refusedScholarshipApplicationsToUpdateStatus.add(scholarshipApplication);
            }
            Iterable<ScholarshipApplication> updatedRefusedScholarships = scholarshipApplicationRepository.saveAll(refusedScholarshipApplicationsToUpdateStatus);
            log.info("Updated Refused Scholarship Application: {}", updatedRefusedScholarships);
        }
        catch (Exception e){
            log.error("An exception occurred in updateStatusOfRefusedScholarships: {}", e);
        }
    }

    public List<FinalGradePoints> rankScholarshipApplications(){
        List<FinalGradePoints> totalFinalGradePoints = new ArrayList<>();
        try{
            Iterable<ScholarshipApplication> receivedScholarshipApplications = scholarshipApplicationRepository.findByStatus(ScholarshipStatus.pending.toString());
            receivedScholarshipApplications.forEach(x->{
                List<Results> results = resultsRepository.findAllResultsPerScholarshipId(x.getId());
                Long finalTotalGradePoint = calculateTotalGradePoint(results);
                Integer numOfAs = countA(results);
                Integer numOfResults = results.size();
                FinalGradePoints finalGradePoints = new FinalGradePoints(x.getId(),x.getFirstName(),x.getSurname(),x.getNameOfInstitution(),x.getFeesPerYear(),finalTotalGradePoint,numOfAs,numOfResults);
                totalFinalGradePoints.add(finalGradePoints);
            });
            Collections.sort(totalFinalGradePoints,finalGradePointsComparator);
        }
        catch (Exception e){
            log.error("An exception occurred in rankScholarshipApplications: {}", e);
        }
        return totalFinalGradePoints;
    }

    private Long calculateTotalGradePoint(List<Results> results){
        int i = 0;
        long totalGradePoint = 0L;
        for(Results result: results){
            if(i < 6){
                totalGradePoint += calculateGradeValue(result.getGrade());
                i++;
            }
        }
        return totalGradePoint;
    }

    private Integer countA(List<Results> results){
        return Math.toIntExact(results.stream().filter(x -> x.getGrade().equalsIgnoreCase("A1")).count());
    }

    // Method
    private static Comparator<FinalGradePoints> finalGradePointsComparator = (grade1, grade2) -> {
        long gradePoint1 = grade1.getTotalFinalGradePoints();
        long gradePoint2 = grade2.getTotalFinalGradePoints();
        // For ascending order
        return (int) (gradePoint1 - gradePoint2);
    };

    private Long calculateGradeValue(String grade){
        switch (grade){
            case "A1":
                return 1L;
            case "B2":
                return 2L;
            case "B3":
                return 3L;
            case "C4":
                return 4L;
            case "C5":
                return 5L;
            case "C6":
                return 6L;
            case "D7":
                return 7L;
            case "E8":
                return 8L;
            default:
                return 9L;
        }
    }
}
