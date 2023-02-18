package com.ingroinfo.mm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dto.CashHistoryDto;
import com.ingroinfo.mm.dto.ChequeBounceDto;
import com.ingroinfo.mm.dto.ConsumerTransactionsDto;
import com.ingroinfo.mm.dto.ConsumersDto;
import com.ingroinfo.mm.dto.DisconnectionsDto;
import com.ingroinfo.mm.dto.IntrestWaveOffDto;
import com.ingroinfo.mm.dto.MeterDtlsDto;
import com.ingroinfo.mm.dto.RebateHistoryDto;
import com.ingroinfo.mm.dto.ReconnectionsDto;
import com.ingroinfo.mm.dto.WrongPostingDto;
import com.ingroinfo.mm.service.BillingService;

@Service
public class BillingServiceImpl implements BillingService {

	@Override
	public ConsumersDto getConsumerData(String consumerId) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		ConsumersDto consumersDto = null;

		try {
			consumersDto = mapper.readValue(
					new URL("http://localhost:9595/ubarms/arms/getconsumerformm/" + consumerId), ConsumersDto.class);
			//System.out.println(consumersDto);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		return consumersDto;
	}

	@Override
	public MeterDtlsDto getMeterDetailsData(String consumerId) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		MeterDtlsDto meterDtlsDto = null;
		try {
			 meterDtlsDto = mapper.readValue(
					new URL("http://localhost:9595/ubarms/arms/getmeterDtlsmm/" + consumerId), MeterDtlsDto.class);
			//System.out.println(meterDtlsDto);
		} catch (Exception e) {
			System.out.println("Connection Failed !!"+e.getMessage());
		}	
		
		return meterDtlsDto;
	}

	@Override
	public List<ConsumerTransactionsDto> getConsumerTransactions(String consumerId) throws IOException{
		
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getTransactionFormm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("getTransactionsFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ConsumerTransactionsDto> consumerTrans = mapper2.readValue(jsonData, new TypeReference<List<ConsumerTransactionsDto>>() {
		});

		return consumerTrans;

	}

	@Override
	public List<CashHistoryDto> getCashHistory(String consumerId) throws IOException {
		
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getcashHistoryFormm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("cashHistoryFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<CashHistoryDto> cashHistory = mapper2.readValue(jsonData, new TypeReference<List<CashHistoryDto>>() {
		});
		return cashHistory;
	}

	@Override
	public List<RebateHistoryDto> getRebateHistory(String consumerId) throws IOException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getRebatehistorymm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("rebateHistoryFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<RebateHistoryDto> rebateHistory = mapper2.readValue(jsonData, new TypeReference<List<RebateHistoryDto>>() {});
		return rebateHistory;
	 }

	@Override
	public List<IntrestWaveOffDto> getInterestWaveOff(String consumerId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getintrestWavehistory/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("intrestWaveOffFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<IntrestWaveOffDto> intwrestWaveOff = mapper2.readValue(jsonData, new TypeReference<List<IntrestWaveOffDto>>() {});
		return intwrestWaveOff;
	}

	@Override
	public List<WrongPostingDto> getWrongPostingDtls(String consumerId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getwrongpostingmm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("wrongPostingFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<WrongPostingDto> wrongposting = mapper2.readValue(jsonData, new TypeReference<List<WrongPostingDto>>() {});
		return wrongposting;
	}

	@Override
	public List<ChequeBounceDto> getChequeBounceDtls(String consumerId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getchequebouncemm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("chequeBounceFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ChequeBounceDto> chequeBounce = mapper2.readValue(jsonData, new TypeReference<List<ChequeBounceDto>>() {});
		return chequeBounce;
	}

	@Override
	public List<ReconnectionsDto> getReconnectionDtls(String consumerId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getreconnectionmm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("reconnectionFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<ReconnectionsDto> reconnDetails = mapper2.readValue(jsonData, new TypeReference<List<ReconnectionsDto>>() {});
		return reconnDetails;
	}

	@Override
	public List<DisconnectionsDto> getDisConnectionDtls(String consumerId) throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL("http://localhost:9595/ubarms/arms/getdisconnectionmm/"+consumerId);
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
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("disconnectionFormMM");		         
		String jsonData = data.toString();
				
		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<DisconnectionsDto> disconnections = mapper2.readValue(jsonData, new TypeReference<List<DisconnectionsDto>>() {});
		return disconnections;
	}
	
}
	

