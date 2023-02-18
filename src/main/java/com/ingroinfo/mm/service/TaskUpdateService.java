package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ingroinfo.mm.dto.ComplaintDto;

public interface TaskUpdateService {

    //get List Of Complaint Assign to JE From UBARMS	
	List<ComplaintDto> getListOfJeComplaint(Long userId) throws JsonMappingException, JsonProcessingException, IOException;
	
    //get List Of Complaint Assign to AEE From UBARMS
	List<ComplaintDto> getListOfAeeComplaint(Long userId) throws JsonMappingException, JsonProcessingException;
	
    //get List Of Complaint Assign to EE From UBARMS
	List<ComplaintDto> getListOfEeComplaint(Long userId) throws JsonMappingException, JsonProcessingException;
	
    //get List Of Complaint Assign to Commissioner From UBARMS
	List<ComplaintDto> getListOfCommissionerComplaint(Long userId) throws JsonMappingException, JsonProcessingException;
	
	//get complaint details by Complaint id from UBARMS
	ComplaintDto getComplaintDtlsByComplNo(String complNo);
	
	//Save JEE Investigation Report
	ComplaintDto saveComplaint(ComplaintDto complaintDto);
	
	//Send JEE Investigation Data To UBARMS
	ComplaintDto sendJeeInvestigation(ComplaintDto complaintDto);
}
