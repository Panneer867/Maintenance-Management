package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dto.VerifyIndentStatusDto;

@Controller
@RequestMapping("/workorder")
public class WorkOrderController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("dashboard")
	public String dashboard() {
		return "/pages/work_orders/dashboard";
	}

	private List<VerifyIndentStatusDto> executeQuery(String sql) {
		List<VerifyIndentStatusDto> verifyIndentDto = null;
		try {
			verifyIndentDto = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(VerifyIndentStatusDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verifyIndentDto;
	}

	@GetMapping("/generate")
	@PreAuthorize("hasAuthority('GENERATE_WORKORDER')")
	public String genrateWorkOrder(Model model) {

		List<VerifyIndentStatusDto> verifyIndentStatus = executeQuery("SELECT * FROM VERIFY_INDENT_STATUS");

		model.addAttribute("VerifiedIndentStsList", verifyIndentStatus);
		model.addAttribute("title", "Work Order | Generate Work Order");
		return "/pages/work_orders/generate_work_order";
	}

	@GetMapping("/approved")
	public String approvedWorkOrderList(Model model) {
		return "/pages/work_orders/approved_work_order_list";
	}

	@GetMapping("/hold")
	@PreAuthorize("hasAuthority('HOLD_WORKORDER')")
	public String holdWorkOrder(Model model) {
		return "/pages/work_orders/hold_work_order";
	}

	@GetMapping("/cancel")
	@PreAuthorize("hasAuthority('CANCEL_WORKORDER')")
	public String cancelWorkOrder(Model model) {
		return "/pages/work_orders/cancel_work_order";
	}

}
