package com.healthcare.service;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.healthcare.DTO.ExaminationDTO;
import com.healthcare.DTO.HospitalDTO;
import com.healthcare.domain.Examination;
import com.healthcare.domain.Hospital;
import com.healthcare.domain.Patient;
import com.healthcare.repository.ExaminationRepository;
import com.healthcare.repository.PatientRepository;

import net.minidev.json.JSONObject;
@Service
public class ExaminationService {
	@Inject
	ExaminationRepository examinationRepository;

	@Inject
	PatientRepository patientRepository;
	Examination exam = new Examination();
	JSONObject jsonObj = new JSONObject();

	public String add(ExaminationDTO examination) {
		// 1. check whether pid is valid
		try {
			Patient patientDetails = patientRepository.getOne(examination.getPid());
			if (patientDetails != null) {

				exam.setExaminationName(examination.getExaminationName());
				exam.setExaminationId(examination.getExaminationId());
				exam.setExaminationDate(examination.getExaminationDate());
				exam.setDescription(examination.getDescription());
				exam.setHeight(examination.getHeight());
				exam.setWeight(examination.getWeight());
				exam.setPatient(patientDetails);
				examinationRepository.save(exam);
				return "Added Successufully";
			} else {
				return "Error in adding Examination Details";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Pid not Found";
		}
	}

	public JSONObject get(long id) {
		List examList = new ArrayList();
		JSONObject jsonExamObj = new JSONObject();
		try {
			
			List<Examination> ExaminationDetails = (List<Examination>) examinationRepository.findByPatientId(id);
			if(ExaminationDetails!=null) {
				for(Examination exmDetails :ExaminationDetails) {
					JSONObject jsonObjExm = new JSONObject();
					jsonObjExm.put("examinationId : ", exmDetails.getExaminationId());
					jsonObjExm.put("examinationName : ", exmDetails.getExaminationName());
					jsonObjExm.put("examinationDate : ", exmDetails.getExaminationDate());
					jsonObjExm.put("examinationDescription : ", exmDetails.getDescription());
					jsonObjExm.put("examinationHeight : ", exmDetails.getHeight());
					jsonObjExm.put("examinationWeight : ", exmDetails.getWeight());
					examList.add(jsonObjExm);
					jsonExamObj.put("Details", examList);
					
				}
				
				return jsonExamObj;
			}else {
				jsonObj.put("message : ", "no details found for the pid");
				return jsonObj;
			}
			
		}catch(Exception e) {
			jsonObj.put("error : ", "no details found for the pid");
			return jsonObj;
		}
	}

	public JSONObject updateExaminationDetails(ExaminationDTO examinationDTO) {
		//1. pid check
		try {
		Patient patientDetails = patientRepository.getOne(examinationDTO.getPid());
		if(patientDetails!=null) {
			exam.setDescription(examinationDTO.getDescription());
			exam.setExaminationDate(examinationDTO.getExaminationDate());
			exam.setExaminationId(examinationDTO.getExaminationId());
			exam.setExaminationName(examinationDTO.getExaminationName());
			exam.setPatient(patientDetails);
			examinationRepository.save(exam);
			jsonObj.put("message", "Updated Successfully");
			return jsonObj;
		}else {
			jsonObj.put("message", "Patient Id not found");
			return jsonObj;
		}
		}catch(Exception e) {
			jsonObj.put("message", "Error in updating");
			return jsonObj;
		}
		
	}

	public JSONObject deleteExaminationDetails(ExaminationDTO examinationDTO) {
		
		if(examinationDTO!=null) {
			examinationRepository.deleteById(examinationDTO.getExaminationId());
			jsonObj.put("message", "Deleted Successfully");
			return jsonObj;
		}else {
			jsonObj.put("message", "failure in deletion");
			return jsonObj;
		}
	}


}
