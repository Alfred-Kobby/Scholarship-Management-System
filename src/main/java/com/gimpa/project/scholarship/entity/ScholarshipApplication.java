package com.gimpa.project.scholarship.entity;

import com.gimpa.project.scholarship.model.ScholarshipApplicationRequest;
import com.gimpa.project.scholarship.model.ScholarshipApplicationWithResultsRequest;
import com.gimpa.project.scholarship.model.ScholarshipStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "scholarship_applications")
@Entity
@NoArgsConstructor
public class ScholarshipApplication {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String surname;
    @Column(length = 10)
    private String gender;
    @Column(length = 12)
    private String dateOfBirth;
    @Column(length = 15)
    private String phone;
    @Column(length = 100)
    private String email;
    @Column(length = 15)
    private String indexNumber;
    @Column(length = 12)
    private String dateOfCompletion;
    @Column(length = 100)
    private String nameOfInstitution;
    @Column(length = 5)
    private String level;
    @Column(length = 50)
    private String programme;
    @Column(length = 10)
    private String status;
    @Column
    private BigDecimal feesPerYear;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public ScholarshipApplication(ScholarshipApplicationWithResultsRequest scholarshipApplicationWithResultsRequest){
        this.firstName = scholarshipApplicationWithResultsRequest.getFirstName();
        this.surname = scholarshipApplicationWithResultsRequest.getSurname();
        this.gender = scholarshipApplicationWithResultsRequest.getGender();
        this.dateOfBirth = scholarshipApplicationWithResultsRequest.getDateOfBirth();
        this.phone = scholarshipApplicationWithResultsRequest.getPhone();
        this.email = scholarshipApplicationWithResultsRequest.getEmail();
        this.indexNumber = scholarshipApplicationWithResultsRequest.getIndexNumber();
        this.dateOfCompletion = scholarshipApplicationWithResultsRequest.getDateOfCompletion();
        this.nameOfInstitution = scholarshipApplicationWithResultsRequest.getNameOfInstitution();
        this.level = scholarshipApplicationWithResultsRequest.getLevel();
        this.programme = scholarshipApplicationWithResultsRequest.getProgramme();
        this.feesPerYear = scholarshipApplicationWithResultsRequest.getFeesPerYear();
        this.status = ScholarshipStatus.pending.toString();
        this.created = new Date();
        this.updated = new Date();
    }

    public ScholarshipApplication(ScholarshipApplicationRequest scholarshipApplicationRequest){
        this.firstName = scholarshipApplicationRequest.getFirstName();
        this.surname = scholarshipApplicationRequest.getSurname();
        this.gender = scholarshipApplicationRequest.getGender();
        this.dateOfBirth = scholarshipApplicationRequest.getDateOfBirth();
        this.phone = scholarshipApplicationRequest.getPhone();
        this.email = scholarshipApplicationRequest.getEmail();
        this.indexNumber = scholarshipApplicationRequest.getIndexNumber();
        this.dateOfCompletion = scholarshipApplicationRequest.getDateOfCompletion();
        this.nameOfInstitution = scholarshipApplicationRequest.getNameOfInstitution();
        this.level = scholarshipApplicationRequest.getLevel();
        this.programme = scholarshipApplicationRequest.getProgramme();
        this.feesPerYear = scholarshipApplicationRequest.getFeesPerYear();
        this.status = ScholarshipStatus.pending.toString();
        this.created = new Date();
        this.updated = new Date();
    }
}
