package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dao.InwardApprovedMaterialsRepository;
import com.ingroinfo.mm.dao.InwardApprovedSparesRepository;
import com.ingroinfo.mm.dao.InwardApprovedToolsRepository;
import com.ingroinfo.mm.dao.TempIndentItemsRepository;
import com.ingroinfo.mm.dao.WorkOrderItemsRequestRepository;
import com.ingroinfo.mm.dto.GraphDto;
import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.WorkOrderItemsDto;
import com.ingroinfo.mm.entity.ApprovedWorkOrderItems;
import com.ingroinfo.mm.entity.ApprovedWorkOrders;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardTempSpares;
import com.ingroinfo.mm.entity.InwardTempTools;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.TempIndentItems;
import com.ingroinfo.mm.entity.TempStocksReturn;
import com.ingroinfo.mm.entity.TempWorkOrders;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.BrandMasterService;
import com.ingroinfo.mm.service.ItemMasterService;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.UnitMeasureService;

@Controller
@RequestMapping("/stocks")
public class StocksController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	@Autowired
	private StockService stockService;

	@Autowired
	private UnitMeasureService unitMeasureService;

	@Autowired
	private BrandMasterService brandService;

	@Autowired
	private ItemMasterService itemMasterService;

	@Autowired
	private TempIndentItemsRepository tempIndentItemsRepository;

	@Autowired
	private WorkOrderItemsRequestRepository workOrderItemsRequestRepository;

	@Autowired
	private InwardApprovedMaterialsRepository inwardApprovedMaterialsRepository;

	@Autowired
	private InwardApprovedSparesRepository inwardApprovedSparesRepository;

	@Autowired
	private InwardApprovedToolsRepository inwardApprovedToolsRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/dashboard")
	@PreAuthorize("hasAuthority('STOCKS_AVAILABLE')")
	public String availableStocks(Model model) {
		model.addAttribute("title", "Stocks Available | Maintenance Management");
		model.addAttribute("allItems", stockService.getAllStocks());
		return "/pages/stock_management/stock_dashboard";
	}

