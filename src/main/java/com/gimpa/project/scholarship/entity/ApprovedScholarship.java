package com.gimpa.project.scholarship.entity;

import com.gimpa.project.scholarship.model.FinalGradePoints;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "approved_scholarships")
@Entity
@NoArgsConstructor
public class ApprovedScholarship {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Long scholarshipApplicationId;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String surname;
    @Column(length = 50)
    private String nameOfInstitution;
    @Column
    private BigDecimal feesPerYear;
    @Column(length = 2)
    private Integer numOfAs;
    @Column(length = 20)
    private String scholarshipType;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;


    public ApprovedScholarship(FinalGradePoints finalGradePoints, String scholarshipType){
        this.scholarshipApplicationId = finalGradePoints.getScholarshipApplicationId();
        this.firstName = finalGradePoints.getFirstName();
        this.surname = finalGradePoints.getSurname();
        this.scholarshipType = scholarshipType;
        this.nameOfInstitution = finalGradePoints.getNameOfInstitution();
        this.numOfAs = finalGradePoints.getNumOfAs();
        this.feesPerYear = finalGradePoints.getFeesPerYear();
        this.created = new Date();
        this.updated = new Date();
    }
}
