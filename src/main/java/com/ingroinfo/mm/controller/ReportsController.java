package com.ingroinfo.mm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.mm.dao.AssetRepository;
import com.ingroinfo.mm.entity.Assets;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	AssetRepository assetRepository;

	@GetMapping("/assets")
	public void generateReport(HttpServletResponse response) throws Exception {

		List<Assets> assets = assetRepository.findAll();

		// Compile the .jrxml file to a .jasper file
		JasperReport jasperReport = JasperCompileManager
				.compileReport(getClass().getResourceAsStream("/reports/Report.jrxml"));

		// Generate the report data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(assets);
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
