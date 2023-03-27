package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ingroinfo.mm.dto.BrandMasterDto;
import com.ingroinfo.mm.dto.CategoryDto;
import com.ingroinfo.mm.dto.DepartmentDto;
import com.ingroinfo.mm.dto.DepartmentIdMasterDto;
import com.ingroinfo.mm.dto.DesignationDto;
import com.ingroinfo.mm.dto.DistributionLocationDto;
import com.ingroinfo.mm.dto.DistributionScheduleDto;
import com.ingroinfo.mm.dto.DivisionSubdivisionDto;
import com.ingroinfo.mm.dto.DmaWardDto;
import com.ingroinfo.mm.dto.EmployeeCategoryDto;
import com.ingroinfo.mm.dto.EmployeePerformanceDto;
import com.ingroinfo.mm.dto.HsnCodeDto;
import com.ingroinfo.mm.dto.IdMasterDto;
import com.ingroinfo.mm.dto.ItemMasterDto;
import com.ingroinfo.mm.dto.LeakageTypeDto;
import com.ingroinfo.mm.dto.MaintanceFrequencyDto;
import com.ingroinfo.mm.dto.MaintenanceActivitiesDto;
import com.ingroinfo.mm.dto.MaintenancePerformanceDto;
import com.ingroinfo.mm.dto.MaintenanceTypeDto;
import com.ingroinfo.mm.dto.MeterManufactureDto;
import com.ingroinfo.mm.dto.MeterTypeDto;
import com.ingroinfo.mm.dto.PipeManufactureDto;
import com.ingroinfo.mm.dto.PressureTypeDto;
import com.ingroinfo.mm.dto.PumpMasterDto;
import com.ingroinfo.mm.dto.SaftyPrecautionsDto;
import com.ingroinfo.mm.dto.ServiceAreaDto;
import com.ingroinfo.mm.dto.ServiceProgressDto;
import com.ingroinfo.mm.dto.ServiceProviderDto;
import com.ingroinfo.mm.dto.SpareEquipmentDto;
import com.ingroinfo.mm.dto.StoreBranchDto;
import com.ingroinfo.mm.dto.SupplierDtlsDto;
import com.ingroinfo.mm.dto.TaskStatusDto;
import com.ingroinfo.mm.dto.TaxMasterDto;
import com.ingroinfo.mm.dto.TeamCodeDto;
import com.ingroinfo.mm.dto.UnitMeasureDto;
import com.ingroinfo.mm.dto.VehicleDtlsDto;
import com.ingroinfo.mm.dto.WaterSourceDto;
import com.ingroinfo.mm.dto.WorkPriorityDto;
import com.ingroinfo.mm.dto.WorkStatusDto;

public interface MasterService {

	// Save Employee Master Category
	EmployeeCategoryDto saveEmployeeMaster(EmployeeCategoryDto employeeCategoryDto);

	// Get List Of Employee Master Category
	List<EmployeeCategoryDto> getAllEmployeeCategory();

	// Delete Employee Master Category By Id
	void deleteEmployeeCategory(Long empCategoryId);

	// save Brand Data
	BrandMasterDto saveBrandMaster(BrandMasterDto brandMasterDto);

	// Get All Brand Data
	List<BrandMasterDto> getAllBrandMasters();

	// Validate Existing Brand Data
	boolean isBrandNameExists(String brandName);

	// Delete Brand Data By brand Id
	void deleteBrandMaster(Long brandMasterId);

	// save Item Category Data
	CategoryDto saveCategory(CategoryDto categoryDto);

	// Validate Category Name
	boolean isCategoryExists(String categoryName);

	// Get All Item Category Data
	List<CategoryDto> findAllCategory();

	// Delete Item Category
	void deleteCategory(Long catid);

