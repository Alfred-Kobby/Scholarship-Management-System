package com.gimpa.project.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipApplicationResponse {
    private String responseCode;
    private String responseMessage;
    private Long scholarshipApplicationId;
}
