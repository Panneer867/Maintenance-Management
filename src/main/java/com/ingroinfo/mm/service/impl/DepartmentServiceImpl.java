package com.ingroinfo.mm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dao.DepartmentRepository;
import com.ingroinfo.mm.dto.DepartmentDto;
import com.ingroinfo.mm.entity.Department;
import com.ingroinfo.mm.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${ubarmsApi}")
	String ubarmsUrl;
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department convDepartment = this.modelMapper.map(departmentDto, Department.class);
		Department savedDepartment = this.departmentRepository.save(convDepartment);
		return this.modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> findAllDepartment() {
		List<Department> departments = this.departmentRepository.findAll();
		List<DepartmentDto> departmentDtos = departments.stream().map((department) -> this.modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
		return departmentDtos;
	}

	@Override
	public void deleteDepartmentMaster(Long depMasterId) {
		Department department = this.departmentRepository.findById(depMasterId).get();
		this.departmentRepository.delete(department);
	}

	@Override
	public String getMaxDepartmentId() {
	    String departmentId = this.departmentRepository.getMaxDepartmentId();
		return departmentId;
	}

	@Override
	public List<DepartmentDto> getDepartmentsFromUbarms() throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getdepartmentsformm");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				json += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection Failed !!"+e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("departmentFormMM");
		String jsonData = data.toString();
		
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<DepartmentDto> departmentDtos = mapper2.readValue(jsonData, new TypeReference<List<DepartmentDto>>() {
		});

		return departmentDtos;	
	}

	

}
