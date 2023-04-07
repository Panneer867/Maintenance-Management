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

import com.ingroinfo.mm.dao.ApprovedStocksReturnRepository;
import com.ingroinfo.mm.dao.AssetsRepository;
import com.ingroinfo.mm.dao.InwardApprovedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardApprovedSparesRepository;
import com.ingroinfo.mm.dao.InwardApprovedToolsRepository;
import com.ingroinfo.mm.dao.StockOrderItemsRepository;
import com.ingroinfo.mm.dao.WapWorkOrderItemsRepository;
import com.ingroinfo.mm.dao.WapWorkOrderLaboursRepository;
import com.ingroinfo.mm.dao.WapWorkOrderVehiclesRepository;
import com.ingroinfo.mm.dto.MisReportDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.Assets;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.StockOrderItems;
import com.ingroinfo.mm.entity.WapWorkOrderItems;
import com.ingroinfo.mm.entity.WapWorkOrderLabours;
import com.ingroinfo.mm.entity.WapWorkOrderVehicles;
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

	@Autowired
	private InwardApprovedMaterialsRepository inwardApprovedMaterialsRepository;

	@Autowired
	private InwardApprovedToolsRepository inwardApprovedToolsRepository;

	@Autowired
	private InwardApprovedSparesRepository inwardApprovedSparesRepository;

	@Autowired
	private StockOrderItemsRepository stockOrderItemsRepository;

	@Autowired
	private ApprovedStocksReturnRepository approvedStocksReturnRepository;
	
	@Autowired
	private WapWorkOrderItemsRepository wapWorkOrderItemsRepository;
	
	@Autowired
	private WapWorkOrderLaboursRepository wapWorkOrderLaboursRepository;
	
	@Autowired
	private WapWorkOrderVehiclesRepository wapWorkOrderVehiclesRepository;

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

	private ResponseEntity<InputStreamResource> generateReport(String reportName, String param, List<?> reportList)
			throws Exception {

		// Compile the .jrxml file to a .jasper file
		InputStream jasperStream = this.getClass().getResourceAsStream(reportName);
		JasperDesign design = JRXmlLoader.load(jasperStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(design);

		// Generate the report data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(param, param);

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
		headers.add("Content-Disposition",
				"attachment; filename=" + param.replaceAll("\\s+", "_") + "_" + formattedDateTime + ".pdf");
		ResponseEntity<InputStreamResource> responseEntity = ResponseEntity.ok().headers(headers)
				.contentType(MediaType.APPLICATION_PDF).body(isr);
		return responseEntity;
	}

	@GetMapping("/daily/report")
	public ResponseEntity<InputStreamResource> misReport(@ModelAttribute("daily") MisReportDto misReportDto,
			HttpSession session) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = formatter.parse(misReportDto.getFromDate());
		Date toDate = formatter.parse(misReportDto.getToDate());

		String message = "No data exists to generate report!";
		String redirect = "/mis/daily";

		if (misReportDto.getCategory().equals("ASSETS") && misReportDto.getSubCategory().equals("ASSETS")) {

			String reportName = "/reports/Assets_Report.jrxml";
			String param = "Assets Report";

			List<Assets> assets = assetsRepository.findAll();
			List<Assets> newAssets = assets.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newAssets.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}

			return generateReport(reportName, param, newAssets);

		} else if (misReportDto.getCategory().equals("STOCKS")
				&& misReportDto.getSubCategory().equals("INWARDMATERIALS")) {

			String reportName = "/reports/Inward_Materials_Report.jrxml";
			String param = "Inward Materials Report";

			List<InwardApprovedMaterials> inwardMaterials = inwardApprovedMaterialsRepository.findAll();
			List<InwardApprovedMaterials> newInwardMaterials = inwardMaterials.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newInwardMaterials.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}

			return generateReport(reportName, param, newInwardMaterials);

		} else if (misReportDto.getCategory().equals("STOCKS")
				&& misReportDto.getSubCategory().equals("INWARDSPARES")) {

			String reportName = "/reports/Inward_Spares_Report.jrxml";
			String param = "Inward Spares Report";

			List<InwardApprovedSpares> inwardSpares = inwardApprovedSparesRepository.findAll();
			List<InwardApprovedSpares> newInwardSpares = inwardSpares.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newInwardSpares.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}

			return generateReport(reportName, param, newInwardSpares);

		} else if (misReportDto.getCategory().equals("STOCKS") && misReportDto.getSubCategory().equals("INWARDTOOLS")) {

			String reportName = "/reports/Inward_Tools_Report.jrxml";
			String param = "Inward Tools Report";

			List<InwardApprovedTools> inwardToos = inwardApprovedToolsRepository.findAll();
			List<InwardApprovedTools> newInwardToos = inwardToos.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newInwardToos.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}

			return generateReport(reportName, param, newInwardToos);

		} else if (misReportDto.getCategory().equals("STOCKS")
				&& misReportDto.getSubCategory().equals("OUTWARDSTOCKS")) {

			String reportName = "/reports/Outward_Stocks_Report.jrxml";
			String param = "Outward Stocks Report";

			List<StockOrderItems> stockOrderItems = stockOrderItemsRepository.findAll();
			List<StockOrderItems> newStockOrderItems = stockOrderItems.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newStockOrderItems.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newStockOrderItems);
		} else if (misReportDto.getCategory().equals("STOCKS")
				&& misReportDto.getSubCategory().equals("STOCKSRETURN")) {

			String reportName = "/reports/Stocks_Return_Report.jrxml";
			String param = "Stocks Return Report";

			List<ApprovedStocksReturn> approvedStocksReturn = approvedStocksReturnRepository.findAll();
			List<ApprovedStocksReturn> newApprovedStocksReturn = approvedStocksReturn.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newApprovedStocksReturn.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newApprovedStocksReturn);
		} else if (misReportDto.getCategory().equals("STOCKS")
				&& misReportDto.getSubCategory().equals("STOCKSRETURN")) {

			String reportName = "/reports/Stocks_Return_Report.jrxml";
			String param = "Stocks Return Report";

			List<ApprovedStocksReturn> approvedStocksReturn = approvedStocksReturnRepository.findAll();
			List<ApprovedStocksReturn> newApprovedStocksReturn = approvedStocksReturn.stream()
					.filter(obj -> obj.getDateCreated().after(fromDate) && obj.getDateCreated().before(toDate))
					.collect(Collectors.toList());

			if (newApprovedStocksReturn.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newApprovedStocksReturn);
		}  else if (misReportDto.getCategory().equals("WORKORDERS")
				&& misReportDto.getSubCategory().equals("WAITINGWORKORDERITEMS")) {

			String reportName = "/reports/Waiting_Workorder_Items_Report.jrxml";
			String param = "Waiting Workorder Items Report";

			List<WapWorkOrderItems> wapWorkOrderItems = wapWorkOrderItemsRepository.findAll();
			List<WapWorkOrderItems> newWapWorkOrderItems = wapWorkOrderItems.stream()
					.filter(obj -> obj.getCreatedDate().after(fromDate) && obj.getCreatedDate().before(toDate))
					.collect(Collectors.toList());

			if (newWapWorkOrderItems.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newWapWorkOrderItems);
		}else if (misReportDto.getCategory().equals("WORKORDERS")
				&& misReportDto.getSubCategory().equals("WAITINGWORKORDERLABOURS")) {

			String reportName = "/reports/Waiting_Workorder_Labours_Report.jrxml";
			String param = "Waiting Workorder Labours Report";

			List<WapWorkOrderLabours> wapWorkOrderLabours = wapWorkOrderLaboursRepository.findAll();
			List<WapWorkOrderLabours> newWapWorkOrderLabours = wapWorkOrderLabours.stream()
					.filter(obj -> obj.getCreatedDate().after(fromDate) && obj.getCreatedDate().before(toDate))
					.collect(Collectors.toList());

			if (newWapWorkOrderLabours.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newWapWorkOrderLabours);
		}else if (misReportDto.getCategory().equals("WORKORDERS")
				&& misReportDto.getSubCategory().equals("WAITINGWORKORDERVEHICLES")) {

			String reportName = "/reports/Waiting_Workorder_Vehicles_Report.jrxml";
			String param = "Waiting Workorder Vehicles Report";

			List<WapWorkOrderVehicles> wapWorkOrderVehicles = wapWorkOrderVehiclesRepository.findAll();
			List<WapWorkOrderVehicles> newWapWorkOrderVehicles = wapWorkOrderVehicles.stream()
					.filter(obj -> obj.getCreatedDate().after(fromDate) && obj.getCreatedDate().before(toDate))
					.collect(Collectors.toList());

			if (newWapWorkOrderVehicles.isEmpty()) {
				session.setAttribute("message", new Message(message, "danger"));
				return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
			}
			return generateReport(reportName, param, newWapWorkOrderVehicles);
		}else {
			session.setAttribute("message", new Message("Please Select Sub Category Appropriately !", "danger"));
			return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirect).build();
		}
	}

	@GetMapping("/monthly")
	@PreAuthorize("hasAuthority('MIS_MONTHLY_REPORT')")
	public String misMonthly() {
		return "/pages/mis_report/monthly";
	}

}