//	@GetMapping("/dashboard/month")
//	public @ResponseBody String getStocksThree() {
//
//		List<Map<String, Object>> stocksData = new ArrayList<>();
//		try (Connection con = jdbcTemplate.getDataSource().getConnection();
//				CallableStatement cs = con.prepareCall("{? = call stockslastthreemonths()}");) {
//			cs.registerOutParameter(1, Types.REF_CURSOR);
//			cs.execute();
//			ResultSet rs = (ResultSet) cs.getObject(1);
//			while (rs.next()) {
//				Map<String, Object> row = new HashMap<>();
//				row.put("type", rs.getString("type"));
//				row.put("month_name", rs.getString("month_name"));
//				row.put("month_number", rs.getInt("month_number"));
//				row.put("year", rs.getInt("year"));
//				row.put("total_quantity", rs.getInt("total_quantity"));
//				stocksData.add(row);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			return objectMapper.writeValueAsString(stocksData);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}

	@GetMapping("/dashboard/total")
	public @ResponseBody List<GraphDto> getMonthlyTotalQuantity() {
		List<GraphDto> graph = null;
		try {
			String sql = "SELECT * FROM DASHBOARD_MONTHWISE_TOTALSTOCKS";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GraphDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}

	@GetMapping("/dashboard/month")
	public @ResponseBody List<GraphDto> getStockwiseMonthlyQuantity() {
		List<GraphDto> graph = null;
		try {
			String sql = "SELECT * FROM DASHBOARD_MONTHWISE_STOCKS";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GraphDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}

	@GetMapping("/dashboard/outward")
	public @ResponseBody List<GraphDto> getOutwardStocks() {

		List<GraphDto> graph = null;
		try {
			String sql = "SELECT * FROM DASHBOARD_OUTWARD_STOCKS";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GraphDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}
	
	@GetMapping("/dashboard/return")
	public @ResponseBody List<GraphDto> getStocksReturn() {

		List<GraphDto> graph = null;
		try {
			String sql = "SELECT * FROM DASHBOARD_STOCKS_RETURN";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GraphDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}
	
	
	@GetMapping("/graph/materials/chart")
	public @ResponseBody List<GraphDto> getMaterialsData() {
		List<GraphDto> graph = null;
		try {
			String sql = "SELECT * FROM DASHBOARD_MONTHWISE_TOTALSTOCKS";
			graph = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(GraphDto.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graph;
	}

	/******************************************************************/

	@GetMapping("/inward/materials/entry")
	@PreAuthorize("hasAuthority('INWARD_MATERIALS')")
	public String inwardMaterials(Model model, Principal principal) {

		model.addAttribute("title", "Inward Materials Entry | Maintenance Management");
		model.addAttribute("inward", new InwardDto());

		List<InwardTempMaterials> tempMaterials = stockService.getInwardTempMaterials(principal.getName());

		if (tempMaterials.size() == 0) {
			model.addAttribute("emptyList", "No Materials");
			model.addAttribute("subTotal", 0);
		} else {
			Double subTotal = tempMaterials.stream().filter(f -> f.getSubTotal() != null)
					.mapToDouble(o -> o.getSubTotal()).sum();

			model.addAttribute("subTotal", subTotal);
		}

		model.addAttribute("tempMaterials", tempMaterials);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("items", itemMasterService.getAllItemNames("ML"));

		model.addAttribute("brands", brandService.getAllBrandMasters());

		return "/pages/stock_management/inward_materials_entry";
	}

	@PostMapping("/inward/materials/entry/add")
	public String addMaterial(@RequestParam("itemImage") MultipartFile file, @ModelAttribute("inward") InwardDto inward,
			BindingResult bindingResult, HttpSession session, Principal principal) throws IOException {

		inward.setUsername(principal.getName());
		stockService.saveInwardTempMaterials(inward, file);

		return "redirect:/stocks/inward/materials/entry";
	}

	@PostMapping("/inward/materials/entry/addAll")
	public String addMaterials(@ModelAttribute("inward") InwardDto inward, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardTempMaterials> inwardMaterialList = stockService.getInwardTempMaterials(principal.getName());

		if (inwardMaterialList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Material Items  !", "danger"));

			return "redirect:/stocks/inward/materials/entry";
		}

		inward.setUsername(principal.getName());

		if (inward.getIgst() == null) {
			inward.setIgst(0.0);
		}

		stockService.saveInwardMaterials(inward);

		session.setAttribute("message", new Message("All the Materials has been successfully added !", "success"));
		return "redirect:/stocks/inward/materials/entry";
	}

	@GetMapping("/inward/materials/delete/{id}")
	public String deleteMaterial(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteTempMaterial(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/materials/entry";

	}

	@GetMapping("/inward/materials/deleteAll")
	public String deleteMaterials(HttpSession session) {

		stockService.deleteAllMaterials();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/materials/entry";

	}

	@GetMapping("/inward/materials/list")
	public String inwardMaterialList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Materials List | Maintenance Management");

		model.addAttribute("materialsLists", stockService.getInwardAllMaterialsList());

		return "/pages/stock_management/inward_materials_list";
	}

	@GetMapping("/inward/materials/list/approved")
	public String inwardApprovedMaterialList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Approved Materials List | Maintenance Management");

		model.addAttribute("approvedMaterialsLists", stockService.getApprovedMaterialsLists());

		return "/pages/stock_management/inward_materials_approved_list";
	}

	@GetMapping("/inward/materials/get/{id}")
	public @ResponseBody InwardMaterials getInwardMaterials(@PathVariable("id") Long id) {
		return stockService.getInwardMaterial(id);
	}

	@GetMapping("/inward/materials/list/delete/{id}")
	public String deleteMaterialsOfList(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteMaterial(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/materials/list";
	}

	@GetMapping("/inward/materials/chart")
	public String inwardMaterialChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material Chart | Maintenance Management");
		return "/pages/stock_management/inward_materials_chart";
	}

	/******************************************************************/

	@GetMapping("/inward/spares/entry")
	@PreAuthorize("hasAuthority('INWARD_SPARES')")
	public String inwardSpares(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spares Entry | Maintenance Management");
		model.addAttribute("inward", new InwardDto());

		List<InwardTempSpares> tempSpares = stockService.getInwardTempSpares(principal.getName());

		if (tempSpares.size() == 0) {
			model.addAttribute("emptyList", "No Spares");
			model.addAttribute("subTotal", 0);
		} else {
			Double subTotal = tempSpares.stream().filter(f -> f.getSubTotal() != null).mapToDouble(o -> o.getSubTotal())
					.sum();

			model.addAttribute("subTotal", subTotal);
		}

		model.addAttribute("tempSpares", tempSpares);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("items", itemMasterService.getAllItemNames("SP"));

		model.addAttribute("brands", brandService.getAllBrandMasters());

		return "/pages/stock_management/inward_spares_entry";
	}

	@PostMapping("/inward/spares/entry/add")
	public String addSpare(@RequestParam("itemImage") MultipartFile file, @ModelAttribute("inward") InwardDto inward,
			BindingResult bindingResult, HttpSession session, Principal principal) throws IOException {

		inward.setUsername(principal.getName());
		stockService.saveInwardTempSpares(inward, file);

		return "redirect:/stocks/inward/spares/entry";
	}

	@PostMapping("/inward/spares/entry/addAll")
	public String addSpares(@ModelAttribute("inward") InwardDto inward, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardTempSpares> inwardSpareList = stockService.getInwardTempSpares(principal.getName());

		if (inwardSpareList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Spare Items  !", "danger"));

			return "redirect:/stocks/inward/spares/entry";
		}

		inward.setUsername(principal.getName());

		if (inward.getIgst() == null) {
			inward.setIgst(0.0);
		}

		stockService.saveInwardSpares(inward);

		session.setAttribute("message", new Message("All the Spares has been successfully added !", "success"));
		return "redirect:/stocks/inward/spares/entry";
	}

	@GetMapping("/inward/spares/delete/{id}")
	public String deleteSpare(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteTempSpare(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/spares/entry";

	}

	@GetMapping("/inward/spares/deleteAll")
	public String deleteSpares(HttpSession session) {

		stockService.deleteAllSpares();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/spares/entry";

	}

	@GetMapping("/inward/spares/list")
	public String inwardSpareList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spares List | Maintenance Management");

		model.addAttribute("sparesLists", stockService.getInwardAllSparesList());

		return "/pages/stock_management/inward_spares_list";
	}

	@GetMapping("/inward/spares/list/approved")
	public String inwardApprovedSpareList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Approved Spares List | Maintenance Management");

		model.addAttribute("approvedSparesLists", stockService.getApprovedSparesLists());

		return "/pages/stock_management/inward_spares_approved_list";
	}

	@GetMapping("/inward/spares/get/{id}")
	public @ResponseBody InwardSpares getInwardSpares(@PathVariable("id") Long id) {
		return stockService.getInwardSpare(id);
	}

	@GetMapping("/inward/spares/list/delete/{id}")
	public String deleteSparesOfList(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteSpare(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/spares/list";
	}

	@GetMapping("/inward/spares/chart")
	public String inwardSpareChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spares Chart | Maintenance Management");
		return "/pages/stock_management/inward_spares_chart";
	}

	/******************************************************************/

	@GetMapping("/inward/tools/entry")
	@PreAuthorize("hasAuthority('INWARD_TOOLS')")
	public String inwardTools(Model model, Principal principal) {

		model.addAttribute("title", "Inward Tools Entry | Maintenance Management");
		model.addAttribute("inward", new InwardDto());

		List<InwardTempTools> tempTools = stockService.getInwardTempTools(principal.getName());

		if (tempTools.size() == 0) {
			model.addAttribute("emptyList", "No Tools");
			model.addAttribute("subTotal", 0);
		} else {
			Double subTotal = tempTools.stream().filter(f -> f.getSubTotal() != null).mapToDouble(o -> o.getSubTotal())
					.sum();

			model.addAttribute("subTotal", subTotal);
		}

		model.addAttribute("tempTools", tempTools);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("items", itemMasterService.getAllItemNames("TE"));

		model.addAttribute("brands", brandService.getAllBrandMasters());

		return "/pages/stock_management/inward_tools_entry";
	}

	@PostMapping("/inward/tools/entry/add")
	public String addTool(@RequestParam("itemImage") MultipartFile file, @ModelAttribute("inward") InwardDto inward,
			BindingResult bindingResult, HttpSession session, Principal principal) throws IOException {

		inward.setUsername(principal.getName());
		stockService.saveInwardTempTools(inward, file);
		return "redirect:/stocks/inward/tools/entry";
	}

	@PostMapping("/inward/tools/entry/addAll")
	public String addTools(@ModelAttribute("inward") InwardDto inward, BindingResult bindingResult, HttpSession session,
			Principal principal) throws IOException {

		List<InwardTempTools> inwardToolList = stockService.getInwardTempTools(principal.getName());

		if (inwardToolList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Tool Items  !", "danger"));

			return "redirect:/stocks/inward/tools/entry";
		}

		inward.setUsername(principal.getName());

		if (inward.getIgst() == null) {
			inward.setIgst(0.0);
		}

		stockService.saveInwardTools(inward);

		session.setAttribute("message", new Message("All the Spares has been successfully added !", "success"));
		return "redirect:/stocks/inward/tools/entry";
	}

	@GetMapping("/inward/tools/delete/{id}")
	public String deleteTool(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteTempTool(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/tools/entry";

	}

	@GetMapping("/inward/tools/deleteAll")
	public String deleteTools(HttpSession session) {

		stockService.deleteAllTools();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/tools/entry";

	}

	@GetMapping("/inward/tools/list")
	public String inwardToolList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Tools List | Maintenance Management");

		model.addAttribute("toolsLists", stockService.getInwardAllToolsList());

		return "/pages/stock_management/inward_tools_list";
	}

	@GetMapping("/inward/tools/list/approved")
	public String inwardApprovedToolList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Approved Tools List | Maintenance Management");

		model.addAttribute("approvedToolsLists", stockService.getApprovedToolsLists());

		return "/pages/stock_management/inward_tools_approved_list";
	}

	@GetMapping("/inward/tools/get/{id}")
	public @ResponseBody InwardTools getInwardTools(@PathVariable("id") Long id) {
		return stockService.getInwardTool(id);
	}

	@GetMapping("/inward/tools/list/delete/{id}")
	public String deleteToolsOfList(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteTool(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/tools/list";
	}

	@GetMapping("/inward/tools/chart")
	public String inwardToolChart(Model model) {

		model.addAttribute("title", "Inward Tools Chart | Maintenance Management");
		return "/pages/stock_management/inward_tools_chart";
	}

	/******************************************************************/

	@GetMapping("/outward")
	@PreAuthorize("hasAuthority('OUTWARD_STOCKS')")
	public String outwardMaterials(Model model) {

		model.addAttribute("title", "Outward Stocks | Maintenance Management");

		List<Long> workOrdersNos = stockService.getWorkOrdersNos();

		model.addAttribute("workOrdersNos", workOrdersNos);

		model.addAttribute("workOrderItems", "No Work Order Items");

		if (workOrdersNos.size() == 0) {
			model.addAttribute("emptyList", "No Work Orders");
		}

		return "/pages/stock_management/outward_stocks";
	}

	@GetMapping("/outward/get/{workOrderNo}")
	public String outwardStocks(@PathVariable Long workOrderNo, Model model) {

		model.addAttribute("title", "Outward Stocks | Maintenance Management");

		model.addAttribute("workOrdersNos", stockService.getWorkOrdersNos());

		model.addAttribute("workOrderNo", workOrderNo);

		model.addAttribute("workOrderItems", null);

		model.addAttribute("getWorkOrderItems", stockService.getWorkOrderItems(workOrderNo));

		model.addAttribute("workorder", new TempWorkOrders());

		List<WorkOrderItemsDto> qty = stockService.checkStockQuantity(workOrderNo);

		if (qty.size() != 0) {
			model.addAttribute("stockQuantities", qty);
		} else {
			model.addAttribute("stockQuantities", null);
		}

		return "/pages/stock_management/outward_stocks";
	}

	@PostMapping("/outward/item/quantity")
	public @ResponseBody void quantity(@RequestBody TempIndentItems indentItems) {
		String itemId = indentItems.getItemId();
		Long workOrderNo = indentItems.getWorkOrderNo();
		if (itemId != null) {
			Optional<TempIndentItems> tempIndentItems = tempIndentItemsRepository.findByItemIdAndWorkOrderNo(itemId,
					workOrderNo);
			DecimalFormat df = new DecimalFormat("#.##");
			tempIndentItems.get()
					.setTotalCost(Double.parseDouble(df.format(indentItems.getMrpRate() * indentItems.getQty())));
			tempIndentItems.get().setFinalQuantity(indentItems.getQty());
			tempIndentItemsRepository.save(tempIndentItems.get());
		}
	}

	@PostMapping("/outward/item/delete")
	public @ResponseBody void delItem(@RequestBody TempIndentItems indentItems, Principal principal) {
		String itemId = indentItems.getItemId();
		Long workOrderNo = indentItems.getWorkOrderNo();

		if (itemId != null) {
			Optional<TempIndentItems> tempIndentItems = tempIndentItemsRepository.findByItemIdAndWorkOrderNo(itemId,
					workOrderNo);
			Long stockId = workOrderItemsRequestRepository.findByWorkOrderNoAndItemId(workOrderNo, itemId)
					.getRecordId();
			stockService.saveRemovedItems(itemId, workOrderNo, principal.getName());
			tempIndentItemsRepository.deleteById(tempIndentItems.get().getRecordId());
			workOrderItemsRequestRepository.deleteById(stockId);
		}
	}

	@PostMapping("/get/quantity/{itemId}")
	public @ResponseBody int getQuantity(@PathVariable("itemId") String itemId) {

		int quantity = 0;

		InwardApprovedMaterials inwardApprovedMaterials = inwardApprovedMaterialsRepository.findByItemId(itemId);
		InwardApprovedSpares inwardApprovedSpares = inwardApprovedSparesRepository.findByItemId(itemId);
		InwardApprovedTools inwardApprovedTools = inwardApprovedToolsRepository.findByItemId(itemId);

		if (inwardApprovedMaterials != null) {
			quantity = inwardApprovedMaterials.getAvailableQty();
		}
		if (inwardApprovedSpares != null) {
			quantity = inwardApprovedSpares.getAvailableQty();
		}
		if (inwardApprovedTools != null) {
			quantity = inwardApprovedTools.getAvailableQty();
		}

		return quantity;
	}

	@PostMapping("/outward/workorder/items")
	public String workOrerItems(@ModelAttribute("workorder") TempWorkOrders workOrders, BindingResult bindingResult,
			HttpSession session, Principal principal) {
		Long workOrderNo = workOrders.getWorkOrderNo();
		boolean itemAvailability = stockService.notAvailableItems(workOrderNo);
		boolean workOrderItemsSize = stockService.getTempWorkOrderItems(workOrderNo);

		if (workOrderItemsSize) {
			session.setAttribute("message",
					new Message("To place an order, you need to have at least one item required !", "danger"));
			return "redirect:/stocks/outward/get/" + workOrderNo;
		}

		if (itemAvailability) {
			session.setAttribute("message", new Message(
					"To place the order, please remove the items that are not available from the list.", "danger"));
			return "redirect:/stocks/outward/get/" + workOrderNo;
		}
		if (workOrders.getWorkOrderNo() != 0 && workOrders.getWorkOrderNo() != null) {
			stockService.saveWorkOrder(workOrders, principal.getName());
			session.setAttribute("message", new Message("Workorder Items has been successfully placed !", "success"));
		} else {
			session.setAttribute("message", new Message("Workorder Number is null !", "danger"));
			return "redirect:/stocks/outward/materials/entry";
		}
		return "redirect:/stocks/outward";
	}

	@GetMapping("/outward/chart")
	public String outwardChart(Model model) {
		model.addAttribute("title", "Outward Stocks Chart | Maintenance Management");
		return "/pages/stock_management/outward_stocks_chart";
	}

	@GetMapping("/outward/list")
	public String outwardList(Model model) {
		model.addAttribute("title", "Outward Stocks List | Maintenance Management");

		model.addAttribute("outwardStocksLists", stockService.getOutwardWorkOrders());
		return "/pages/stock_management/outward_stocks_list";
	}

	@GetMapping("/outward/list/items/{workOrderNo}")
	public String outwardListItems(@PathVariable("workOrderNo") Long workOrderNo, Model model) {

		model.addAttribute("title", "Outward Stocks Workorder Items | Maintenance Management");

		model.addAttribute("outwardStocksListItems", stockService.getOutwardWorkOrderItems(workOrderNo));
		model.addAttribute("outwardStocksWorkorderNo", stockService.getOutwardWorkOrder(workOrderNo));
		return "/pages/stock_management/outward_stocks_list_items";
	}

	@GetMapping("/outward/approved/list")
	public String outwardApprovedList(Model model, Principal principal) {
		model.addAttribute("title", "Outward Stocks Approved List | Maintenance Management");
		model.addAttribute("approvedOutwardStocksLists", stockService.getOutwardApprovedWorkOrders());
		return "/pages/stock_management/outward_stocks_approved_list";
	}

	@GetMapping("/outward/approved/list/items/{workOrderNo}")
	public String outwardApprovedListItems(@PathVariable("workOrderNo") Long workOrderNo, Model model,
			Principal principal) {
		model.addAttribute("title", "Outward Stocks Approved Items List | Maintenance Management");

		model.addAttribute("approvedOutwardStocksListItems",
				stockService.getOutwardApprovedWorkOrderItems(workOrderNo));
		model.addAttribute("approvedOutwardStocksWorkorderNo", stockService.getOutwardApprovedWorkOrder(workOrderNo));

		return "/pages/stock_management/outward_stocks_approved_list_items";
	}

	/******************************************************************/

	@GetMapping("/return/entry")
	@PreAuthorize("hasAuthority('STOCKS_RETURN')")
	public String returnEntry(Model model, Principal principal) {
		model.addAttribute("title", "Stock Returns Entry | Maintenance Management");

		model.addAttribute("workOrders", stockService.getOutwardApprovedWorkOrders());
		model.addAttribute("stockReturn", new TempStocksReturn());

		List<TempStocksReturn> tempStocksReturn = stockService.getTempStockReturn(principal.getName());

		model.addAttribute("tempStockReturns", tempStocksReturn);

		if (tempStocksReturn.size() == 0) {
			model.addAttribute("emptyList", "No Return Stocks");
			model.addAttribute("returnCost", 0);
		} else {
			Double returnCost = tempStocksReturn.stream().filter(f -> f.getReturnTotalCost() != null)
					.mapToDouble(o -> o.getReturnTotalCost()).sum();

			model.addAttribute("returnCost", returnCost);
		}

		return "/pages/stock_management/stock_returns_entry";
	}

	@PostMapping("/return/items/entry/add")
	public String returnItem(@RequestParam("itemImage") MultipartFile file,
			@ModelAttribute("stockReturn") TempStocksReturn stocksReturn, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		stocksReturn.setUsername(principal.getName());

		if (stockService.checkReturnedItem(stocksReturn)) {
			TempStocksReturn tempStocksReturn = stockService.getReturnQuantity(stocksReturn);

			int orderedQuantity = tempStocksReturn.getOrderQuantity();

			int returnedQuantity = tempStocksReturn.getReturnQuantity();

			int canReturnQuantity = orderedQuantity - returnedQuantity;
			session.setAttribute("message",
					new Message(returnedQuantity + " items have already been returned out of the " + orderedQuantity
							+ " ordered items, which means you can return maximum of " + canReturnQuantity + " items.",
							"danger"));
			return "redirect:/stocks/return/entry";
		}

		stockService.saveReturnItem(stocksReturn, file);
		session.setAttribute("message", new Message(" Items has been successfully placed for return!", "success"));
		return "redirect:/stocks/return/entry";
	}

	@PostMapping("/return/items/entry/addAll")
	public String addReturnItems(@ModelAttribute("stockReturn") TempStocksReturn stocksReturn,
			BindingResult bindingResult, HttpSession session, Principal principal) throws IOException {

		List<TempStocksReturn> tempStocksReturn = stockService.getTempStockReturn(principal.getName());

		if (tempStocksReturn.size() == 0) {
			session.setAttribute("message", new Message("Please Add Return Items  !", "danger"));
			return "redirect:/stocks/return/entry";
		}

		stocksReturn.setUsername(principal.getName());
		stockService.saveReturnItems(stocksReturn);
		session.setAttribute("message", new Message("All the Item has been successfully added for Return!", "success"));
		return "redirect:/stocks/return/entry";
	}

	@GetMapping("/return/items/{workOrderNo}")
	public @ResponseBody String getWorkorderItemForReturn(@PathVariable("workOrderNo") Long workOrderNo) {

		String json = null;
		List<ApprovedWorkOrderItems> list = stockService.getOutwardApprovedWorkOrderItems(workOrderNo);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@GetMapping("/return/workorder/{workOrderNo}")
	public @ResponseBody ApprovedWorkOrders getWorkorderDetails(@PathVariable("workOrderNo") Long workOrderNo) {
		return stockService.getOutwardApprovedWorkOrder(workOrderNo);
	}

	@GetMapping("/return/items/details/{itemId}/{workOrderNo}")
	public @ResponseBody ApprovedWorkOrderItems getWorkorderItemDetailsForReturn(@PathVariable("itemId") String itemId,
			@PathVariable("workOrderNo") Long workOrderNo) {
		return stockService.getWorkorderItemDetails(itemId, workOrderNo);
	}

	@GetMapping("/return/items/delete/{id}")
	public String deleteTempReturnItem(@PathVariable("id") Long id, HttpSession session) {
		stockService.deleteTempReturnItem(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));
		return "redirect:/stocks/return/entry";
	}

	@GetMapping("/return/items/deleteAll")
	public String deleteAllTempReturnItem(HttpSession session) {

		stockService.deleteAllTempReturnItem();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));
		return "redirect:/stocks/return/entry";
	}

	@GetMapping("/return/item/list/delete/{id}")
	public String deleteReturnItem(@PathVariable("id") Long id, HttpSession session) {

		stockService.deleteReturnItem(id);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));
		return "redirect:/stocks/return/list";
	}

	@GetMapping("/return/chart")
	public String returnChart(Model model) {
		model.addAttribute("title", "Stock Returns Chart | Maintenance Management");
		return "/pages/stock_management/stock_returns_chart";
	}

	@GetMapping("/return/list")
	public String returnList(Model model) {
		model.addAttribute("title", "Stock Returns List | Maintenance Management");
		model.addAttribute("returnedItemLists", stockService.getStockReturnItemList());
		return "/pages/stock_management/stock_returns_list";
	}

	@GetMapping("/return/approved/list")
	public String returnApprovedList(Model model) {
		model.addAttribute("title", "Stock Returns Approved List | Maintenance Management");

		model.addAttribute("returnedItemLists", stockService.getApprovedStockReturnItemList());
		return "/pages/stock_management/stock_returns_approved_list";
	}

}