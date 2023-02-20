package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.BillingService;

@Controller
@RequestMapping("/billing")
public class BillingController {

	@Autowired
	private BillingService billingService;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	// Handler For Opening Consumer Details Page
	@GetMapping("/consumer-master")
	@PreAuthorize("hasAuthority('CONSUMER_MASTER')")
	public String openConsumerMasterPage(Model model) {
		model.addAttribute("getConsumerData", new ConsumersDto());
		model.addAttribute("title", "Billing | Consumer | Maintenance Management");
		return "/pages/billing/consumer_master";
	}

	// Handler For Opening Device Details Page
	@GetMapping("/meter-details")
	@PreAuthorize("hasAuthority('METER_DETAILS')")
	public String openMeterDetailsPage(Model model) {
		model.addAttribute("meterDtls", new MeterDtlsDto());
		model.addAttribute("title", "Billing | Meter | Maintenance Management");
		return "/pages/billing/meter_details";
	}

	// Handler For Getting Consumer Data From UBARMS
	@GetMapping("/get/consumers")
	public String getConsumers(String consumerId, Model model, HttpSession session) {

		ConsumersDto consumersDto = billingService.getConsumerData(consumerId);
		if (consumersDto != null) {
			model.addAttribute("getConsumerData", consumersDto);
		} else {
			session.setAttribute("message",
					new Message("Consumer Id Not Found !! Please Check Consumer Id !!", "danger"));
			System.out.println("ConsumerId Not found !!");
			return "redirect:/billing/consumer-master";
		}
		model.addAttribute("title", "Billing | Consumer | Maintenance Management");
		return "/pages/billing/consumer_master";
	}

	// Handler For getting Device Details From UBARMS
	@GetMapping("/get/meter")
	public String getDeviceDetails(@PathParam(value = "consumerId") String consumerId, Model model,
			HttpSession session) {

		MeterDtlsDto meterDtlsDto = billingService.getMeterDetailsData(consumerId);
		if (meterDtlsDto != null) {
			model.addAttribute("meterDtls", meterDtlsDto);
		} else {
			session.setAttribute("message",
					new Message("Consumer Id Not Found !! Please Check Consumer Id !!", "danger"));
			System.out.println("Consumer Id not Found !!");
			return "redirect:/billing/meter-details";
		}
		model.addAttribute("title", "Billing | Meter | Maintenance Management");
		return "/pages/billing/meter_details";
	}

	// Handler For Opening Consumer Transactions Details Page
	@GetMapping("/transactions")
	@PreAuthorize("hasAuthority('CONSUMER_TRANSACTION')")
	public String openTransactionPage(Model model) {
		model.addAttribute("title", "Billing | Transaction | Maintenance Management");
		model.addAttribute("consumerData", new ConsumersDto());
		model.addAttribute("consumerTrans", new ConsumerTransactionsDto());
		model.addAttribute("meterDtls", new MeterDtlsDto());
		model.addAttribute("cashHistory", new CashHistoryDto());
		model.addAttribute("rebateHistory", new RebateHistoryDto());
		model.addAttribute("intrestWaveoffHistory", new IntrestWaveOffDto());
		model.addAttribute("wrongPostingHistory", new WrongPostingDto());
		model.addAttribute("chequeBounceHistory", new ChequeBounceDto());
		model.addAttribute("reconnectionHistory", new ReconnectionsDto());
		model.addAttribute("disconnectionHistory", new DisconnectionsDto());
		model.addAttribute("show", null);
		return "/pages/billing/consumer_transactions";
	}

