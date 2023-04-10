package com.ingroinfo.mm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ingroinfo.mm.dao.StockOrderItemsRepository;
import com.ingroinfo.mm.dao.TempAddedReturnItemsRepository;
import com.ingroinfo.mm.entity.StockOrderItems;
import com.ingroinfo.mm.entity.TempAddedReturnItems;

@Controller
@RequestMapping("/help")
public class CommonController {

	@Autowired
	private StockOrderItemsRepository stockOrderItemsRepo;
	@Autowired
	private TempAddedReturnItemsRepository tempAddedReturnItemsRepo;

	// Get Item Details From Approved Stock Order
		@GetMapping("/get/stockorder/items/{itemId}/{complNo}")
		@ResponseBody
		public ResponseEntity<StockOrderItems> getStockOrderItemsByItemId(@PathVariable String itemId,@PathVariable String complNo) {
			StockOrderItems stockOrderItems = this.stockOrderItemsRepo.getByItemIdAndComplNo(itemId,complNo);
			return new ResponseEntity<StockOrderItems>(stockOrderItems, HttpStatus.OK);
		}

	// Handler For Adding Return Items In List
	@PostMapping("/save/add/returnItems")
	@ResponseBody
	public ResponseEntity<TempAddedReturnItems> addTempReturnItems(
			@ModelAttribute("returnItems") TempAddedReturnItems tempAddedReturnItems) {
		TempAddedReturnItems savedReturnItems = this.tempAddedReturnItemsRepo.save(tempAddedReturnItems);
		return new ResponseEntity<TempAddedReturnItems>(savedReturnItems, HttpStatus.OK);
	}

	// Get Added Return Items Data in List
	@ResponseBody
	@GetMapping("/get/added/return/items/{indentNo}/{complNo}")
	public ResponseEntity<List<TempAddedReturnItems>> getListOfReturnAddedItems(@PathVariable String indentNo,
			@PathVariable String complNo) {
		List<TempAddedReturnItems> tempAddedReturnItems = this.tempAddedReturnItemsRepo
				.getByIndentNoAndComplNo(indentNo, complNo);
		return new ResponseEntity<List<TempAddedReturnItems>>(tempAddedReturnItems, HttpStatus.OK);
	}

	// Delete Added Return Items Data From List
	@DeleteMapping("/delete/returnItem/{returnId}")
	public ResponseEntity<String> deleteReturnItemsFromList(@PathVariable Long returnId) {
		this.tempAddedReturnItemsRepo.deleteById(returnId);
		return new ResponseEntity<>("Are You Sure You Want To Remove The Item !!", HttpStatus.OK);

	}
}
