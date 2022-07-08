package com.example.toothfairy.web.repository;

import com.example.toothfairy.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
}
