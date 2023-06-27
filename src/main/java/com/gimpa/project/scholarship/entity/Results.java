package com.gimpa.project.scholarship.entity;

import com.gimpa.project.scholarship.model.Result;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "results")
@Entity
@NoArgsConstructor
public class Results {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long scholarshipApplicationId;
    @Column(length = 50)
    private String subject;
    @Column(length = 4)
    private String grade;
    @Column(length = 20)
    private String examType;

    public Results(Result result, Long scholarshipApplicationId){
        this.scholarshipApplicationId = scholarshipApplicationId;
        this.subject = result.getSubject();
        this.grade = result.getGrade();
        this.examType = result.getExamType();
    }
}
