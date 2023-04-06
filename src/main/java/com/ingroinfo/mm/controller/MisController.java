package com.ingroinfo.mm.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dao.AssetsRepository;
import com.ingroinfo.mm.dto.MisReportDto;
import com.ingroinfo.mm.entity.Assets;
import com.ingroinfo.mm.helper.Message;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Controller
@RequestMapping("/mis")
public class MisController {

	@Autowired
	private AssetsRepository assetsRepository;

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@GetMapping("/daily")
	@PreAuthorize("hasAuthority('MIS_DAILY_REPORT')")
	public String misDaily(Model model) {

		model.addAttribute("daily", new MisReportDto());
		return "/pages/mis_report/daily";
	}

	@GetMapping("/daily/report")
	public ResponseEntity<InputStreamResource> generateReport(@ModelAttribute("daily") MisReportDto misReportDto,
			HttpSession session) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = formatter.parse(misReportDto.getFromDate());
		Date toDate = formatter.parse(misReportDto.getToDate());

		if (misReportDto.getSubCategory().equals("ASSETS")) {

			List<Assets> assets = assetsRepository.findAll();
			List<Assets> newAssets = assets.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newAssets.size() == 0) {
				session.setAttribute("message", new Message("No data exists to generate report!", "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/mis/daily").build();
			}

			// Compile the .jrxml file to a .jasper file
			InputStream jasperStream = this.getClass().getResourceAsStream("/reports/Assets_Report.jrxml");
			JasperDesign design = JRXmlLoader.load(jasperStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);

			// Generate the report data
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(newAssets);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("Report Asset", "Assets Report");

			// Fill the report with data
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// Export the report to a PDF file and create an InputStreamResource from the
			// generated PDF
			ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint));
			InputStreamResource isr = new InputStreamResource(bis);

			// Date and Time
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter Dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_hh:mm");
			String formattedDateTime = now.format(Dateformatter);

			// Create and return a ResponseEntity with the InputStreamResource
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=Assets_Report_" + formattedDateTime + ".pdf");
			ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok().headers(headers)
					.contentType(MediaType.APPLICATION_PDF).body(isr);
			return responseEntity;
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/monthly")
	@PreAuthorize("hasAuthority('MIS_MONTHLY_REPORT')")
	public String misMonthly() {
		return "/pages/mis_report/monthly";
	}

}
