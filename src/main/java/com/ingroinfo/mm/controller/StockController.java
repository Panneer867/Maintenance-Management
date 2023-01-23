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
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.Company;
import com.ingroinfo.mm.entity.InwardItemTemp;
import com.ingroinfo.mm.entity.InwardMaterial;
import com.ingroinfo.mm.helper.Message;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.CategoryService;
import com.ingroinfo.mm.service.HsnCodeService;
import com.ingroinfo.mm.service.MaterialService;
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
	private MaterialService materialService;

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

		List<InwardItemTemp> inwardItemList = materialService.getInwardTempItemList(principal.getName());
		if (inwardItemList.size() == 0) {
			model.addAttribute("emptyList", "No Materials");
		} else {
			Long subTotal = inwardItemList.stream().filter(f -> f.getTotalAmount() != null).mapToLong(o -> o.getTotalAmount()).sum();
			model.addAttribute("subTotal", subTotal);
		}
		model.addAttribute("inwardItemLists", inwardItemList);

		model.addAttribute("unitOfMeasures", unitMeasureService.getAllUnitMeasure());

		model.addAttribute("categories", categoryService.findAllCategory());

		model.addAttribute("items", hsnCodeService.findAllHsnCode());

		
		return "/pages/stock_management/inward_material";
	}

	@PostMapping("/inward/add")
	public String itemAdd(@RequestParam("materialImage") MultipartFile file,
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

		InwardItemTemp inwardItemTemp = modelMapper.map(inwardItemDto, InwardItemTemp.class);
		String itemName = hsnCodeService.getHsnById(ItemOrhsnId).getItemName();
		String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "." + tokens.get();
		String uploadDir = "C:\\Company\\" + companyName + "\\Inward_Materials\\";

		adminService.saveFile(uploadDir, fileName, file);
		inwardItemTemp.setMaterialImage(fileName);
		inwardItemTemp.setItemName(itemName);
		inwardItemTemp.setImagePath("/Company/" + companyName + "/Inward_Materials/");
		inwardItemTemp.setItemId(ItemOrhsnId);
		inwardItemTemp.setUsername(principal.getName());
		inwardItemTemp.setTotalAmount(inwardItemTemp.getCostRate() * inwardItemTemp.getTotalQuantity());
		materialService.saveInwardItem(inwardItemTemp);

		return "redirect:/stocks/inward/material";
	}

	@PostMapping("/inward/materials/add")
	public String itemAdd(@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult,
			HttpSession session, Principal principal) throws IOException {

		List<InwardItemTemp> inwardItemList = materialService.getInwardTempItemList(principal.getName());

		if (inwardItemList.size() == 0) {

			session.setAttribute("message", new Message("Please Add Material Items  !", "danger"));

			return "redirect:/stocks/inward/material";
		}

		if (inwardItemDto.getIgst() == null) {
			inwardItemDto.setIgst("0");
		}
		InwardMaterial inwardMaterial = modelMapper.map(inwardItemDto, InwardMaterial.class);

		inwardMaterial.setUsername(principal.getName());

		materialService.saveInwardMaterial(inwardMaterial);

		session.setAttribute("message", new Message("All the Item has been successfully added !", "success"));
		return "redirect:/stocks/inward/material";
	}

	@GetMapping("/inward/item/delete")
	public String deleteItem(@RequestParam("id") Long tempBunleId, HttpSession session) {
		materialService.deleteTempBundleItem(tempBunleId);
		session.setAttribute("message", new Message("Item has been successfully removed from the list !", "success"));
		return "redirect:/stocks/inward/material";

	}

	@GetMapping("/inward/items/delete")
	public String deleteItems(HttpSession session) {
		materialService.deleteAllItems();
		session.setAttribute("message",
				new Message("All Item has been successfully removed from the list !", "success"));
		return "redirect:/stocks/inward";

	}
	
	
	@GetMapping("/inward/material/list")
	public String inwardList(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material List | Maintenance Management");
		
		model.addAttribute("materialsLists", materialService.getInwarAlldItemList());
		
		return "/pages/stock_management/inward_materials_list";
	}
	
	@GetMapping("/inward/item/list/delete")
	public String deleteItemsOfList(@RequestParam("id") Long itemId,HttpSession session) {
		materialService.deleteBundleItem(itemId);
		session.setAttribute("message",
				new Message("Item has been successfully removed from the list !", "danger"));
		return "redirect:/stocks/inward/material/list";

	}
	
	@GetMapping("/inward/material/chart")
	public String inwardChart(Model model, Principal principal) {

		model.addAttribute("title", "Inward Material Chart | Maintenance Management");
		
		return "/pages/stock_management/inward_material_chart";
	}

	@GetMapping("/inward/spares")
	public String inwardSpares() {
		return "/pages/stock_management/spares_material";
	}

	@GetMapping("/inward/tools")
	public String inwardTools() {
		return "/pages/stock_management/tools_and_equipment";
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
