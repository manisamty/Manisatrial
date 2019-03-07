package com.healthcare.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.domain.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	@Query(" from Hospital hsp where hsp.patient.pid = ?1")
	List<Hospital> findByPatientId(long id);
	
}
