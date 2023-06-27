package com.gimpa.project.scholarship.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String subject;
    private String grade;
    private String examType;

    public Result(AddResultRequest addResultRequest){
        this.subject = addResultRequest.getSubject();
        this.grade = addResultRequest.getGrade();
        this.examType = addResultRequest.getExamType();
    }
}