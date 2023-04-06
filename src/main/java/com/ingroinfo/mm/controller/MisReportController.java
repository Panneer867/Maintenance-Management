package com.ingroinfo.mm.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.mm.dao.AssetRepository;
import com.ingroinfo.mm.dto.MisReportDto;
import com.ingroinfo.mm.entity.Assets;
import com.ingroinfo.mm.service.AssetService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/mis")
public class MisReportController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private AssetRepository assetRepository;
	
	@GetMapping("/daily")
	@PreAuthorize("hasAuthority('MIS_DAILY_REPORT')")
	public String misDaily(Model model) {

		model.addAttribute("daily", new MisReportDto());
		return "/pages/mis_report/daily";
	}

	@PostMapping("/daily")
	private void generateReport(@ModelAttribute("daily") MisReportDto misReportDto, HttpServletResponse response)
			throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = formatter.parse(misReportDto.getFromDate());
		Date toDate = formatter.parse(misReportDto.getToDate());

		if (misReportDto.getSubCategory().equals("ASSETS")) {

			List<Assets> assets = assetRepository.findAll();
						
			List<Assets> newAssets = assets.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			// Compile the .jrxml file to a .jasper file
			JasperReport jasperReport = JasperCompileManager
					.compileReport(getClass().getResourceAsStream("/reports/Assets_Report.jrxml"));

			// Generate the report data
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(newAssets);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("Assets Report", "Report");

			// Fill the report with data
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// Export the report to a PDF file and write it to the response
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=Assets_Report.pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

		}

	}

	@GetMapping("/monthly")
	@PreAuthorize("hasAuthority('MIS_MONTHLY_REPORT')")
	public String misMonthly() {
		return "/pages/mis_report/monthly";
	}

}
