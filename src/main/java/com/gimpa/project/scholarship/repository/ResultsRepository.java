package com.gimpa.project.scholarship.repository;

import com.gimpa.project.scholarship.entity.Results;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultsRepository extends CrudRepository<Results, Long> {

    @Query(value="SELECT * FROM results WHERE scholarship_application_id = ? ORDER BY grade", nativeQuery=true)
    List<Results> findAllResultsPerScholarshipId(Long scholarshipId);

    List<Results> findByScholarshipApplicationId(Long scholarshipId);
}
