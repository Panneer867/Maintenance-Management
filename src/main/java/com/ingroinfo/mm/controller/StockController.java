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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.mm.dto.InwardItemDto;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.service.AdminService;
import com.ingroinfo.mm.service.MaterialService;

@Controller
@RequestMapping("/stocks")
public class StockController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private AdminService adminService;

	@Autowired
	private MaterialService materialService;

	@GetMapping("/dashboard")
	public String availableStocks() {
		return "/pages/stock_management/stock_available";
	}

	@GetMapping("/inward/materials")
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

	@PostMapping("/inward/material/add")
	public String itemAdd(@RequestParam("materialImage") MultipartFile file,
			@ModelAttribute("inwardItem") InwardItemDto inwardItemDto, BindingResult bindingResult) throws IOException {

		Optional<String> tokens = Optional.ofNullable(file.getOriginalFilename()).filter(f -> f.contains("."))
				.map(f -> f.substring(file.getOriginalFilename().lastIndexOf(".") + 1));

		InwardItem inwardItem = modelMapper.map(inwardItemDto, InwardItem.class);

		String fileName = inwardItemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + "."
				+ tokens.get();
		String uploadDir = "C:/Material_Management/Inward_Materials/";
		inwardItem.setMaterialImage(fileName);
		adminService.saveFile(uploadDir, fileName, file);

		materialService.saveInwardItem(inwardItem);
		return "redirect:/stocks/inward/materials?MaterialAdded";
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
