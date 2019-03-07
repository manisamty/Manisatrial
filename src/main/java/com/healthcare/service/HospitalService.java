package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.healthcare.DTO.HospitalDTO;
import com.healthcare.domain.Hospital;
import com.healthcare.domain.Patient;
import com.healthcare.repository.HospitalRepository;
import com.healthcare.repository.PatientRepository;

import net.minidev.json.JSONObject;

@Service
public class HospitalService {

	@Inject
	HospitalRepository hospitalRepository;

	@Inject
	PatientRepository patientRepository;

	/*
	 * @Autowired Patient patient;
	 */

	Hospital hospt = new Hospital();
	JSONObject jsonObj = new JSONObject();

	public String add(HospitalDTO hospital) {
		// 1. check whether pid is valid
		try {
			Patient patientDetails = patientRepository.getOne(hospital.getPid());
			if (patientDetails != null) {

				hospt.setHospialDescription(hospital.getHospialDescription());
				hospt.setHospitalId(hospital.getHospitalId());
				hospt.setHospitalName(hospital.getHospitalName());
				hospt.setPatient(patientDetails);
				hospitalRepository.save(hospt);
				return "Added Successufully";
			} else {
				return "Error in adding Hospital Details";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Pid not Found";
		}
	}

	public JSONObject get(long id) {
		List hospitalList = new ArrayList();
		JSONObject jsonHospObj = new JSONObject();
		try {
			
			List<Hospital> hospitalDetails = (List<Hospital>) hospitalRepository.findByPatientId(id);
			if(hospitalDetails!=null) {
				for(Hospital hptDetails :hospitalDetails) {
					JSONObject jsonObjhosp= new JSONObject();
					jsonObjhosp.put("hospitalId : ", hptDetails.getHospitalId());
					jsonObjhosp.put("hospitaldesc : ", hptDetails.getHospialDescription());
					jsonObjhosp.put("hospitalName : ", hptDetails.getHospitalName());
					hospitalList.add(jsonObjhosp);
					jsonHospObj.put("Details", hospitalList);
				}
				return jsonHospObj;
			}else {
				jsonObj.put("message : ", "no details found for the pid");
				return jsonObj;
			}
			
		}catch(Exception e) {
			jsonObj.put("error : ", "no details found for the pid");
			return jsonObj;
		}
	}

	public JSONObject updateHospitalDetails(HospitalDTO hospitalDTO) {
		//1. pid check
		try {
		Patient patientDetails = patientRepository.getOne(hospitalDTO.getPid());
		if(patientDetails!=null) {
			hospt.setHospialDescription(hospitalDTO.getHospialDescription());
			hospt.setHospitalId(hospitalDTO.getHospitalId());
			hospt.setHospitalName(hospitalDTO.getHospitalName());
			hospt.setPatient(patientDetails);
			hospitalRepository.save(hospt);
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

	public JSONObject deleteHospitalDetails(HospitalDTO hospitalDTO) {
		if(hospitalDTO!=null) {
			hospitalRepository.deleteById(hospitalDTO.getHospitalId());
			jsonObj.put("message", "Deleted Successfully");
			return jsonObj;
		}else {
			jsonObj.put("message", "failure in deletion");
			return jsonObj;
		}
			
	}

}
