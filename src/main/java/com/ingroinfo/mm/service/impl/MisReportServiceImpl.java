package com.ingroinfo.mm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.AssetsRepository;
import com.ingroinfo.mm.entity.Assets;
import com.ingroinfo.mm.service.MisReportService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MisReportServiceImpl implements MisReportService {

	@Autowired
	private AssetsRepository assetsRepository;

	@Override
	public JasperPrint generateAssetsReport(String from, String to) throws ParseException, JRException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = formatter.parse(from);
		Date toDate = formatter.parse(to);

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

		return jasperPrint;

	}

}
