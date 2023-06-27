package com.gimpa.project.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalGradePoints {
    private Long scholarshipApplicationId;
    private String firstName;
    private String surname;
    private String nameOfInstitution;
    private BigDecimal feesPerYear;
    private Long totalFinalGradePoints;
    private Integer numOfAs;
    private Integer numOfResults;
}
