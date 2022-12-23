package com.example.toothfairy.web.repository;

import com.example.toothfairy.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query(value = "SELECT * FROM patient WHERE patient_id = :id", nativeQuery = true)
    Optional<Patient> findByUserId(@Param("id") String id);
}
