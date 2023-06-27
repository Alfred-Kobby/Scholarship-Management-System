package com.gimpa.project.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipApplicationRequest {
    private String firstName;
    private String surname;
    private String gender;
    private String dateOfBirth;
    private String phone;
    private String email;
    private String indexNumber;
    private String dateOfCompletion;
    private String nameOfInstitution;
    private String level;
    private String programme;
    private BigDecimal feesPerYear;
}
