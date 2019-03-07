package com.healthcare.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.healthcare.domain.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

	

}
