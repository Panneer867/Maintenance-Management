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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dao.TaskUpdateRepository;
import com.ingroinfo.mm.dto.ComplaintDto;
import com.ingroinfo.mm.entity.Complaints;
import com.ingroinfo.mm.service.TaskUpdateService;

@Service
public class TaskUpdateServiceImpl implements TaskUpdateService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private TaskUpdateRepository taskUpdateRepo;
	@Autowired
	private RestTemplate restTemplate;

	@Value("${ubarmsApi}")
	String ubarmsUrl;

	@Override
	public List<ComplaintDto> getListOfJeComplaint(Long userId)
			throws JsonMappingException, JsonProcessingException, IOException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getjecomplainsmm/" + userId);
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
			System.out.println("Connection Failed " + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("complainListFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ComplaintDto> jeComplaintList = mapper2.readValue(jsonData, new TypeReference<List<ComplaintDto>>() {
		});

		return jeComplaintList;
	}

	@Override
	public ComplaintDto getComplaintDtlsByComplNo(String complNo) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ComplaintDto complaintDto = null;

		try {
			complaintDto = mapper.readValue(new URL(ubarmsUrl + "getComplainDtls/" + complNo), ComplaintDto.class);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return complaintDto;
	}

	@Override
	public List<ComplaintDto> getListOfAeeComplaint(Long userId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getaeecomplainsmm/" + userId);
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
			System.out.println("Connection Failed " + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("complainListFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ComplaintDto> aeeComplaintList = mapper2.readValue(jsonData, new TypeReference<List<ComplaintDto>>() {
		});

		return aeeComplaintList;
	}

	@Override
	public List<ComplaintDto> getListOfEeComplaint(Long userId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "geteecomplainsmm/" + userId);
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
			System.out.println("Connection Failed " + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("complainListFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ComplaintDto> EeComplaintList = mapper2.readValue(jsonData, new TypeReference<List<ComplaintDto>>() {
		});

		return EeComplaintList;
	}

	@Override
	public List<ComplaintDto> getListOfCommissionerComplaint(Long userId)
			throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getcommisscomplainsmm/" + userId);
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
			System.out.println("Connection Failed " + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("complainListFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ComplaintDto> comissComplaintList = mapper2.readValue(jsonData, new TypeReference<List<ComplaintDto>>() {
		});

		return comissComplaintList;
	}

	@Override
	public ComplaintDto saveComplaint(ComplaintDto complaintDto) {
		Complaints complaints = this.modelMapper.map(complaintDto, Complaints.class);
		Complaints complaints2 = this.taskUpdateRepo.save(complaints);
		return this.modelMapper.map(complaints2, ComplaintDto.class);
	}

	@Override
	public ComplaintDto submitInvestigations(ComplaintDto complaintDto) {

		ComplaintDto complaintDto2 = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ComplaintDto> httpEntity = new HttpEntity<>(complaintDto, headers);
		ResponseEntity<ComplaintDto> newCResponseEntity = restTemplate.postForEntity(ubarmsUrl + "submitInvestigations",
				httpEntity, ComplaintDto.class);

		if (newCResponseEntity.getStatusCode() == HttpStatus.CREATED) {
			complaintDto2 = newCResponseEntity.getBody();
		}

		return complaintDto2;
	}

	@Override
	public ComplaintDto getComplainDataByComplainNo(String complNo) {
		Complaints complaints = this.taskUpdateRepo.findByComplNo(complNo);
		ComplaintDto complaintDto = this.modelMapper.map(complaints, ComplaintDto.class);
		return complaintDto;
	}

	@Override
	public ComplaintDto submitEsclations(ComplaintDto complaintDto) {

		ComplaintDto complaintDto2 = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ComplaintDto> httpEntity = new HttpEntity<>(complaintDto, headers);
		ResponseEntity<ComplaintDto> newCResponseEntity = restTemplate.postForEntity(ubarmsUrl + "submitEsclations",
				httpEntity, ComplaintDto.class);
		if (newCResponseEntity.getStatusCode() == HttpStatus.CREATED) {
			complaintDto2 = newCResponseEntity.getBody();
		}

		return complaintDto2;
	}

	@Override
	public List<ComplaintDto> getComplainByDeptComplStsUserId(String department, String complSts, String userId) {
		List<Complaints> complaints = this.taskUpdateRepo.getComplainByDeptComplStsUserId(department, complSts, userId);
		List<ComplaintDto> complaintDtos = complaints.stream()
				.map((complain) -> this.modelMapper.map(complain, ComplaintDto.class)).collect(Collectors.toList());
		return complaintDtos;
	}

	@Override
	public List<ComplaintDto> getComplainByDeptComplSts(String department, String complSts) {
		List<Complaints> complaints = this.taskUpdateRepo.getComplainByDeptComplSts(department, complSts);
		List<ComplaintDto> complaintDtos = complaints.stream()
				.map((complaint) -> this.modelMapper.map(complaint, ComplaintDto.class)).collect(Collectors.toList());
		return complaintDtos;
	}

	@Override
	public ComplaintDto getComplaintDtlsByComplaintId(Long complId) {
		Complaints complaints = this.taskUpdateRepo.findById(complId).get();
		return this.modelMapper.map(complaints, ComplaintDto.class);
	}

	@Override
	public List<ComplaintDto> getListOfComplaintByStatus(String complStatus) {
		List<Complaints> complaints = this.taskUpdateRepo.findByComplStatus(complStatus);
		return complaints.stream().map((complaint) -> this.modelMapper.map(complaint, ComplaintDto.class))
				.collect(Collectors.toList());
	}

}
