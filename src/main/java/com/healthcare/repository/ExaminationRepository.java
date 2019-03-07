package com.healthcare.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.domain.Examination;



@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long>{

	@Query(" from Examination exp where exp.patient.pid = ?1")
	List<Examination> findByPatientId(long id);
	
}