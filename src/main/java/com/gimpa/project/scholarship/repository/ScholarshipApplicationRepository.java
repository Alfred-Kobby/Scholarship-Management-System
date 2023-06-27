package com.gimpa.project.scholarship.repository;

import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
    List<ScholarshipApplication> findByEmail(String email);
    List<ScholarshipApplication> findByStatus(String status);
}
