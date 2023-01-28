package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.InwardToolsBundle;
import com.ingroinfo.mm.entity.InwardToolsTempBundle;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.InwardMaterialBundles;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardSpare;
import com.ingroinfo.mm.entity.InwardSpareBundle;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.CategoryService;
import com.ingroinfo.mm.service.HsnCodeService;
import com.ingroinfo.mm.service.StockService;
import com.ingroinfo.mm.service.UnitMeasureService;

@Controller
@RequestMapping("/stocks")
public class StockController {

	@ModelAttribute
	private void UserDetailsService(Model model, Principal principal) {
		model.addAttribute("getLoggedUser", principal.getName());
	}

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AdminService adminService;

	@Autowired
	private StockService stockService;

	@Autowired
	private UnitMeasureService unitMeasureService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private HsnCodeService hsnCodeService;

	@GetMapping("/dashboard")
	public String availableStocks() {
		return "/pages/stock_management/stock_available";
	}

	@GetMapping("/inward/material")
	public String inwardMaterials(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material Page");
		model.addAttribute("inwardItem", new InwardItemDto());

		List<InwardTempMaterials> inwardMaterialList = stockService.getInwardTempMaterialList(principal.getName());
		if (inwardMaterialList.size() == 0) {
			model.addAttribute("emptyList", "No Materials");
		} else {
			Long subTotal = inwardMaterialList.stream().filter(f -> f.getTotalAmount() != null)
					.mapToLong(o -> o.getTotalAmount()).sum();
			model.addAttribute("subTotal", subTotal);
		}
		model.addAttribute("inwardMaterialLists", inwardMaterialList);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("categories", categoryService.findAllCategory());

		model.addAttribute("items", hsnCodeService.findAllHsnCode());

		return "/pages/stock_management/inward_material";
	}

	@PostMapping("/inward/material/add")
	public String addMaterial(@RequestParam("materialImage") MultipartFile file,
			@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult, HttpSession session,
			Principal principal) throws IOException {

		String companyName = "";
		Long ItemOrhsnId = Long.parseLong(inwardItemDto.getItemName());
		Company company = adminService.getCompanyByUsername(principal.getName());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin_Data";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardTempMaterials inwardMaterialTemp = modelMapper.map(inwardItemDto, InwardTempMaterials.class);
		String itemName = hsnCodeService.getHsnById(ItemOrhsnId).getItemName();
		String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "." + tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Materials\\";

		adminService.saveFile(uploadDir, fileName, file);
		inwardMaterialTemp.setMaterialImage(fileName);
		inwardMaterialTemp.setItemName(itemName);
		inwardMaterialTemp.setImagePath("/Company/" + companyName + "/Inward_Materials/");
		inwardMaterialTemp.setItemId(ItemOrhsnId);
		inwardMaterialTemp.setUsername(principal.getName());
		inwardMaterialTemp.setTotalAmount(inwardMaterialTemp.getCostRate() * inwardMaterialTemp.getTotalQuantity());
		stockService.saveInwardMaterial(inwardMaterialTemp);

		return "redirect:/stocks/inward/material";
	}

	@PostMapping("/inward/materials/add")
	public String addMaterials(@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardTempMaterials> inwardMaterialList = stockService.getInwardTempMaterialList(principal.getName());

		if (inwardMaterialList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Material Items  !", "danger"));

			return "redirect:/stocks/inward/material";
		}

		if (inwardItemDto.getIgst() == null) {
			inwardItemDto.setIgst(0.0);
		}

		if (inwardItemDto.getGstType() == "Exempted") {
			inwardItemDto.setIgst(0.0);
			inwardItemDto.setSgst(0.0);
			inwardItemDto.setCgst(0.0);
		}

		InwardMaterialBundles inwardMaterial = modelMapper.map(inwardItemDto, InwardMaterialBundles.class);

		inwardMaterial.setUsername(principal.getName());

		stockService.saveInwardMaterials(inwardMaterial);

		session.setAttribute("message", new Message("All the Item has been successfully added !", "success"));
		return "redirect:/stocks/inward/material";
	}

	@GetMapping("/inward/material/delete")
	public String deleteMaterial(@RequestParam("id") Long tempBunleId, HttpSession session) {

		stockService.deleteTempBundleMaterial(tempBunleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/material";

	}

	@GetMapping("/inward/materials/delete")
	public String deleteMaterials(HttpSession session) {

		stockService.deleteAllMaterials();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/material";

	}

	@GetMapping("/inward/material/list")
	public String inwardMaterialList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material List | Maintenance Management");

		model.addAttribute("materialsLists", stockService.getInwarAllMaterialList());

		return "/pages/stock_management/inward_materials_list";
	}

