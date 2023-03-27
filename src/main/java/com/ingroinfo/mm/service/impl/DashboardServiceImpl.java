package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.dto.GraphDto;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.DashboardService;
import com.ingroinfo.mm.service.TaskUpdateService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private TaskUpdateService taskUpdateService;

	@Autowired
	private AdminService adminService;

	@Override
	public GraphDto taskManagementGraph(String username) {

		User currentUser = adminService.getUserByUsername(username);
		Long userId = currentUser.getUbarmsUserId();

		GraphDto graph = new GraphDto();

		int jeCount = 0;
		int aeeCount = 0;
		int eeCount = 0;
		int CommCount = 0;

		Map<String, Integer> deptCount = new HashMap<String, Integer>();

		List<ComplaintDto> jeComplaints = null;
		try {
			jeComplaints = taskUpdateService.getListOfJeComplaint(userId);
			jeCount = jeComplaints.size();

			Map<String, List<ComplaintDto>> complaintsByDepartment = jeComplaints.stream()
					.collect(Collectors.groupingBy(ComplaintDto::getDepartment));

			for (Map.Entry<String, List<ComplaintDto>> entry : complaintsByDepartment.entrySet()) {
				String department = entry.getKey();
				List<ComplaintDto> complNo = entry.getValue();

				int count = complNo.size();
				deptCount.compute(department, (key, value) -> (value == null) ? count : value + count);
			}
			graph.setDepartmentCount(deptCount);
		} catch (NullPointerException e) {
			jeCount = 0;

		} catch (JsonMappingException e) {
			jeCount = 0;
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			jeCount = 0;
			e.printStackTrace();
		} catch (IOException e) {
			jeCount = 0;
			e.printStackTrace();
		}
		List<ComplaintDto> aeeComplaints = null;
		try {
			aeeComplaints = taskUpdateService.getListOfAeeComplaint(userId);
			aeeCount = aeeComplaints.size();

			Map<String, List<ComplaintDto>> complaintsByDepartment = aeeComplaints.stream()
					.collect(Collectors.groupingBy(ComplaintDto::getDepartment));

			for (Map.Entry<String, List<ComplaintDto>> entry : complaintsByDepartment.entrySet()) {
				String department = entry.getKey();
				List<ComplaintDto> complNo = entry.getValue();

				int count = complNo.size();
				deptCount.compute(department, (key, value) -> (value == null) ? count : value + count);
			}
			graph.setDepartmentCount(deptCount);
		} catch (NullPointerException e) {
			aeeCount = 0;

		} catch (JsonMappingException e) {
			aeeCount = 0;
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			aeeCount = 0;
			e.printStackTrace();
		}
		List<ComplaintDto> eeComplaints = null;
		try {
			eeComplaints = taskUpdateService.getListOfEeComplaint(userId);
			eeCount = eeComplaints.size();

			Map<String, List<ComplaintDto>> complaintsByDepartment = eeComplaints.stream()
					.collect(Collectors.groupingBy(ComplaintDto::getDepartment));

			for (Map.Entry<String, List<ComplaintDto>> entry : complaintsByDepartment.entrySet()) {
				String department = entry.getKey();
				List<ComplaintDto> complNo = entry.getValue();

				int count = complNo.size();
				deptCount.compute(department, (key, value) -> (value == null) ? count : value + count);
			}
			graph.setDepartmentCount(deptCount);
		} catch (NullPointerException e) {
			eeCount = 0;

		} catch (JsonMappingException e) {
			eeCount = 0;
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			eeCount = 0;
			e.printStackTrace();
		}
		List<ComplaintDto> commComplaints = null;
		try {
			commComplaints = taskUpdateService.getListOfCommissionerComplaint(userId);
			CommCount = commComplaints.size();

			Map<String, List<ComplaintDto>> complaintsByDepartment = commComplaints.stream()
					.collect(Collectors.groupingBy(ComplaintDto::getDepartment));

			for (Map.Entry<String, List<ComplaintDto>> entry : complaintsByDepartment.entrySet()) {
				String department = entry.getKey();
				List<ComplaintDto> complNo = entry.getValue();

				int count = complNo.size();
				deptCount.compute(department, (key, value) -> (value == null) ? count : value + count);
			}
			graph.setDepartmentCount(deptCount);
		} catch (NullPointerException e) {
			CommCount = 0;

		} catch (JsonMappingException e) {
			CommCount = 0;
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			CommCount = 0;
			e.printStackTrace();
		}

		graph.setJeComplaints(jeCount);
		graph.setAeeComplaints(aeeCount);
		graph.setEeComplaints(eeCount);
		graph.setCommComplaints(CommCount);
		return graph;
	}

}
