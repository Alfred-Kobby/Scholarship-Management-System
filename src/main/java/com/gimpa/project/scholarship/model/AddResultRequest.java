package com.gimpa.project.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddResultRequest {
    private String subject;
    private String grade;
    private String examType;
    private String scholarshipId;
}