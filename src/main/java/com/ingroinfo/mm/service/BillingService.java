package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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

public interface BillingService {

	//get Consumer Data From UBARMS
	ConsumersDto getConsumerData(String consumerId);
	
	//get Device Details From UBARMS
	MeterDtlsDto getMeterDetailsData(String consumerId);

	//get Transactions Details From UBARMS
	List<ConsumerTransactionsDto> getConsumerTransactions(String consumerId) throws IOException;
	
	//get Cash History From UBARMS
	List<CashHistoryDto> getCashHistory(String consumerId) throws IOException;
	
	//get Rebate History From UBARMS
	List<RebateHistoryDto> getRebateHistory(String consumerId) throws IOException;
	
	//get Interest Wave Off From UBARMS
	List<IntrestWaveOffDto> getInterestWaveOff(String consumerId) throws JsonMappingException, JsonProcessingException;

	//get Wrong Posting Details From UBARMS
	List<WrongPostingDto> getWrongPostingDtls(String consumerId) throws JsonMappingException, JsonProcessingException;
	
	//get Cheque Bounce Details From UBARMS
	List<ChequeBounceDto> getChequeBounceDtls(String consumerId) throws JsonMappingException, JsonProcessingException;
	
	//get Reconnection Details From UBARMS
	List<ReconnectionsDto> getReconnectionDtls(String consumerId) throws JsonMappingException, JsonProcessingException;
	
	//get Dis-Connections Detalis From UBARMS
	List<DisconnectionsDto> getDisConnectionDtls(String consumerId) throws JsonMappingException, JsonProcessingException;
	
	
}