	// Handler For getting transactions Details From UBARMS
	@GetMapping("/get/transactions")
	public String getTransactionsDetails(@PathParam(value = "consumerId") String consumerId, Model model,
			HttpSession session) throws IOException {

		ConsumersDto consumers = billingService.getConsumerData(consumerId);
		if (consumers != null) {
			model.addAttribute("consumerData", consumers);
		} else {
			try {
				session.setAttribute("message",
						new Message("Consumer Id Not Found !! Please Check Consumer Id !!", "danger"));
				return "redirect:/billing/transactions";
			} catch (Exception e) {
				System.out.println("Exception : " + e.getMessage());
			}

		}
		try {
			List<ConsumerTransactionsDto> consumerTrans = billingService.getConsumerTransactions(consumerId);
			if (consumerTrans != null) {
				model.addAttribute("consumerTrans", consumerTrans);
			} else {
				System.out.println("Consumer Transactions Not Found !!");
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}

		try {
			MeterDtlsDto meterDtlsDto = billingService.getMeterDetailsData(consumerId);
			if (meterDtlsDto != null) {
				model.addAttribute("meterDtls", meterDtlsDto);			
			} else {
				System.out.println("Meter Not Found !!");
			}
		} catch (Exception e) {			
			System.out.println("Exception : " + e.getMessage());
		}

		try {
			List<CashHistoryDto> cashHistoryDtos = billingService.getCashHistory(consumerId);
			if (cashHistoryDtos != null) {
				model.addAttribute("cashHistory", cashHistoryDtos);
				model.addAttribute("view1", "view1");
			} else {
				System.out.println("cash History Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view1", null);
			System.out.println("Exception : " + e.getMessage());
		}

		try {
			List<RebateHistoryDto> rebateHistoryDtos = this.billingService.getRebateHistory(consumerId);
			if (rebateHistoryDtos != null) {
				model.addAttribute("rebateHistory", rebateHistoryDtos);
				model.addAttribute("view2", "view2");
			} else {
				System.out.println("Rebate History Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view2", null);
			System.out.println("Exception :" + e.getMessage());
		}
		try {
			List<IntrestWaveOffDto> intrestWaveOffDtos = this.billingService.getInterestWaveOff(consumerId);
			if (intrestWaveOffDtos != null) {
				model.addAttribute("intrestWaveoffHistory", intrestWaveOffDtos);
				model.addAttribute("view3", "view3");
			} else {
				System.out.println("Interest Wave-off Not found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view3", null);
			System.out.println("Exception : " + e.getMessage());
		}
		try {
			List<WrongPostingDto> wrongPostingDtos = this.billingService.getWrongPostingDtls(consumerId);
			if (wrongPostingDtos != null) {
				model.addAttribute("wrongPostingHistory", wrongPostingDtos);
				model.addAttribute("view4", "view4");
			} else {
				System.out.println("Wrong Posting Is Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view4", null);
			System.out.println("Exception : " + e.getMessage());
		}
		try {
			List<ChequeBounceDto> chequeBounceDtos = this.billingService.getChequeBounceDtls(consumerId);
			if (chequeBounceDtos != null) {
				model.addAttribute("chequeBounceHistory", chequeBounceDtos);
				model.addAttribute("view5", "view5");
			} else {
				System.out.println("Cheque Bounce Data Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view5", null);
			System.out.println("Exception : " + e.getMessage());
		}
		try {
			List<ReconnectionsDto> reconnectionsDtos = this.billingService.getReconnectionDtls(consumerId);
			if (reconnectionsDtos != null) {
				model.addAttribute("reconnectionHistory", reconnectionsDtos);
				model.addAttribute("view6", "view6");
			} else {
				System.out.println("Reconnection Data Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view6", null);
			System.out.println("Exception : " + e.getMessage());
		}
		try {
			List<DisconnectionsDto> disconnectionsDtos = this.billingService.getDisConnectionDtls(consumerId);
			if (disconnectionsDtos != null) {
				model.addAttribute("disconnectionHistory", disconnectionsDtos);
				model.addAttribute("view7", "view7");
			} else {
				System.out.println("DisConnection Data Not Found !!");
			}
		} catch (Exception e) {
			model.addAttribute("view7", null);
			System.out.println("Exception : " + e.getMessage());
		}

		model.addAttribute("show", "show");
		model.addAttribute("title", "Billing | Transaction | Maintenance Management");
		return "/pages/billing/consumer_transactions";
	}

}
