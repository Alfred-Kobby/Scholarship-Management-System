package com.gimpa.project.scholarship.entity;

import com.gimpa.project.scholarship.model.RegistrationRequest;
import com.gimpa.project.scholarship.utils.PasswordConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "admins")
@Entity
@NoArgsConstructor
public class Admin {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 50)
    private String username;
    @Column(length = 100)
    private String email;
    @Column
    @Convert(converter = PasswordConverter.class)
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Admin(RegistrationRequest registrationRequest){
        this.firstName = registrationRequest.getFirstName();
        this.lastName = registrationRequest.getLastName();
        this.phoneNumber = registrationRequest.getPhoneNumber();
        this.username = registrationRequest.getUsername();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
        this.created = new Date();
        this.updated = new Date();
    }

}
