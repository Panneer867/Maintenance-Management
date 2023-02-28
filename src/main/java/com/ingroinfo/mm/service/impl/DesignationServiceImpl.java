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
import com.ingroinfo.mm.dao.DesignationRepository;
import com.ingroinfo.mm.dto.DesignationDto;
import com.ingroinfo.mm.entity.Designation;
import com.ingroinfo.mm.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${ubarmsApi}")
	String ubarmsUrl;
	
	@Override
	public DesignationDto saveDesignation(DesignationDto designationDto) {
		Designation convDesignation = this.modelMapper.map(designationDto, Designation.class);
		Designation savedDesignation = this.designationRepo.save(convDesignation);
		return this.modelMapper.map(savedDesignation, DesignationDto.class);
	}

	@Override
	public List<DesignationDto> getAllDesignations() {
		List<Designation> designations = this.designationRepo.findAll();
		List<DesignationDto> designationDtos = designations.stream().map((designation) -> 
		this.modelMapper.map(designation, DesignationDto.class)).collect(Collectors.toList());
		return designationDtos;
	}

	@Override
	public void deleteDesignations(Long desigId) {
		 Designation designation = this.designationRepo.findById(desigId).get();
		 this.designationRepo.delete(designation);
	}

	@Override
	public List<DesignationDto> getDesignationsFormUbarms() throws JsonMappingException, JsonProcessingException,IOException {	
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getdesignationformm");
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
		JsonNode data = jsonNode.get("designationFormMM");
		String jsonData = data.toString();
		
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<DesignationDto> desigList = mapper2.readValue(jsonData, new TypeReference<List<DesignationDto>>() {
		});

		return desigList;		
	}

}