	// save Data
	DepartmentIdMasterDto saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto);

	// find All
	List<DepartmentIdMasterDto> findAllDepartmentIdMaster();

	// Delete
	void deleteDepartmenIdtMaster(Long depMasterId);

	// get Data by MasterIdName
	List<DepartmentIdMasterDto> getByMasterIdName(String masterIdName);

	// get Data by MasterId
	DepartmentIdMasterDto getDeptIdMasterByDepMasterId(Long depMasterId);

	// Get Department Id Master Data By Department Id Name And Department Name
	DepartmentIdMasterDto getByMasterIdNameAndDeptName(String masterIdName, String deptName);

	// create
	DepartmentDto saveDepartment(DepartmentDto departmentDto);

	// find All
	List<DepartmentDto> findAllDepartment();

	// Delete
	void deleteDepartmentMaster(Long depMasterId);

	// Get Department List From UBARMS
	List<DepartmentDto> getDepartmentsFromUbarms() throws JsonMappingException, JsonProcessingException;

	// save Data
	DesignationDto saveDesignation(DesignationDto designationDto);

	// find All
	List<DesignationDto> getAllDesignations();

	// Delete
	void deleteDesignations(Long desigId);

	// Get All Designations From UBARMS
	List<DesignationDto> getDesignationsFormUbarms() throws JsonMappingException, JsonProcessingException, IOException;

	// Save Distribution Location Data
	DistributionLocationDto saveDistributionLocation(DistributionLocationDto disLocationDto);

	// validate Distribution Schedule
	boolean isisDistributionLocationExists(String distlocation);

	// Find All Distribution Location
	List<DistributionLocationDto> findAllDistributionLocation();

	// Delete Distribution Location
	void deleteDistributeLocation(Long disLocId);

	// save Distribution Schedule Data
	DistributionScheduleDto saveDisSchedule(DistributionScheduleDto disSchedule);

	// Find Distribution Schedule All Data
	List<DistributionScheduleDto> findAllDisSchedule();

	// validate Distribution Schedule
	boolean isisDistributionScheduleExists(String distSchedule);

	// Delete Distribution Schedule
	void deleteDistrbSchedule(Long disScheduleId);

	// save Division SubDivision Data
	DivisionSubdivisionDto saveDivisionSubdivision(DivisionSubdivisionDto divSubDto);

	// Validate SubDivision Data
	boolean isSubDivisionExists(String subdivision);

	// Validate Service Station
	boolean isServiceStationExists(String serviceStation);

	// Find All Division SubDivision Data
	List<DivisionSubdivisionDto> findAllDivSubdiv();

	// Delete Division SubDivision
	void deleteDivSubDiv(Long divsubId);

	// Get Distinct Divisions
	List<String> getDistinctDivisions();

	// Get SubDivisionList By Division
	List<DivisionSubdivisionDto> getSubDivisionListByDivision(String division);

	// Save DMA Ward
	DmaWardDto saveDmaWard(DmaWardDto dmaWardDto);

	// Validate DMA Number
	boolean isDmaNumberExists(String dmaNumber);

	// Validate Ward Number
	boolean isWardNumberExists(String wardNumber);

	// GetAll DMA Ward Data
	List<DmaWardDto> getAllDmaWard();

	// Delete DMA Ward
	void deleteDmaWard(Long dmaWardId);

	// save Employee Performance Data
	EmployeePerformanceDto saveEmplyeePerformmance(EmployeePerformanceDto empPerformance);

	// Find All Employee Performance Data
	List<EmployeePerformanceDto> getAllEmpPerformance();

	// Validate Employee Performance
	boolean isExistsEmpPerformanceSts(String performStatus);

	// Delete Employee Performance
	void deleteEmpPerformance(Long empPerformId);

	// create Item HSN Code
	HsnCodeDto saveHsnCode(HsnCodeDto hsnCodeDto);

	// findAll Item HSN Data
	List<HsnCodeDto> findAllHsnCode();

	// Validate HSN Code
	boolean isHsnCodeExists(String hsnCode);

	// Validate CategoryName HsnCode Master
	boolean isCategoryExistsInHsnCode(String categoryName);

	// Delete Item HSN
	void deleteHsnCode(Long hsnCodeId);

	// Get HsnCode By CategoryId
	HsnCodeDto getHsnCodeDtoByCategory(Long categoryId);

	// save Data
	IdMasterDto saveIdMaster(IdMasterDto idDto);

	// FindAll
	List<IdMasterDto> findAllIdMaster();

	// get Data By Id
	IdMasterDto getByMasterId(Long masterId);

	// get Data by IdMaster Name
	IdMasterDto getIdMasterByMasterIdName(String masterIdName);

	// save Data
	ItemMasterDto saveItemmaster(ItemMasterDto itemDto);

	// find All Data
	List<ItemMasterDto> getAllItems();

	// Delete
	void deleteMasterItem(Long itemMasterId);

	// Get Item List By Category Id
	List<ItemMasterDto> getItemListByCategoryId(Long categoryId);

	// Get Item List By Item Id
	ItemMasterDto getItemByItemId(Long itemId);

	// Get Item List By Stock Type
	List<ItemMasterDto> getAllItemNames(String stockType);

	// save Leakage Type
	LeakageTypeDto saveLeakageType(LeakageTypeDto leakageTypeDto);

	// Validate Leakage Type
	boolean isLeakageTypeExists(String leakageType);

	// Find All Leakage Type
	List<LeakageTypeDto> getAllLeakageType();

	// Delete Leakage Type
	void deleteLeakageType(Long leakageId);

	// save Data
	MaintanceFrequencyDto saveMaintanceFrequency(MaintanceFrequencyDto maintanFrequency);

	// Find All Data
	List<MaintanceFrequencyDto> getAllMaintanceFrequency();

	// Delete
	void deleteMaintenFrequency(Long maintanFrequId);

	// save Maintenance Activity Data
	MaintenanceActivitiesDto saveMaintenActivity(MaintenanceActivitiesDto maintenActivDto);

	// Validate Maintenance Activity
	boolean isMaintenanceActivityExists(String maintenActivity);

	// find All Maintenance Activity Data
	List<MaintenanceActivitiesDto> findAllMaintnActve();

	// Delete Maintenance Activity
	void deleteMaintenActivity(Long maintenActiveId);

	// save Data
	MaintenancePerformanceDto saveMaintenPerform(MaintenancePerformanceDto maintenPerformDto);

	// find All Data
	List<MaintenancePerformanceDto> getAllMaintenPerform();

	// Delete
	void deleteMaintainsPerformance(Long maintenPerformId);

	// create Data
	MaintenanceTypeDto saveMaintenanceType(MaintenanceTypeDto maintenTypeDto);

	// findAll Data
	List<MaintenanceTypeDto> findAllMaintenanceType();

	// Delete
	void deleteMaintainsType(Long maintenTypeId);

	// save
	MeterManufactureDto saveMeterManufact(MeterManufactureDto meterManufactDto);

	// Get All Data
	List<MeterManufactureDto> findAllMeterManufact();

	// Delete
	void deleteMeterManufacture(Long mtrmanufacId);

	// create
	MeterTypeDto saveMeterType(MeterTypeDto meterTypeDto);

	// Find All data
	List<MeterTypeDto> getAllMeterType();

	// Delete
	void deleteMeterType(Long meterTypeId);

	// save
	PipeManufactureDto savePipeManufacture(PipeManufactureDto pipemanufacDto);

	// find All Data
	List<PipeManufactureDto> findAllPipeManufact();

	// Delete
	void deletePipeManufacture(Long pipemanufId);

	// create
	PressureTypeDto savePressureType(PressureTypeDto pressureDto);

	// Get All Data
	List<PressureTypeDto> getAllPressureType();

	// Delete
	void deletePressureType(Long pressureId);

	// create
	PumpMasterDto savePumpMaster(PumpMasterDto pumpDto);

	// Find All data
	List<PumpMasterDto> getAllPumpMaster();

	// Delete
	void deletePumpMaster(Long pumpMasterId);

	// get PumpData By pump Id
	PumpMasterDto getPumpDataByPumpId(String pumpId);

	// save
	SaftyPrecautionsDto saveSaftyPrecus(SaftyPrecautionsDto saftyPrecusDto);

	// Find All Data
	List<SaftyPrecautionsDto> findAllSaftyPrecus();

	// Delete
	void deleteSaftyPrecason(Long saftyprecId);

	// create
	ServiceAreaDto saveSaerviceArea(ServiceAreaDto serviceAreaDto);

	// Find All Data
	List<ServiceAreaDto> findAllServiceArea();

	// Delete
	void deleteServiceArea(Long sericAreaId);

	// create
	ServiceProgressDto saveServiceProgress(ServiceProgressDto serviceProgrssDto);

	// Find All
	List<ServiceProgressDto> findAllServiceProgress();

	// Delete
	void deleteServiceProgressType(Long servcProgressId);

	// save Data
	ServiceProviderDto saveServiceProvider(ServiceProviderDto serviceProviderDto);

	// find All Data
	List<ServiceProviderDto> getAllServiceProvider();

	// Delete
	void deleteServiceProvider(Long servProvId);

	// create
	SpareEquipmentDto saveSpareEquipment(SpareEquipmentDto spareEquipDto);

	// Show All Data
	List<SpareEquipmentDto> getAllSpareEquipmens();

	// Delete
	void deleteSpareEquipment(Long spareequiId);

	// Save Data
	StoreBranchDto saveStoreBranch(StoreBranchDto storeBranchDto);

	// Find All
	List<StoreBranchDto> findAllStoreBranch();

	// Delete
	void deleteStoreBranch(Long strbraNameId);

	// create
	TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto);

	// get All Data
	List<TaskStatusDto> findAllTaskStatus();

	// Delete
	void deleteTaskStatus(Long taskstsId);

	// save Data
	TaxMasterDto saveTaxMaster(TaxMasterDto taxMasterDto);

	// get All Data
	List<TaxMasterDto> getAllTaxMaster();

	// Delete
	void deleteTaxMaster(Long taxMasterId);

	// save Data
	UnitMeasureDto saveUnitMeasure(UnitMeasureDto unitMeasureDto);

	// find All
	List<UnitMeasureDto> getAllUnitMeasure();

	// Delete
	void deleteUnitOfMeasure(Long unitMeasureId);

	// create
	VehicleDtlsDto saveVDtls(VehicleDtlsDto vehicleDtlsDto);

	// find All
	List<VehicleDtlsDto> findAllVehicleDtls();

	// delete
	void deleteVehicleDetails(Long vehicleDtlsId);

	// Get All Vehicle Types
	List<String> getAllVehicleTypes();

	// Get Vehicle Details By VehicleType
	List<VehicleDtlsDto> getVehiclesByVehicleType(String vehicleType);

	// Get Vehicle Details By VehicleDtlsId
	VehicleDtlsDto getVehicleDtlsByVehicleDtlsId(Long vehicleId);

	// save
	WorkPriorityDto saveWorkPriority(WorkPriorityDto workPriorityDto);

	// find All
	List<WorkPriorityDto> findAllWorkPriority();

	// Delete
	void deleteWorkPriority(Long workPrioId);

	// save
	WorkStatusDto saveWorkStatus(WorkStatusDto workStatusDto);

	// Find All
	List<WorkStatusDto> getAllWorkStatus();

	// Delete
	void deleteWorkStatus(Long workStsId);

	// save
	TeamCodeDto saveTeamCode(TeamCodeDto teamCodeDto);

	// find All
	List<TeamCodeDto> getAllTeamCode();

	// Delete
	void deleteTeamcode(Long teamCodeId);

	// create
	WaterSourceDto saveWaterSource(WaterSourceDto waterSourceDto);

	// find All
	List<WaterSourceDto> findAllWaterSource();

	// Delete
	void deleteWaterCource(Long wateSourceId);

	// create
	SupplierDtlsDto saveSupplierDtls(SupplierDtlsDto supplierDtlsDto);

	// find All
	List<SupplierDtlsDto> getAllSupplierDtls();

	// Delete
	void deleteSupplierDetails(Long suplyDtlsId);

	// Get Distribution Location By Sub-Division
	List<DistributionLocationDto> getDistributLocationBysubDivision(String subDivision);

	// Get Category By Department
	EmployeeCategoryDto getCategoryByDept(String department);
	
	// Get AutoIncrement Id
	String getAutoIncrementId(String IdName);
}
