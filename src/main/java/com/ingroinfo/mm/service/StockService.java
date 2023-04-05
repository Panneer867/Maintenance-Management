package com.ingroinfo.mm.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dto.InwardDto;
import com.ingroinfo.mm.dto.StockOrderItemsDto;
import com.ingroinfo.mm.entity.ApprovedStocksReturn;
import com.ingroinfo.mm.entity.InwardApprovedMaterials;
import com.ingroinfo.mm.entity.InwardApprovedSpares;
import com.ingroinfo.mm.entity.InwardApprovedTools;
import com.ingroinfo.mm.entity.InwardMaterials;
import com.ingroinfo.mm.entity.InwardSpares;
import com.ingroinfo.mm.entity.InwardTempMaterials;
import com.ingroinfo.mm.entity.InwardTempSpares;
import com.ingroinfo.mm.entity.InwardTempTools;
import com.ingroinfo.mm.entity.InwardTools;
import com.ingroinfo.mm.entity.StocksReturn;
import com.ingroinfo.mm.entity.TempStocksReturn;
import com.ingroinfo.mm.entity.StockOrderItems;
import com.ingroinfo.mm.entity.StockOrders;
import com.ingroinfo.mm.entity.StockOrderItemsRequest;

public interface StockService {

	List<InwardDto> getAllStocks();

	/******** Materials *********/

	void saveInwardTempMaterials(InwardDto inward, MultipartFile file);

	void saveInwardMaterials(InwardDto inward);

	List<InwardTempMaterials> getInwardTempMaterials(String username);

	List<InwardMaterials> getInwardAllMaterialsList();

	void deleteTempMaterial(Long materialId);

	void deleteMaterial(Long materialId);

	void deleteAllMaterials();

	InwardMaterials getInwardMaterial(Long id);

	InwardApprovedMaterials getApprovedInwardMaterialById(Long id);

	List<InwardApprovedMaterials> getApprovedMaterialsLists();

	/******** Spares *********/

	void saveInwardTempSpares(InwardDto inward, MultipartFile file);

	void saveInwardSpares(InwardDto inward);

	List<InwardTempSpares> getInwardTempSpares(String username);

	List<InwardSpares> getInwardAllSparesList();

	void deleteTempSpare(Long spareId);

	void deleteSpare(Long spareId);

	void deleteAllSpares();

	InwardSpares getInwardSpare(Long id);

	InwardApprovedSpares getApprovedInwardSpareById(Long id);

	List<InwardApprovedSpares> getApprovedSparesLists();

	/******** Tools *********/

	void saveInwardTempTools(InwardDto inward, MultipartFile file);

	void saveInwardTools(InwardDto inward);

	List<InwardTempTools> getInwardTempTools(String username);

	List<InwardTools> getInwardAllToolsList();

	void deleteTempTool(Long toolId);

	void deleteTool(Long toolId);

	void deleteAllTools();

	InwardTools getInwardTool(Long id);

	InwardApprovedTools getApprovedInwardToolById(Long id);

	List<InwardApprovedTools> getApprovedToolsLists();

	/******** Work Orders *********/

	List<StockOrderItemsRequest> getStockOrderNos(String username);

	List<StockOrderItemsDto> getStockOrderItems(Long stockOrderNo);

	List<StockOrderItemsDto> checkStockQuantity(Long stockOrderNo);

	void saveStockOrder(StockOrders stockOrders, String username);

	boolean notAvailableItems(Long stockOrderNo);

	void saveRemovedItems(String itemId, Long stockOrderNo, String username);

	boolean getTempStockOrderItems(Long stockOrderNo);

	List<StockOrders> getOutwardStockOrders();

	List<StockOrderItems> getOutwardStockOrderItems(Long stockOrderNo);

	StockOrders getOutwardStockOrder(Long stockOrderNo);

	StockOrderItemsDto getStockorderItemDetails(String itemId, Long stockOrderNo);

	/******** Return Stocks *********/

	void saveReturnItem(TempStocksReturn tempStocksReturn, MultipartFile file);

	boolean checkReturnedItem(TempStocksReturn tempStocksReturn);

	TempStocksReturn getReturnQuantity(TempStocksReturn tempStocksReturn);

	List<TempStocksReturn> getTempStockReturn(String username);

	void deleteTempReturnItem(Long id);

	void deleteAllTempReturnItem();

	void saveReturnItems(TempStocksReturn tempStocksReturn);

	List<StocksReturn> getStockReturnItemList();

	void deleteReturnItem(Long id);

	List<ApprovedStocksReturn> getApprovedStockReturnItemList();

	/******** Approval *********/

	void saveMaterial(InwardDto inwardItemDto);

	void rejectMaterial(Long id, String username);

	void saveSpare(InwardDto inwardItemDto);

	void rejectSpare(Long id, String username);

	void saveTool(InwardDto inwardItemDto);

	void rejectTool(Long id, String username);

	void approveReturnItem(Long id, String username);

	void rejectReturnItem(Long id, String username);
}
