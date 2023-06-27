package com.gimpa.project.scholarship.model;

import com.gimpa.project.scholarship.entity.ScholarshipApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {
    private String responseCode;
    private String responseMessage;
    private ScholarshipApplication scholarshipApplication;
}