	@GetMapping("/inward/material/list/delete")
	public String deleteMaterialsOfList(@RequestParam("id") Long materialId, HttpSession session) {

		stockService.deleteBundleMaterial(materialId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/material/list";

	}

	@GetMapping("/inward/material/bundle/list/delete")
	public String deleteMaterialsOfBundleList(@RequestParam("id") Long bundleId, HttpSession session) {

		InwardMaterials materialId = stockService.getMaterialById(bundleId);

		boolean deletedAll = stockService.deleteBundleMaterial(bundleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		if (deletedAll) {
			session.setAttribute("message",
					new Message("Bundle has been removed successfully  from the list !", "danger"));
			return "redirect:/stocks/inward/material/bundles";
		}

		return "redirect:/stocks/inward/material/bundle/view/" + materialId.getMaterialBundle().getBundleId();
	}

	@GetMapping("/inward/material/bundles")
	public String viewMaterialsOfList(Model model) {

		model.addAttribute("title", "Inward Bundles | Maintenance Management");
		model.addAttribute("bundleMaterialsLists", stockService.getMaterialsBundlesList());

		return "/pages/stock_management/inward_material_bundles";

	}

	@GetMapping("/inward/material/bundle/view/{id}")
	public String viewMaterialsOfList(@PathVariable Long id, Model model) {

		model.addAttribute("title", "Inward Bundle List | Maintenance Management");
		model.addAttribute("bundledMaterials", stockService.getBundledMaterialsById(id));

		return "/pages/stock_management/inward_material_bundle_list";

	}

	@GetMapping("/inward/material/chart")
	public String inwardMaterialChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material Chart | Maintenance Management");
		return "/pages/stock_management/inward_material_chart";
	}

	@GetMapping("/inward/spare")
	public String inwardSpares(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spares | Maintenance Management");
		model.addAttribute("inwardItem", new InwardItemDto());

		List<InwardSpareTempBundle> inwardSpareList = stockService.getInwardTempSpareList(principal.getName());
		if (inwardSpareList.size() == 0) {
			model.addAttribute("emptyList", "No Spares");
		} else {
			Long subTotal = inwardSpareList.stream().filter(f -> f.getTotalAmount() != null)
					.mapToLong(o -> o.getTotalAmount()).sum();
			model.addAttribute("subTotal", subTotal);
		}
		model.addAttribute("inwardSpareLists", inwardSpareList);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("categories", categoryService.findAllCategory());

		return "/pages/stock_management/inward_spares";
	}

	@PostMapping("/inward/spare/add")
	public String addSpare(@RequestParam("spareImage") MultipartFile file,
			@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult, HttpSession session,
			Principal principal) throws IOException {

		String companyName = "";

		Company company = adminService.getCompanyByUsername(principal.getName());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin_Data";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardSpareTempBundle inwardSpareTempBundle = modelMapper.map(inwardItemDto, InwardSpareTempBundle.class);

		String fileName = inwardItemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Spares\\";

		adminService.saveFile(uploadDir, fileName, file);
		inwardSpareTempBundle.setSpareImage(fileName);
		inwardSpareTempBundle.setImagePath("/Company/" + companyName + "/Inward_Spares/");
		inwardSpareTempBundle.setUsername(principal.getName());
		inwardSpareTempBundle
				.setTotalAmount(inwardSpareTempBundle.getCostRate() * inwardSpareTempBundle.getTotalQuantity());
		stockService.saveInwardSpare(inwardSpareTempBundle);

		return "redirect:/stocks/inward/spare";
	}

	@PostMapping("/inward/spares/add")
	public String addSpares(@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardSpareTempBundle> inwardSpareList = stockService.getInwardTempSpareList(principal.getName());

		if (inwardSpareList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Spare Items !", "danger"));

			return "redirect:/stocks/inward/spare";
		}

		if (inwardItemDto.getIgst() == null) {
			inwardItemDto.setIgst(0.0);
		}

		if (inwardItemDto.getGstType() == "Exempted") {
			inwardItemDto.setIgst(0.0);
			inwardItemDto.setSgst(0.0);
			inwardItemDto.setCgst(0.0);
		}

		InwardSpare inwardSpare = modelMapper.map(inwardItemDto, InwardSpare.class);

		inwardSpare.setUsername(principal.getName());

		stockService.saveInwardSpares(inwardSpare);

		session.setAttribute("message", new Message("All the Item has been successfully added !", "success"));
		return "redirect:/stocks/inward/spare";
	}

	@GetMapping("/inward/spare/delete")
	public String deleteSpare(@RequestParam("id") Long tempBunleId, HttpSession session) {

		stockService.deleteTempBundleSpare(tempBunleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/spare";

	}

	@GetMapping("/inward/spares/delete")
	public String deleteSpares(HttpSession session) {

		stockService.deleteAllSpares();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/spare";

	}

	@GetMapping("/inward/spare/list")
	public String inwardSpareList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spare List | Maintenance Management");
		model.addAttribute("sparesLists", stockService.getInwardAllSpareList());

		return "/pages/stock_management/inward_spares_list";
	}

	@GetMapping("/inward/spare/list/delete")
	public String deleteSparesOfList(@RequestParam("id") Long spareId, HttpSession session) {

		stockService.deleteBundleSpare(spareId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/spare/list";

	}

	@GetMapping("/inward/spare/bundle/list/delete")
	public String deleteSparesOfBundleList(@RequestParam("id") Long bundleId, HttpSession session) {

		InwardSpareBundle spareId = stockService.getSpareById(bundleId);

		boolean deletedAll = stockService.deleteBundleSpare(bundleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		if (deletedAll) {
			session.setAttribute("message",
					new Message("Bundle has been removed successfully  from the list !", "danger"));
			return "redirect:/stocks/inward/spare/bundles";
		}

		return "redirect:/stocks/inward/spare/bundle/view/" + spareId.getInwardSpare().getAllSparesId();
	}

	@GetMapping("/inward/spare/bundles")
	public String viewSparesOfList(Model model) {

		model.addAttribute("title", "Inward Spares Bundles | Maintenance Management");
		model.addAttribute("bundleSparesLists", stockService.getSparesBundlesList());

		return "/pages/stock_management/inward_spares_bundles";

	}

	@GetMapping("/inward/spare/bundle/view/{id}")
	public String viewSparesOfList(@PathVariable Long id, Model model) {

		model.addAttribute("title", "Inward Spares Bundle List | Maintenance Management");
		model.addAttribute("bundledSpares", stockService.getBundledSparesById(id));

		return "/pages/stock_management/inward_spares_bundle_list";

	}

	@GetMapping("/inward/spare/chart")
	public String inwardSpareChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spare Chart | Maintenance Management");

		return "/pages/stock_management/inward_spares_chart";
	}

	@GetMapping("/inward/tools")
	public String inwardTools(Model model, Principal principal) {

		model.addAttribute("title", "Inward Tools | Maintenance Management");
		model.addAttribute("inwardItem", new InwardItemDto());

		List<InwardToolsTempBundle> inwardToolsList = stockService.getInwardTempToolsList(principal.getName());

		if (inwardToolsList.size() == 0) {
			model.addAttribute("emptyList", "No Tools");
		} else {
			Long subTotal = inwardToolsList.stream().filter(f -> f.getTotalAmount() != null)
					.mapToLong(o -> o.getTotalAmount()).sum();
			model.addAttribute("subTotal", subTotal);
		}
		model.addAttribute("inwardToolsLists", inwardToolsList);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("categories", categoryService.findAllCategory());

		return "/pages/stock_management/inward_tools";
	}

	@PostMapping("/inward/tools/add")
	public String addTools(@RequestParam("toolsImage") MultipartFile file,
			@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult, HttpSession session,
			Principal principal) throws IOException {

		String companyName = "";

		Company company = adminService.getCompanyByUsername(principal.getName());

		if (company != null) {
			companyName = company.getCompanyName();
		} else {
			companyName = "Admin_Data";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardToolsTempBundle inwardToolsTempBundle = modelMapper.map(inwardItemDto, InwardToolsTempBundle.class);

		String fileName = inwardItemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Tools\\";

		adminService.saveFile(uploadDir, fileName, file);
		inwardToolsTempBundle.setToolsImage(fileName);
		inwardToolsTempBundle.setImagePath("/Company/" + companyName + "/Inward_Tools/");
		inwardToolsTempBundle.setUsername(principal.getName());
		inwardToolsTempBundle
				.setTotalAmount(inwardToolsTempBundle.getCostRate() * inwardToolsTempBundle.getTotalQuantity());
		stockService.saveInwardTools(inwardToolsTempBundle);

		return "redirect:/stocks/inward/tools";
	}

	@PostMapping("/inward/alltools/add")
	public String addAllTools(@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardToolsTempBundle> inwardToolsList = stockService.getInwardTempToolsList(principal.getName());

		if (inwardToolsList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Tools Items !", "danger"));

			return "redirect:/stocks/inward/tools";
		}

		if (inwardItemDto.getIgst() == null) {
			inwardItemDto.setIgst(0.0);
		}

		if (inwardItemDto.getGstType() == "Exempted") {
			inwardItemDto.setIgst(0.0);
			inwardItemDto.setSgst(0.0);
			inwardItemDto.setCgst(0.0);
		}

		InwardTools inwardTools = modelMapper.map(inwardItemDto, InwardTools.class);

		inwardTools.setUsername(principal.getName());

		stockService.saveInwardAllTools(inwardTools);

		session.setAttribute("message", new Message("All the Item has been successfully added !", "success"));

		return "redirect:/stocks/inward/tools";
	}

	@GetMapping("/inward/tools/delete")
	public String deleteTools(@RequestParam("id") Long tempBunleId, HttpSession session) {

		stockService.deleteTempBundleTools(tempBunleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/tools";

	}

	@GetMapping("/inward/alltools/delete")
	public String deleteAllTools(HttpSession session) {

		stockService.deleteAllTools();
		session.setAttribute("message",
				new Message("All Item has been removed successfully from the list !", "success"));

		return "redirect:/stocks/inward/tools";

	}

	@GetMapping("/inward/tools/list")
	public String inwardToolsList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Tools List | Maintenance Management");
		model.addAttribute("toolsLists", stockService.getInwardAllToolsList());

		return "/pages/stock_management/inward_tools_list";
	}

	@GetMapping("/inward/tools/list/delete")
	public String deleteToolsOfList(@RequestParam("id") Long toolsId, HttpSession session) {

		stockService.deleteBundleTools(toolsId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		return "redirect:/stocks/inward/tools/list";
	}

	@GetMapping("/inward/tools/bundle/list/delete")
	public String deleteToolsOfBundleList(@RequestParam("id") Long bundleId, HttpSession session) {

		InwardToolsBundle spareId = stockService.getToolsById(bundleId);

		boolean deletedAll = stockService.deleteBundleTools(bundleId);
		session.setAttribute("message", new Message("Item has been removed successfully from the list !", "danger"));

		if (deletedAll) {
			session.setAttribute("message",
					new Message("Bundle has been removed successfully  from the list !", "danger"));
			return "redirect:/stocks/inward/tools/bundles";
		}

		return "redirect:/stocks/inward/tools/bundle/view/" + spareId.getInwardTools().getAllToolsId();
	}

	@GetMapping("/inward/tools/bundles")
	public String viewToolsOfList(Model model) {

		model.addAttribute("title", "Inward Tools Bundles | Maintenance Management");
		model.addAttribute("bundleToolsLists", stockService.getToolsBundlesList());

		return "/pages/stock_management/inward_tools_bundles";

	}

	@GetMapping("/inward/tools/bundle/view/{id}")
	public String viewToolsOfList(@PathVariable Long id, Model model) {

		model.addAttribute("title", "Inward Tools Bundle List | Maintenance Management");
		model.addAttribute("bundledTools", stockService.getBundledToolsById(id));

		return "/pages/stock_management/inward_tools_bundle_list";

	}

	@GetMapping("/inward/tools/chart")
	public String inwardToolsChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Tools Chart | Maintenance Management");
		return "/pages/stock_management/inward_tools_chart";
	}

	@GetMapping("/outward/materials")
	public String outwardMaterials() {
		return "/pages/stock_management/outward_material";
	}

	@GetMapping("/outward/spares")
	public String outwardSpares() {
		return "/pages/stock_management/outward_spares";
	}

	@GetMapping("/outward/tools")
	public String outwardTools() {
		return "/pages/stock_management/outward_tools_and_equipment";
	}

	@GetMapping("/material/return")
	public String materialReturn() {
		return "/pages/stock_management/stock_material_return";
	}

	@GetMapping("/spares/return")
	public String sparesReturn() {
		return "/pages/stock_management/stock_spares_return";
	}

	@GetMapping("/tools/return")
	public String toolsReturn() {
		return "/pages/stock_management/stock_tools_return";
	}

	@GetMapping("/material/reject")
	public String materialReject() {
		return "/pages/stock_management/stock_material_reject";
	}

	@GetMapping("/spares/reject")
	public String sparesReject() {
		return "/pages/stock_management/stock_spares_reject";
	}

	@GetMapping("/tools/reject")
	public String toolsReject() {
		return "/pages/stock_management/stock_tools_reject";
	}

}
