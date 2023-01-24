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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.entity.InwardSpareTempBundle;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.InwardMaterial;
import com.ingroinfo.mm.entity.InwardMaterialTempBundle;
import com.ingroinfo.mm.entity.InwardSpare;
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

		List<InwardMaterialTempBundle> inwardMaterialList = stockService.getInwardTempMaterialList(principal.getName());
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
			companyName = "Admin Data";
		}

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardMaterialTempBundle inwardMaterialTemp = modelMapper.map(inwardItemDto, InwardMaterialTempBundle.class);
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

		List<InwardMaterialTempBundle> inwardMaterialList = stockService.getInwardTempMaterialList(principal.getName());

		if (inwardMaterialList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Material Items  !", "danger"));

			return "redirect:/stocks/inward/material";
		}

		if (inwardItemDto.getIgst() == null) {
			inwardItemDto.setIgst("0");
		}
		InwardMaterial inwardMaterial = modelMapper.map(inwardItemDto, InwardMaterial.class);

		inwardMaterial.setUsername(principal.getName());

		stockService.saveInwardMaterials(inwardMaterial);

		session.setAttribute("message", new Message("All the Item has been successfully added !", "success"));
		return "redirect:/stocks/inward/material";
	}

	@GetMapping("/inward/material/delete")
	public String deleteMaterial(@RequestParam("id") Long tempBunleId, HttpSession session) {
		stockService.deleteTempBundleMaterial(tempBunleId);
		session.setAttribute("message", new Message("Item has been successfully removed from the list !", "success"));
		return "redirect:/stocks/inward/material";

	}

	@GetMapping("/inward/materials/delete")
	public String deleteMaterials(HttpSession session) {
		stockService.deleteAllMaterials();
		session.setAttribute("message",
				new Message("All Item has been successfully removed from the list !", "success"));
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
		session.setAttribute("message", new Message("Item has been successfully removed from the list !", "danger"));
		return "redirect:/stocks/inward/material/list";

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
			inwardItemDto.setIgst("0");
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
		session.setAttribute("message", new Message("Item has been successfully removed from the list !", "success"));
		return "redirect:/stocks/inward/spare";

	}

	@GetMapping("/inward/spares/delete")
	public String deleteSpares(HttpSession session) {
		stockService.deleteAllSpares();
		session.setAttribute("message",
				new Message("All Item has been successfully removed from the list !", "success"));
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
		session.setAttribute("message", new Message("Item has been successfully removed from the list !", "danger"));
		return "redirect:/stocks/inward/spare/list";

	}

	@GetMapping("/inward/spare/chart")
	public String inwardSpareChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Spare Chart | Maintenance Management");
		return "/pages/stock_management/inward_spare_chart";
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
