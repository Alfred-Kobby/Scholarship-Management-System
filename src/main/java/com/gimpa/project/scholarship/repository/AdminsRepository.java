package com.gimpa.project.scholarship.repository;

import com.gimpa.project.scholarship.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends CrudRepository<Admin, Long> {
    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);
    Admin findByUsername(String username);
}