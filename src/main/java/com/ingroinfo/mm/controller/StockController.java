package com.ingroinfo.mm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.service.MaterialService;

@Controller
public class StockController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MaterialService materialService;

	@GetMapping("/stocks")
	public String availableStocks() {
		return "/pages/stock_management/stock_available";
	}

	@GetMapping("/stocks/inward/materials")
	public String inwardMaterials(Model model) {

		model.addAttribute("title", "Inward Material Page");
		model.addAttribute("inwardItem", new InwardItemDto());

		List<InwardItem> inwardItemList = materialService.getInwardItemList();
		if (inwardItemList.size() == 0) {
			model.addAttribute("emptyList", "No Materials");
		}
		model.addAttribute("inwardItemLists", inwardItemList);

		return "/pages/stock_management/inward_material";
	}

	@PostMapping("/stocks/inward/material/add")
	public String itemAdd(@RequestParam("materialImage") MultipartFile file,
			@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult) throws IOException {

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardItem inwardItem = modelMapper.map(inwardItemDto, InwardItem.class);

		String fileName = inwardItemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = "C:/Material_Management/Inward_Materials/";
		inwardItem.setMaterialImage(fileName);
		materialService.saveFile(uploadDir, fileName, file);

		materialService.saveInwardItem(inwardItem);
		return "redirect:/inward/materials?MaterialAdded";
	}

	@GetMapping("/stocks/inward/spares")
	public String inwardSpares() {
		return "/pages/stock_management/spares_material";
	}

	@GetMapping("/stocks/inward/tools")
	public String inwardTools() {
		return "/pages/stock_management/tools_and_equipment";
	}

	@GetMapping("/stocks/outward/materials")
	public String outwardMaterials() {
		return "/pages/stock_management/outward_material";
	}

	@GetMapping("/stocks/outward/spares")
	public String outwardSpares() {
		return "/pages/stock_management/outward_spares";
	}

	@GetMapping("/stocks/outward/tools")
	public String outwardTools() {
		return "/pages/stocks/outward_tools_and_equipment";
	}

	@GetMapping("/stocks/material/return")
	public String materialReturn() {
		return "/pages/stock_management/stock_material_return";
	}

	@GetMapping("/stocks/spares/return")
	public String sparesReturn() {
		return "/pages/stock_management/stock_spares_return";
	}

	@GetMapping("/stocks/tools/return")
	public String toolsReturn() {
		return "/pages/stock_management/stock_tools_return";
	}

	@GetMapping("/stocks/material/reject")
	public String materialReject() {
		return "/pages/stock_management/stock_material_reject";
	}

	@GetMapping("/stocks/spares/reject")
	public String sparesReject() {
		return "/pages/stock_management/stock_spares_reject";
	}

	@GetMapping("/stocks/tools/reject")
	public String toolsReject() {
		return "/pages/stock_management/stock_tools_reject";
	}

	@GetMapping("/admin/account/company")
	public String createCompany() {
		return "/pages/admin/create_company";
	}
	
	@GetMapping("/admin/account/company/list")
	public String companyList() {
		return "/pages/admin/company_list";
	}
	
	@GetMapping("/admin/account/branch")
	public String createBranch() {
		return "/pages/admin/create_branch";
	}
	
	@GetMapping("/admin/account/branch/list")
	public String branchList() {
		return "/pages/admin/branch_list";
	}
	
	@GetMapping("/admin/user")
	public String createUser() {
		return "/pages/admin/create_user";
	}
	
	@GetMapping("/admin/user/list")
	public String userList() {
		return "/pages/admin/users_list";
	}
	
	@GetMapping("/admin/user/role")
	public String userRoles() {
		return "/pages/admin/user_roles";
	}
	
	@GetMapping("/admin/user/role/master")
	public String roleMaster() {
		return "/pages/admin/roles_master";
	}
	
	@GetMapping("/admin/user/change-password")
	public String changePassword() {
		return "/pages/admin/change_password";
	}
	
	@GetMapping("/admin/backup")
	public String backup() {
		return "/pages/admin/backup";
	}
		
	@GetMapping("/admin/excel/import-export")
	public String excel() {
		return "/pages/admin/excel_import_export";
	}
	
	@GetMapping("/admin/device/control")
	public String deviceControl() {
		return "/pages/admin/device_control";
	}
}

