package com.ingroinfo.mm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset")
public class AssetController {
	
	@GetMapping("/entry")
	public String assetEntry() {
		return "pages/asset/asset_entry";
	}

}
