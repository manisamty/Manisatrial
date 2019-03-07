package com.healthcare.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.DTO.HospitalDTO;
import com.healthcare.DTO.PatientDTO;
import com.healthcare.domain.Hospital;
import com.healthcare.service.HospitalService;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/healthcare")
public class HospitalController {

	@Autowired
	HospitalService hospitalService;
	
	@RequestMapping(method = RequestMethod.POST,value = "/hospital/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addHospitalDetails(@RequestBody HospitalDTO hospital){
			
		String message = hospitalService.add(hospital);
		//return message;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message : ", message);
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/hospital/retrieve/{pid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> getHospitalDetails(@PathVariable long pid){
			
		JSONObject hospitalDetails = hospitalService.get(pid);
		//return message;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message : ", hospitalDetails);
		return new ResponseEntity<JSONObject>(jsonObj,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/hospital/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> updateHospitalDetails(@RequestBody HospitalDTO hospitalDTO){
			
		JSONObject hospitalDetails = hospitalService.updateHospitalDetails(hospitalDTO);
		//return message;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message : ", hospitalDetails);
		return new ResponseEntity<JSONObject>(jsonObj,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/hospital/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> deleteHospitalDetails(@RequestBody HospitalDTO hospitalDTO){
			
		JSONObject hospitalDetails = hospitalService.deleteHospitalDetails(hospitalDTO);
		//return message;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("message : ", hospitalDetails);
		return new ResponseEntity<JSONObject>(jsonObj,HttpStatus.CREATED);
		
	}
}
