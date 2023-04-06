package com.ingroinfo.mm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingroinfo.mm.dao.AssetsRepository;
import com.ingroinfo.mm.entity.Assets;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@RestController
public class Controller {
	
	@Autowired
	private AssetsRepository assetsRepository;
	
	@GetMapping("/assets")
	private void generateReport(HttpServletResponse response)
			throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = formatter.parse("2023-04-01");
		Date toDate = formatter.parse("2023-04-03");

			List<Assets> assets = assetsRepository.findAll();
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
