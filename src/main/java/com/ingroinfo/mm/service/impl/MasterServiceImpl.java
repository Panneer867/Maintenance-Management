package com.ingroinfo.mm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.mm.dao.BrandMasterRepository;
import com.ingroinfo.mm.dao.CategoryRepository;
import com.ingroinfo.mm.dao.DepartmentIdMasterRepository;
import com.ingroinfo.mm.dao.DepartmentRepository;
import com.ingroinfo.mm.dao.DesignationRepository;
import com.ingroinfo.mm.dao.DistributionLocationRepository;
import com.ingroinfo.mm.dao.DistributionScheduleRepository;
import com.ingroinfo.mm.dao.DivisionSubdivisionRepository;
import com.ingroinfo.mm.dao.DmaWardRepository;
import com.ingroinfo.mm.dao.EmployeeCategoryRepository;
import com.ingroinfo.mm.dao.EmployeePerformRepostory;
import com.ingroinfo.mm.dao.HsnCodeRepository;
import com.ingroinfo.mm.dao.IdMasterRepository;
import com.ingroinfo.mm.dao.ItemMasterRepository;
import com.ingroinfo.mm.dao.LeakageTypeRepository;
import com.ingroinfo.mm.dao.MaintanceFrequencyRepository;
import com.ingroinfo.mm.dao.MaintenanceActivitiesRepository;
import com.ingroinfo.mm.dao.MaintenancePerformanceRepository;
import com.ingroinfo.mm.dao.MaintenanceTypeRepository;
import com.ingroinfo.mm.dao.MeterManufactureRepository;
import com.ingroinfo.mm.dao.MeterTypeRepository;
import com.ingroinfo.mm.dao.PipeManufactureRepository;
import com.ingroinfo.mm.dao.PressureTypeRepository;
import com.ingroinfo.mm.dao.PumpMasterRepository;
import com.ingroinfo.mm.dao.SaftyPrecautionRepository;
import com.ingroinfo.mm.dao.ServiceAreaRepository;
import com.ingroinfo.mm.dao.ServiceProgressRepository;
import com.ingroinfo.mm.dao.ServiceProviderRepository;
import com.ingroinfo.mm.dao.SpareEquipmentRepository;
import com.ingroinfo.mm.dao.StoreBranchRepository;
import com.ingroinfo.mm.dao.SupplierDtlsRepository;
import com.ingroinfo.mm.dao.TaskStatusRepository;
import com.ingroinfo.mm.dao.TaxMasterRepository;
import com.ingroinfo.mm.dao.TeamCodeRepository;
import com.ingroinfo.mm.dao.UnitMeasureRepository;
import com.ingroinfo.mm.dao.VehicleDtlsRepository;
import com.ingroinfo.mm.dao.WaterSourceRepository;
import com.ingroinfo.mm.dao.WorkPriorityRepository;
import com.ingroinfo.mm.dao.WorkStatusRepository;
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
import com.ingroinfo.mm.entity.BrandMaster;
import com.ingroinfo.mm.entity.Category;
import com.ingroinfo.mm.entity.Department;
import com.ingroinfo.mm.entity.DepartmentIdMaster;
import com.ingroinfo.mm.entity.Designation;
import com.ingroinfo.mm.entity.DistributionLocation;
import com.ingroinfo.mm.entity.DistributionSchedule;
import com.ingroinfo.mm.entity.DivisionSubdivision;
import com.ingroinfo.mm.entity.DmaWard;
import com.ingroinfo.mm.entity.EmployeeCategory;
import com.ingroinfo.mm.entity.EmployeePerformance;
import com.ingroinfo.mm.entity.HsnCode;
import com.ingroinfo.mm.entity.IdMaster;
import com.ingroinfo.mm.entity.ItemMaster;
import com.ingroinfo.mm.entity.LeakageType;
import com.ingroinfo.mm.entity.MaintanceFrequency;
import com.ingroinfo.mm.entity.MaintenanceActivities;
import com.ingroinfo.mm.entity.MaintenancePerformance;
import com.ingroinfo.mm.entity.MaintenanceType;
import com.ingroinfo.mm.entity.MeterManufacture;
import com.ingroinfo.mm.entity.MeterType;
import com.ingroinfo.mm.entity.PipeManufacture;
import com.ingroinfo.mm.entity.PressureType;
import com.ingroinfo.mm.entity.PumpMaster;
import com.ingroinfo.mm.entity.SaftyPrecautions;
import com.ingroinfo.mm.entity.ServiceArea;
import com.ingroinfo.mm.entity.ServiceProgress;
import com.ingroinfo.mm.entity.ServiceProvider;
import com.ingroinfo.mm.entity.SpareEquipment;
import com.ingroinfo.mm.entity.StoreBranch;
import com.ingroinfo.mm.entity.SupplierDtls;
import com.ingroinfo.mm.entity.TaskStatus;
import com.ingroinfo.mm.entity.TaxMaster;
import com.ingroinfo.mm.entity.TeamCode;
import com.ingroinfo.mm.entity.UnitMeasure;
import com.ingroinfo.mm.entity.VehicleDtls;
import com.ingroinfo.mm.entity.WaterSource;
import com.ingroinfo.mm.entity.WorkPriority;
import com.ingroinfo.mm.entity.WorkStatus;
import com.ingroinfo.mm.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Value("${ubarmsApi}")
	String ubarmsUrl;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmployeeCategoryRepository employeeCategoryRepository;
	@Autowired
	private BrandMasterRepository brandMasterRepo;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private DepartmentIdMasterRepository deptIdMasterRepo;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DesignationRepository designationRepo;
	@Autowired
	private DistributionLocationRepository disLocationRepo;
	@Autowired
	private DistributionScheduleRepository disScheduleRepo;
	@Autowired
	private DivisionSubdivisionRepository divsubdivRepo;
	@Autowired
	private DmaWardRepository dmaWardRepository;
	@Autowired
	private EmployeePerformRepostory empPerformRepo;
	@Autowired
	private HsnCodeRepository hsnCodeRepo;
	@Autowired
	private IdMasterRepository idRepository;
	@Autowired
	ItemMasterRepository itemMasterRepo;
	@Autowired
	private LeakageTypeRepository leakageTypeRepository;
	@Autowired
	private MaintanceFrequencyRepository maintanFrequencyRepo;
	@Autowired
	private MaintenanceActivitiesRepository maintenActiveRepo;
	@Autowired
	private MaintenancePerformanceRepository maintenPerformRepo;
	@Autowired
	private MaintenanceTypeRepository maintenTypeRepo;
	@Autowired
	private MeterManufactureRepository meterManufactRepo;
	@Autowired
	private MeterTypeRepository meterTypeRepo;
	@Autowired
	private PipeManufactureRepository pipeManufactRepo;
	@Autowired
	private PressureTypeRepository pressureTypeRepo;
	@Autowired
	private PumpMasterRepository pumpMasterRepo;
	@Autowired
	private SaftyPrecautionRepository saftyPrecusRepo;
	@Autowired
	private ServiceAreaRepository serviceAreaRepo;
	@Autowired
	private ServiceProgressRepository serviceProgressRepo;
	@Autowired
	private ServiceProviderRepository serviceProviderRepo;
	@Autowired
	private SpareEquipmentRepository spareEquipRepo;
	@Autowired
	private StoreBranchRepository storeBranchRepository;
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	@Autowired
	private TaxMasterRepository taxMasterRepository;
	@Autowired
	private UnitMeasureRepository unitMeasureRepo;
	@Autowired
	private VehicleDtlsRepository vehicleDtlsRepo;
	@Autowired
	private WorkPriorityRepository workPriorityRepo;
	@Autowired
	private WorkStatusRepository workStatusRepo;
	@Autowired
	private TeamCodeRepository teamCodeRepo;
	@Autowired
	private WaterSourceRepository waterSourceRepo;
	@Autowired
	SupplierDtlsRepository supplierDtlsRepo;

	@Override
	public EmployeeCategoryDto saveEmployeeMaster(EmployeeCategoryDto employeeCategoryDto) {
		EmployeeCategory convertEmployeeCategory = this.modelMapper.map(employeeCategoryDto, EmployeeCategory.class);
		EmployeeCategory savedEmployeeCategory = this.employeeCategoryRepository.save(convertEmployeeCategory);
		return this.modelMapper.map(savedEmployeeCategory, EmployeeCategoryDto.class);
	}

	@Override
	public List<EmployeeCategoryDto> getAllEmployeeCategory() {
		List<EmployeeCategory> employeeCategories = this.employeeCategoryRepository.findAll();
		return employeeCategories.stream()
				.map((empCategory) -> this.modelMapper.map(empCategory, EmployeeCategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteEmployeeCategory(Long empCategoryId) {
		EmployeeCategory employeeCategory = this.employeeCategoryRepository.findById(empCategoryId).get();
		this.employeeCategoryRepository.delete(employeeCategory);
	}

	@Override
	public BrandMasterDto saveBrandMaster(BrandMasterDto brandMasterDto) {
		BrandMaster convBrandMaster = this.modelMapper.map(brandMasterDto, BrandMaster.class);
		BrandMaster savedBrandMaster = this.brandMasterRepo.save(convBrandMaster);
		return this.modelMapper.map(savedBrandMaster, BrandMasterDto.class);
	}

	@Override
	public List<BrandMasterDto> getAllBrandMasters() {
		List<BrandMaster> brandMasters = this.brandMasterRepo.findAll();
		List<BrandMasterDto> brandMasterDtos = brandMasters.stream()
				.map((brandMastr) -> this.modelMapper.map(brandMastr, BrandMasterDto.class))
				.collect(Collectors.toList());
		return brandMasterDtos;
	}

	@Override
	public void deleteBrandMaster(Long brandMasterId) {
		BrandMaster brandMaster = this.brandMasterRepo.findById(brandMasterId).get();
		this.brandMasterRepo.delete(brandMaster);
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = this.categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> findAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		return categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Long catid) {
		Category category = this.categoryRepository.findById(catid).get();
		this.categoryRepository.delete(category);
	}

	@Override
	public DepartmentIdMasterDto saveDepartmentIdMaster(DepartmentIdMasterDto deptIdMasterDto) {
		DepartmentIdMaster convDepartmentIdMaster = this.modelMapper.map(deptIdMasterDto, DepartmentIdMaster.class);
		DepartmentIdMaster saveDepartmentIdMaster = this.deptIdMasterRepo.save(convDepartmentIdMaster);
		return this.modelMapper.map(saveDepartmentIdMaster, DepartmentIdMasterDto.class);
	}

	@Override
	public List<DepartmentIdMasterDto> findAllDepartmentIdMaster() {
		List<DepartmentIdMaster> departmentIdMasters = this.deptIdMasterRepo.findAll();
		List<DepartmentIdMasterDto> departmentIdMasterDtos = departmentIdMasters.stream()
				.map((departmentId) -> this.modelMapper.map(departmentId, DepartmentIdMasterDto.class))
				.collect(Collectors.toList());
		return departmentIdMasterDtos;
	}

	@Override
	public void deleteDepartmenIdtMaster(Long depMasterId) {
		DepartmentIdMaster departmentIdMaster = this.deptIdMasterRepo.findById(depMasterId).orElseThrow();
		this.deptIdMasterRepo.delete(departmentIdMaster);
	}

	@Override
	public List<DepartmentIdMasterDto> getByMasterIdName(String masterIdName) {
		List<DepartmentIdMaster> deptDepartmentIdMasters = this.deptIdMasterRepo.findByMasterIdName(masterIdName);
		List<DepartmentIdMasterDto> departmentIdMasterDtos = deptDepartmentIdMasters.stream()
				.map((deptMasterId) -> this.modelMapper.map(deptMasterId, DepartmentIdMasterDto.class))
				.collect(Collectors.toList());
		return departmentIdMasterDtos;
	}

	@Override
	public DepartmentIdMasterDto getDeptIdMasterByDepMasterId(Long depMasterId) {
		DepartmentIdMaster departmentIdMaster = this.deptIdMasterRepo.findById(depMasterId).get();
		return this.modelMapper.map(departmentIdMaster, DepartmentIdMasterDto.class);
	}

	@Override
	public DepartmentIdMasterDto getByMasterIdNameAndDeptName(String masterIdName, String deptName) {
		DepartmentIdMaster departmentIdMaster = this.deptIdMasterRepo.getByDeptIdNameAndDeptName(masterIdName,
				deptName);
		return this.modelMapper.map(departmentIdMaster, DepartmentIdMasterDto.class);
	}

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		Department convDepartment = this.modelMapper.map(departmentDto, Department.class);
		Department savedDepartment = this.departmentRepository.save(convDepartment);
		return this.modelMapper.map(savedDepartment, DepartmentDto.class);
	}

	@Override
	public List<DepartmentDto> findAllDepartment() {
		List<Department> departments = this.departmentRepository.findAll();
		List<DepartmentDto> departmentDtos = departments.stream()
				.map((department) -> this.modelMapper.map(department, DepartmentDto.class))
				.collect(Collectors.toList());
		return departmentDtos;
	}

	@Override
	public void deleteDepartmentMaster(Long depMasterId) {
		Department department = this.departmentRepository.findById(depMasterId).get();
		this.departmentRepository.delete(department);
	}

	@Override
	public List<DepartmentDto> getDepartmentsFromUbarms() throws JsonMappingException, JsonProcessingException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getdepartmentsformm");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				json += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection Failed !!" + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("departmentFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<DepartmentDto> departmentDtos = mapper2.readValue(jsonData, new TypeReference<List<DepartmentDto>>() {
		});

		return departmentDtos;
	}

	@Override
	public DesignationDto saveDesignation(DesignationDto designationDto) {
		Designation convDesignation = this.modelMapper.map(designationDto, Designation.class);
		Designation savedDesignation = this.designationRepo.save(convDesignation);
		return this.modelMapper.map(savedDesignation, DesignationDto.class);
	}

	@Override
	public List<DesignationDto> getAllDesignations() {
		List<Designation> designations = this.designationRepo.findAll();
		List<DesignationDto> designationDtos = designations.stream()
				.map((designation) -> this.modelMapper.map(designation, DesignationDto.class))
				.collect(Collectors.toList());
		return designationDtos;
	}

	@Override
	public void deleteDesignations(Long desigId) {
		Designation designation = this.designationRepo.findById(desigId).get();
		this.designationRepo.delete(designation);
	}

	@Override
	public List<DesignationDto> getDesignationsFormUbarms()
			throws JsonMappingException, JsonProcessingException, IOException {
		String json = "";
		try {
			URL url = new URL(ubarmsUrl + "getdesignationformm");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				json += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection Failed !!" + e.getMessage());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		JsonNode data = jsonNode.get("designationFormMM");
		String jsonData = data.toString();

		ObjectMapper mapper1 = new ObjectMapper();
		ObjectMapper mapper2 = mapper1.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		List<DesignationDto> desigList = mapper2.readValue(jsonData, new TypeReference<List<DesignationDto>>() {
		});

		return desigList;
	}

	@Override
	public DistributionLocationDto saveDistributionLocation(DistributionLocationDto disLocationDto) {
		DistributionLocation distributionLocation = this.modelMapper.map(disLocationDto, DistributionLocation.class);
		DistributionLocation saveDistributionLocation = this.disLocationRepo.save(distributionLocation);
		return modelMapper.map(saveDistributionLocation, DistributionLocationDto.class);
	}

	@Override
	public List<DistributionLocationDto> findAllDistributionLocation() {
		List<DistributionLocation> distributionLocations = this.disLocationRepo.findAll();
		List<DistributionLocationDto> distributionLocationDtos = distributionLocations.stream().map(
				(distributionLocation) -> this.modelMapper.map(distributionLocation, DistributionLocationDto.class))
				.collect(Collectors.toList());
		return distributionLocationDtos;
	}

	@Override
	public void deleteDistributeLocation(Long disLocId) {
		DistributionLocation distributionLocation = this.disLocationRepo.findById(disLocId).get();
		this.disLocationRepo.delete(distributionLocation);
	}

	@Override
	public DistributionScheduleDto saveDisSchedule(DistributionScheduleDto disSchedule) {
		DistributionSchedule disSchedules = this.modelMapper.map(disSchedule, DistributionSchedule.class);
		DistributionSchedule savedDisSchedule = this.disScheduleRepo.save(disSchedules);
		return this.modelMapper.map(savedDisSchedule, DistributionScheduleDto.class);
	}

	@Override
	public List<DistributionScheduleDto> findAllDisSchedule() {
		List<DistributionSchedule> disSchedules = this.disScheduleRepo.findAll();
		List<DistributionScheduleDto> disScheduleDtos = disSchedules.stream().map(
				(distributionSchedule) -> this.modelMapper.map(distributionSchedule, DistributionScheduleDto.class))
				.collect(Collectors.toList());
		return disScheduleDtos;
	}

	@Override
	public void deleteDistrbSchedule(Long disScheduleId) {
		DistributionSchedule distributionSchedule = this.disScheduleRepo.findById(disScheduleId).get();
		this.disScheduleRepo.delete(distributionSchedule);
	}

	@Override
	public DivisionSubdivisionDto saveDivisionSubdivision(DivisionSubdivisionDto divSubDto) {
		DivisionSubdivision divSubDivs = this.modelMapper.map(divSubDto, DivisionSubdivision.class);
		DivisionSubdivision savedDivSubDiv = this.divsubdivRepo.save(divSubDivs);
		return modelMapper.map(savedDivSubDiv, DivisionSubdivisionDto.class);
	}

	@Override
	public List<DivisionSubdivisionDto> findAllDivSubdiv() {
		List<DivisionSubdivision> listOfDivSubdiv = this.divsubdivRepo.findAll();
		List<DivisionSubdivisionDto> listOfDivSubdivDto = listOfDivSubdiv.stream()
				.map((divsubdiv) -> this.modelMapper.map(divsubdiv, DivisionSubdivisionDto.class))
				.collect(Collectors.toList());
		return listOfDivSubdivDto;
	}

	@Override
	public void deleteDivSubDiv(Long divsubId) {
		DivisionSubdivision divisionSubdivision = this.divsubdivRepo.findById(divsubId).get();
		this.divsubdivRepo.delete(divisionSubdivision);
	}

	@Override
	public List<String> getDistinctDivisions() {
		List<String> divisions = this.divsubdivRepo.getDistinctDivision();
		return divisions;
	}

	@Override
	public List<DivisionSubdivisionDto> getSubDivisionListByDivision(String division) {
		List<DivisionSubdivision> divisionSubdivisions = this.divsubdivRepo.findByDivision(division);
		List<DivisionSubdivisionDto> dSubdivisionDtos = divisionSubdivisions.stream()
				.map((divSubDiv) -> this.modelMapper.map(divSubDiv, DivisionSubdivisionDto.class))
				.collect(Collectors.toList());
		return dSubdivisionDtos;
	}

	@Override
	public DmaWardDto saveDmaWard(DmaWardDto dmaWardDto) {
		DmaWard convDmaWard = this.modelMapper.map(dmaWardDto, DmaWard.class);
		DmaWard savedDmaWard = this.dmaWardRepository.save(convDmaWard);
		return modelMapper.map(savedDmaWard, DmaWardDto.class);
	}

	@Override
	public List<DmaWardDto> getAllDmaWard() {
		List<DmaWard> dmaWards = this.dmaWardRepository.findAll();
		List<DmaWardDto> dmaWardDtos = dmaWards.stream()
				.map((dmaWard) -> this.modelMapper.map(dmaWard, DmaWardDto.class)).collect(Collectors.toList());
		return dmaWardDtos;
	}

	@Override
	public void deleteDmaWard(Long dmaWardId) {
		DmaWard dmaWard = this.dmaWardRepository.findById(dmaWardId).get();
		this.dmaWardRepository.delete(dmaWard);
	}

	@Override
	public EmployeePerformanceDto saveEmplyeePerformmance(EmployeePerformanceDto empPerformance) {
		EmployeePerformance convEmpPerform = this.modelMapper.map(empPerformance, EmployeePerformance.class);
		EmployeePerformance savedEmpPerform = this.empPerformRepo.save(convEmpPerform);
		return modelMapper.map(savedEmpPerform, EmployeePerformanceDto.class);
	}

	@Override
	public List<EmployeePerformanceDto> getAllEmpPerformance() {
		List<EmployeePerformance> empPerforms = this.empPerformRepo.findAll();
		List<EmployeePerformanceDto> empPerformDtos = empPerforms.stream()
				.map((empPerform) -> this.modelMapper.map(empPerform, EmployeePerformanceDto.class))
				.collect(Collectors.toList());
		return empPerformDtos;
	}

	@Override
	public void deleteEmpPerformance(Long empPerformId) {
		EmployeePerformance employeePerformance = this.empPerformRepo.findById(empPerformId).get();
		this.empPerformRepo.delete(employeePerformance);
	}

	@Override
	public HsnCodeDto saveHsnCode(HsnCodeDto hsnCodeDto) {
		hsnCodeDto.setCategoryName(hsnCodeDto.getCategory().getCategoryName());
		HsnCode conHsnCode = this.modelMapper.map(hsnCodeDto, HsnCode.class);
		HsnCode savedHsnCode = this.hsnCodeRepo.save(conHsnCode);
		return this.modelMapper.map(savedHsnCode, HsnCodeDto.class);
	}

	@Override
	public List<HsnCodeDto> findAllHsnCode() {
		List<HsnCode> hsnCodes = this.hsnCodeRepo.findAll();
		List<HsnCodeDto> hsnCodeDtos = hsnCodes.stream()
				.map((hsnCode) -> this.modelMapper.map(hsnCode, HsnCodeDto.class)).collect(Collectors.toList());
		return hsnCodeDtos;
	}

	@Override
	public void deleteHsnCode(Long hsnCodeId) {
		HsnCode hsnCode = this.hsnCodeRepo.findById(hsnCodeId).get();
		this.hsnCodeRepo.delete(hsnCode);
	}

	@Override
	public HsnCodeDto getHsnCodeDtoByCategory(Long categoryId) {
		HsnCode hsnCode = this.hsnCodeRepo.findHsnCodeByCategory(categoryId);
		return this.modelMapper.map(hsnCode, HsnCodeDto.class);
	}

	@Override
	public IdMasterDto saveIdMaster(IdMasterDto idDto) {
		IdMaster idMaster = this.modelMapper.map(idDto, IdMaster.class);
		IdMaster savedIdMaster = this.idRepository.save(idMaster);
		return modelMapper.map(savedIdMaster, IdMasterDto.class);
	}

	@Override
	public List<IdMasterDto> findAllIdMaster() {
		List<IdMaster> idMasters = this.idRepository.findAll();
		List<IdMasterDto> idMasterDto = idMasters.stream()
				.map((idMaster) -> this.modelMapper.map(idMaster, IdMasterDto.class)).collect(Collectors.toList());
		return idMasterDto;
	}

	@Override
	public IdMasterDto getByMasterId(Long masterId) {
		IdMaster idMaster = this.idRepository.findById(masterId).get();
		return this.modelMapper.map(idMaster, IdMasterDto.class);
	}

	@Override
	public IdMasterDto getIdMasterByMasterIdName(String masterIdName) {
		IdMaster idMaster = this.idRepository.getByMasterIdName(masterIdName);
		return this.modelMapper.map(idMaster, IdMasterDto.class);
	}

	@Override
	public ItemMasterDto saveItemmaster(ItemMasterDto itemDto) {
		itemDto.setCategoryName(itemDto.getCategory().getCategoryName());
		ItemMaster convItemMaster = this.modelMapper.map(itemDto, ItemMaster.class);
		ItemMaster savedItemMaster = this.itemMasterRepo.save(convItemMaster);
		return this.modelMapper.map(savedItemMaster, ItemMasterDto.class);
	}

	@Override
	public List<ItemMasterDto> getAllItems() {
		List<ItemMaster> itemMasters = this.itemMasterRepo.findAll();
		List<ItemMasterDto> itemMasterDtos = itemMasters.stream()
				.map((itemMaster) -> this.modelMapper.map(itemMaster, ItemMasterDto.class))
				.collect(Collectors.toList());
		return itemMasterDtos;
	}

	@Override
	public void deleteMasterItem(Long itemMasterId) {
		ItemMaster itemMaster = this.itemMasterRepo.findById(itemMasterId).get();
		this.itemMasterRepo.delete(itemMaster);
	}

	@Override
	public List<ItemMasterDto> getItemListByCategoryId(Long categoryId) {
		List<ItemMaster> itemMasters = this.itemMasterRepo.findItemsByCategoryId(categoryId);
		return itemMasters.stream().map((items) -> this.modelMapper.map(items, ItemMasterDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ItemMasterDto getItemByItemId(Long itemId) {
		ItemMaster itemMaster = this.itemMasterRepo.findById(itemId).get();
		return this.modelMapper.map(itemMaster, ItemMasterDto.class);
	}

	@Override
	public List<ItemMasterDto> getAllItemNames(String stockType) {
		List<ItemMasterDto> itemMasterDtos = this.itemMasterRepo.findByStockType(stockType).stream()
				.map((itemMaster) -> this.modelMapper.map(itemMaster, ItemMasterDto.class))
				.collect(Collectors.toList());
		return itemMasterDtos;
	}

	@Override
	public LeakageTypeDto saveLeakageType(LeakageTypeDto leakageTypeDto) {
		LeakageType convLeakageType = this.modelMapper.map(leakageTypeDto, LeakageType.class);
		LeakageType savedLeakageType = this.leakageTypeRepository.save(convLeakageType);
		return this.modelMapper.map(savedLeakageType, LeakageTypeDto.class);
	}

	@Override
	public List<LeakageTypeDto> getAllLeakageType() {
		List<LeakageType> leakageTypes = this.leakageTypeRepository.findAll();
		List<LeakageTypeDto> leakageTypeDtos = leakageTypes.stream()
				.map((leakageType) -> this.modelMapper.map(leakageType, LeakageTypeDto.class))
				.collect(Collectors.toList());
		return leakageTypeDtos;
	}

	@Override
	public void deleteLeakageType(Long leakageId) {
		LeakageType leakageType = this.leakageTypeRepository.findById(leakageId).get();
		this.leakageTypeRepository.delete(leakageType);
	}

	@Override
	public MaintanceFrequencyDto saveMaintanceFrequency(MaintanceFrequencyDto maintanFrequency) {
		MaintanceFrequency convMaintanFreq = this.modelMapper.map(maintanFrequency, MaintanceFrequency.class);
		MaintanceFrequency savedMaintanFreq = this.maintanFrequencyRepo.save(convMaintanFreq);
		return this.modelMapper.map(savedMaintanFreq, MaintanceFrequencyDto.class);
	}

	@Override
	public List<MaintanceFrequencyDto> getAllMaintanceFrequency() {
		List<MaintanceFrequency> maintanFreqs = this.maintanFrequencyRepo.findAll();
		List<MaintanceFrequencyDto> maintanFreqDtos = maintanFreqs.stream()
				.map((maintanFreq) -> this.modelMapper.map(maintanFreq, MaintanceFrequencyDto.class))
				.collect(Collectors.toList());
		return maintanFreqDtos;
	}

	@Override
	public void deleteMaintenFrequency(Long maintanFrequId) {
		MaintanceFrequency maintanceFrequency = this.maintanFrequencyRepo.findById(maintanFrequId).get();
		this.maintanFrequencyRepo.delete(maintanceFrequency);
	}

	@Override
	public MaintenanceActivitiesDto saveMaintenActivity(MaintenanceActivitiesDto maintenActivDto) {
		MaintenanceActivities convMaintenActive = this.modelMapper.map(maintenActivDto, MaintenanceActivities.class);
		MaintenanceActivities savedMaintenActive = this.maintenActiveRepo.save(convMaintenActive);
		return modelMapper.map(savedMaintenActive, MaintenanceActivitiesDto.class);
	}

	@Override
	public List<MaintenanceActivitiesDto> findAllMaintnActve() {
		List<MaintenanceActivities> maintenActives = this.maintenActiveRepo.findAll();
		List<MaintenanceActivitiesDto> maintenActiveDtos = maintenActives.stream()
				.map((maintenActive) -> this.modelMapper.map(maintenActive, MaintenanceActivitiesDto.class))
				.collect(Collectors.toList());
		return maintenActiveDtos;
	}

	@Override
	public void deleteMaintenActivity(Long maintenActiveId) {
		MaintenanceActivities maintenanceActivities = this.maintenActiveRepo.findById(maintenActiveId).get();
		this.maintenActiveRepo.delete(maintenanceActivities);
	}

	@Override
	public MaintenancePerformanceDto saveMaintenPerform(MaintenancePerformanceDto maintenPerformDto) {
		MaintenancePerformance convMaintenPerform = this.modelMapper.map(maintenPerformDto,
				MaintenancePerformance.class);
		MaintenancePerformance savedMaintenPerform = this.maintenPerformRepo.save(convMaintenPerform);
		return this.modelMapper.map(savedMaintenPerform, MaintenancePerformanceDto.class);
	}

	@Override
	public List<MaintenancePerformanceDto> getAllMaintenPerform() {
		List<MaintenancePerformance> maintenPerforms = this.maintenPerformRepo.findAll();
		List<MaintenancePerformanceDto> maintenPerformDtos = maintenPerforms.stream()
				.map((maintenPerform) -> this.modelMapper.map(maintenPerform, MaintenancePerformanceDto.class))
				.collect(Collectors.toList());
		return maintenPerformDtos;
	}

	@Override
	public void deleteMaintainsPerformance(Long maintenPerformId) {
		MaintenancePerformance maintenancePerformance = this.maintenPerformRepo.findById(maintenPerformId).get();
		this.maintenPerformRepo.delete(maintenancePerformance);
	}

	@Override
	public MaintenanceTypeDto saveMaintenanceType(MaintenanceTypeDto maintenTypeDto) {
		MaintenanceType convMaintenType = this.modelMapper.map(maintenTypeDto, MaintenanceType.class);
		MaintenanceType savedMaintenType = this.maintenTypeRepo.save(convMaintenType);
		return this.modelMapper.map(savedMaintenType, MaintenanceTypeDto.class);
	}

	@Override
	public List<MaintenanceTypeDto> findAllMaintenanceType() {
		List<MaintenanceType> maintenTypes = this.maintenTypeRepo.findAll();
		List<MaintenanceTypeDto> maintenTypeDtos = maintenTypes.stream()
				.map((maintenType) -> this.modelMapper.map(maintenType, MaintenanceTypeDto.class))
				.collect(Collectors.toList());
		return maintenTypeDtos;
	}

	@Override
	public void deleteMaintainsType(Long maintenTypeId) {
		MaintenanceType maintenanceType = this.maintenTypeRepo.findById(maintenTypeId).get();
		this.maintenTypeRepo.delete(maintenanceType);
	}

	@Override
	public MeterManufactureDto saveMeterManufact(MeterManufactureDto meterManufactDto) {
		MeterManufacture convMeterManufact = this.modelMapper.map(meterManufactDto, MeterManufacture.class);
		MeterManufacture savedMeterManufact = this.meterManufactRepo.save(convMeterManufact);
		return this.modelMapper.map(savedMeterManufact, MeterManufactureDto.class);
	}

	@Override
	public List<MeterManufactureDto> findAllMeterManufact() {
		List<MeterManufacture> meterManufacts = this.meterManufactRepo.findAll();
		List<MeterManufactureDto> meterManufactDtos = meterManufacts.stream()
				.map((meterManufact) -> this.modelMapper.map(meterManufact, MeterManufactureDto.class))
				.collect(Collectors.toList());
		return meterManufactDtos;
	}

	@Override
	public void deleteMeterManufacture(Long mtrmanufacId) {
		MeterManufacture meterManufacture = this.meterManufactRepo.findById(mtrmanufacId).get();
		this.meterManufactRepo.delete(meterManufacture);
	}

	@Override
	public MeterTypeDto saveMeterType(MeterTypeDto meterTypeDto) {
		MeterType convMeterType = this.modelMapper.map(meterTypeDto, MeterType.class);
		MeterType savedMeterType = this.meterTypeRepo.save(convMeterType);
		return this.modelMapper.map(savedMeterType, MeterTypeDto.class);
	}

	@Override
	public List<MeterTypeDto> getAllMeterType() {
		List<MeterType> meterTypes = this.meterTypeRepo.findAll();
		List<MeterTypeDto> meterTypeDtos = meterTypes.stream()
				.map((meterType) -> this.modelMapper.map(meterType, MeterTypeDto.class)).collect(Collectors.toList());
		return meterTypeDtos;
	}

	@Override
	public void deleteMeterType(Long meterTypeId) {
		MeterType meterType = this.meterTypeRepo.findById(meterTypeId).get();
		this.meterTypeRepo.delete(meterType);
	}

	@Override
	public PipeManufactureDto savePipeManufacture(PipeManufactureDto pipemanufacDto) {
		PipeManufacture convPipeManufact = this.modelMapper.map(pipemanufacDto, PipeManufacture.class);
		PipeManufacture savedPipeManufact = this.pipeManufactRepo.save(convPipeManufact);
		return this.modelMapper.map(savedPipeManufact, PipeManufactureDto.class);
	}

	@Override
	public List<PipeManufactureDto> findAllPipeManufact() {
		List<PipeManufacture> pipeManufacts = this.pipeManufactRepo.findAll();
		List<PipeManufactureDto> pipeManufactDtos = pipeManufacts.stream()
				.map((pipeManufact) -> this.modelMapper.map(pipeManufact, PipeManufactureDto.class))
				.collect(Collectors.toList());
		return pipeManufactDtos;
	}

	@Override
	public void deletePipeManufacture(Long pipemanufId) {
		PipeManufacture pipeManufacture = this.pipeManufactRepo.findById(pipemanufId).get();
		this.pipeManufactRepo.delete(pipeManufacture);
	}

	@Override
	public PressureTypeDto savePressureType(PressureTypeDto pressureDto) {
		PressureType convPressure = this.modelMapper.map(pressureDto, PressureType.class);
		PressureType savedPressure = this.pressureTypeRepo.save(convPressure);
		return this.modelMapper.map(savedPressure, PressureTypeDto.class);
	}

	@Override
	public List<PressureTypeDto> getAllPressureType() {
		List<PressureType> pressureTypes = this.pressureTypeRepo.findAll();
		List<PressureTypeDto> pressureTypeDtos = pressureTypes.stream()
				.map((pressureType) -> this.modelMapper.map(pressureType, PressureTypeDto.class))
				.collect(Collectors.toList());
		return pressureTypeDtos;
	}

	@Override
	public void deletePressureType(Long pressureId) {
		PressureType pressureType = this.pressureTypeRepo.findById(pressureId).get();
		this.pressureTypeRepo.delete(pressureType);
	}

	@Override
	public PumpMasterDto savePumpMaster(PumpMasterDto pumpDto) {
		PumpMaster convPumpMaster = this.modelMapper.map(pumpDto, PumpMaster.class);
		PumpMaster savedPumps = this.pumpMasterRepo.save(convPumpMaster);
		return this.modelMapper.map(savedPumps, PumpMasterDto.class);
	}

	@Override
	public List<PumpMasterDto> getAllPumpMaster() {
		List<PumpMaster> pumpMasters = this.pumpMasterRepo.findAll();
		List<PumpMasterDto> pumpMasterDtos = pumpMasters.stream()
				.map((pumpMaster) -> this.modelMapper.map(pumpMaster, PumpMasterDto.class))
				.collect(Collectors.toList());
		return pumpMasterDtos;
	}

	@Override
	public void deletePumpMaster(Long pumpMasterId) {
		PumpMaster pumpMaster = this.pumpMasterRepo.findById(pumpMasterId).get();
		this.pumpMasterRepo.delete(pumpMaster);
	}

	@Override
	public PumpMasterDto getPumpDataByPumpId(String pumpId) {
		PumpMaster pumpMaster = this.pumpMasterRepo.getPumpMasterByPumpId(pumpId);
		return this.modelMapper.map(pumpMaster, PumpMasterDto.class);
	}

	@Override
	public SaftyPrecautionsDto saveSaftyPrecus(SaftyPrecautionsDto saftyPrecusDto) {
		SaftyPrecautions convPrecaution = this.modelMapper.map(saftyPrecusDto, SaftyPrecautions.class);
		SaftyPrecautions savedPrecaution = this.saftyPrecusRepo.save(convPrecaution);
		return modelMapper.map(savedPrecaution, SaftyPrecautionsDto.class);
	}

	@Override
	public List<SaftyPrecautionsDto> findAllSaftyPrecus() {
		List<SaftyPrecautions> saftyPrecautions = this.saftyPrecusRepo.findAll();
		List<SaftyPrecautionsDto> precautionDtos = saftyPrecautions.stream()
				.map((precaution) -> this.modelMapper.map(precaution, SaftyPrecautionsDto.class))
				.collect(Collectors.toList());
		return precautionDtos;
	}

	@Override
	public void deleteSaftyPrecason(Long saftyprecId) {
		SaftyPrecautions saftyPrecautions = this.saftyPrecusRepo.findById(saftyprecId).get();
		this.saftyPrecusRepo.delete(saftyPrecautions);
	}

	@Override
	public ServiceAreaDto saveSaerviceArea(ServiceAreaDto serviceAreaDto) {
		ServiceArea convServiceArea = this.modelMapper.map(serviceAreaDto, ServiceArea.class);
		ServiceArea savedServiceArea = this.serviceAreaRepo.save(convServiceArea);
		return this.modelMapper.map(savedServiceArea, ServiceAreaDto.class);
	}

	@Override
	public List<ServiceAreaDto> findAllServiceArea() {
		List<ServiceArea> serviceAreas = this.serviceAreaRepo.findAll();
		List<ServiceAreaDto> serviceAreaDtos = serviceAreas.stream()
				.map((serviceArea) -> this.modelMapper.map(serviceArea, ServiceAreaDto.class))
				.collect(Collectors.toList());
		return serviceAreaDtos;
	}

	@Override
	public void deleteServiceArea(Long sericAreaId) {
		ServiceArea serviceArea = this.serviceAreaRepo.findById(sericAreaId).get();
		this.serviceAreaRepo.delete(serviceArea);
	}

	@Override
	public ServiceProgressDto saveServiceProgress(ServiceProgressDto serviceProgrssDto) {
		ServiceProgress convServiceProgrec = this.modelMapper.map(serviceProgrssDto, ServiceProgress.class);
		ServiceProgress savedServiceProgrec = this.serviceProgressRepo.save(convServiceProgrec);
		return this.modelMapper.map(savedServiceProgrec, ServiceProgressDto.class);
	}

	@Override
	public List<ServiceProgressDto> findAllServiceProgress() {
		List<ServiceProgress> serviceProgresses = this.serviceProgressRepo.findAll();
		List<ServiceProgressDto> serviceProgressDtos = serviceProgresses.stream()
				.map((serviceProgress) -> this.modelMapper.map(serviceProgress, ServiceProgressDto.class))
				.collect(Collectors.toList());
		return serviceProgressDtos;
	}

	@Override
	public void deleteServiceProgressType(Long servcProgressId) {
		ServiceProgress serviceProgress = this.serviceProgressRepo.findById(servcProgressId).get();
		this.serviceProgressRepo.delete(serviceProgress);
	}

	@Override
	public ServiceProviderDto saveServiceProvider(ServiceProviderDto serviceProviderDto) {
		ServiceProvider convserviceProvider = this.modelMapper.map(serviceProviderDto, ServiceProvider.class);
		ServiceProvider savedServiceProvider = this.serviceProviderRepo.save(convserviceProvider);
		return this.modelMapper.map(savedServiceProvider, ServiceProviderDto.class);
	}

	@Override
	public List<ServiceProviderDto> getAllServiceProvider() {
		List<ServiceProvider> serviceProviders = this.serviceProviderRepo.findAll();
		List<ServiceProviderDto> serviceProviderDtos = serviceProviders.stream()
				.map((serviceProvider) -> this.modelMapper.map(serviceProvider, ServiceProviderDto.class))
				.collect(Collectors.toList());
		return serviceProviderDtos;
	}

	@Override
	public void deleteServiceProvider(Long servProvId) {
		ServiceProvider serviceProvider = this.serviceProviderRepo.findById(servProvId).get();
		this.serviceProviderRepo.delete(serviceProvider);
	}

	@Override
	public SpareEquipmentDto saveSpareEquipment(SpareEquipmentDto spareEquipDto) {
		SpareEquipment convEquipment = this.modelMapper.map(spareEquipDto, SpareEquipment.class);
		SpareEquipment savedEquipment = this.spareEquipRepo.save(convEquipment);
		return this.modelMapper.map(savedEquipment, SpareEquipmentDto.class);
	}

	@Override
	public List<SpareEquipmentDto> getAllSpareEquipmens() {
		List<SpareEquipment> spareEquipments = this.spareEquipRepo.findAll();
		List<SpareEquipmentDto> spareEquipmentDtos = spareEquipments.stream()
				.map((spareEquipment) -> this.modelMapper.map(spareEquipment, SpareEquipmentDto.class))
				.collect(Collectors.toList());
		return spareEquipmentDtos;
	}

	@Override
	public void deleteSpareEquipment(Long spareequiId) {
		SpareEquipment spareEquipment = this.spareEquipRepo.findById(spareequiId).get();
		this.spareEquipRepo.delete(spareEquipment);
	}

	@Override
	public StoreBranchDto saveStoreBranch(StoreBranchDto storeBranchDto) {
		StoreBranch convBranch = this.modelMapper.map(storeBranchDto, StoreBranch.class);
		StoreBranch savedBranch = this.storeBranchRepository.save(convBranch);
		return this.modelMapper.map(savedBranch, StoreBranchDto.class);
	}

	@Override
	public List<StoreBranchDto> findAllStoreBranch() {
		List<StoreBranch> storeBranchs = this.storeBranchRepository.findAll();
		List<StoreBranchDto> storeBranchDtos = storeBranchs.stream()
				.map((storeBranch) -> this.modelMapper.map(storeBranch, StoreBranchDto.class))
				.collect(Collectors.toList());
		return storeBranchDtos;
	}

	@Override
	public void deleteStoreBranch(Long strbraNameId) {
		StoreBranch storeBranch = this.storeBranchRepository.findById(strbraNameId).get();
		this.storeBranchRepository.delete(storeBranch);
	}

	@Override
	public TaskStatusDto saveTaskStatus(TaskStatusDto taskStatusDto) {
		TaskStatus convTaskStatus = this.modelMapper.map(taskStatusDto, TaskStatus.class);
		TaskStatus savedTaskStatus = this.taskStatusRepository.save(convTaskStatus);
		return this.modelMapper.map(savedTaskStatus, TaskStatusDto.class);
	}

	@Override
	public List<TaskStatusDto> findAllTaskStatus() {
		List<TaskStatus> taskStatuss = this.taskStatusRepository.findAll();
		List<TaskStatusDto> taskStatusDtos = taskStatuss.stream()
				.map((taskSts) -> this.modelMapper.map(taskSts, TaskStatusDto.class)).collect(Collectors.toList());
		return taskStatusDtos;
	}

	@Override
	public void deleteTaskStatus(Long taskstsId) {
		TaskStatus taskStatus = this.taskStatusRepository.findById(taskstsId).get();
		this.taskStatusRepository.delete(taskStatus);
	}

	@Override
	public TaxMasterDto saveTaxMaster(TaxMasterDto taxMasterDto) {
		TaxMaster convTaxMaster = this.modelMapper.map(taxMasterDto, TaxMaster.class);
		this.taxMasterRepository.save(convTaxMaster);
		return this.modelMapper.map(convTaxMaster, TaxMasterDto.class);
	}

	@Override
	public List<TaxMasterDto> getAllTaxMaster() {
		List<TaxMaster> taxMasters = this.taxMasterRepository.findAll();
		List<TaxMasterDto> taxMasterDtos = taxMasters.stream()
				.map((taxmaster) -> this.modelMapper.map(taxmaster, TaxMasterDto.class)).collect(Collectors.toList());
		return taxMasterDtos;
	}

	@Override
	public void deleteTaxMaster(Long taxMasterId) {
		TaxMaster taxMaster = this.taxMasterRepository.findById(taxMasterId).get();
		this.taxMasterRepository.delete(taxMaster);
	}

	@Override
	public UnitMeasureDto saveUnitMeasure(UnitMeasureDto unitMeasureDto) {
		UnitMeasure convUnitMeasure = this.modelMapper.map(unitMeasureDto, UnitMeasure.class);
		UnitMeasure savedUnitMeasure = this.unitMeasureRepo.save(convUnitMeasure);
		return this.modelMapper.map(savedUnitMeasure, UnitMeasureDto.class);
	}

	@Override
	public List<UnitMeasureDto> getAllUnitMeasure() {
		List<UnitMeasure> unitMeasures = this.unitMeasureRepo.findAll();
		List<UnitMeasureDto> unitMeasureDtos = unitMeasures.stream()
				.map((unitMeasure) -> this.modelMapper.map(unitMeasure, UnitMeasureDto.class))
				.collect(Collectors.toList());
		return unitMeasureDtos;
	}

	@Override
	public void deleteUnitOfMeasure(Long unitMeasureId) {
		UnitMeasure unitMeasure = this.unitMeasureRepo.findById(unitMeasureId).get();
		this.unitMeasureRepo.delete(unitMeasure);
	}

	@Override
	public VehicleDtlsDto saveVDtls(VehicleDtlsDto vehicleDtlsDto) {
		VehicleDtls conVehicleDtls = this.modelMapper.map(vehicleDtlsDto, VehicleDtls.class);
		VehicleDtls savedVehicleDtls = this.vehicleDtlsRepo.save(conVehicleDtls);
		return this.modelMapper.map(savedVehicleDtls, VehicleDtlsDto.class);
	}

	@Override
	public List<VehicleDtlsDto> findAllVehicleDtls() {
		List<VehicleDtls> vehicleDtlss = this.vehicleDtlsRepo.findAll();
		List<VehicleDtlsDto> vehicleDtlsDtos = vehicleDtlss.stream()
				.map((vehicle) -> this.modelMapper.map(vehicle, VehicleDtlsDto.class)).collect(Collectors.toList());
		return vehicleDtlsDtos;
	}

	@Override
	public void deleteVehicleDetails(Long vehicleDtlsId) {
		VehicleDtls vehicleDtls = this.vehicleDtlsRepo.findById(vehicleDtlsId).get();
		this.vehicleDtlsRepo.delete(vehicleDtls);
	}

	@Override
	public List<String> getAllVehicleTypes() {
		List<String> vehicleTypes = this.vehicleDtlsRepo.getAllVehicleTypes();
		return vehicleTypes;
	}

	@Override
	public List<VehicleDtlsDto> getVehiclesByVehicleType(String vehicleType) {
		List<VehicleDtls> vehicleDtls = this.vehicleDtlsRepo.findByVehicleType(vehicleType);
		return vehicleDtls.stream().map((vehicle) -> this.modelMapper.map(vehicle, VehicleDtlsDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public VehicleDtlsDto getVehicleDtlsByVehicleDtlsId(Long vehicleId) {
		VehicleDtls vehicleDtls = this.vehicleDtlsRepo.findById(vehicleId).get();
		return this.modelMapper.map(vehicleDtls, VehicleDtlsDto.class);
	}

	@Override
	public WorkPriorityDto saveWorkPriority(WorkPriorityDto workPriorityDto) {
		WorkPriority convWorkPriority = this.modelMapper.map(workPriorityDto, WorkPriority.class);
		WorkPriority savedWorkPriority = this.workPriorityRepo.save(convWorkPriority);
		return this.modelMapper.map(savedWorkPriority, WorkPriorityDto.class);
	}

	@Override
	public List<WorkPriorityDto> findAllWorkPriority() {
		List<WorkPriority> workPriorities = this.workPriorityRepo.findAll();
		List<WorkPriorityDto> workPriorityDtos = workPriorities.stream()
				.map((workPriority) -> this.modelMapper.map(workPriority, WorkPriorityDto.class))
				.collect(Collectors.toList());
		return workPriorityDtos;
	}

	@Override
	public void deleteWorkPriority(Long workPrioId) {
		WorkPriority workPriority = this.workPriorityRepo.findById(workPrioId).get();
		this.workPriorityRepo.delete(workPriority);
	}

	@Override
	public WorkStatusDto saveWorkStatus(WorkStatusDto workStatusDto) {
		WorkStatus convWorkStatus = this.modelMapper.map(workStatusDto, WorkStatus.class);
		WorkStatus savedWorkStatus = this.workStatusRepo.save(convWorkStatus);
		return this.modelMapper.map(savedWorkStatus, WorkStatusDto.class);
	}

	@Override
	public List<WorkStatusDto> getAllWorkStatus() {
		List<WorkStatus> workStatuss = this.workStatusRepo.findAll();
		List<WorkStatusDto> workStatusDtos = workStatuss.stream()
				.map((workStatus) -> this.modelMapper.map(workStatus, WorkStatusDto.class))
				.collect(Collectors.toList());
		return workStatusDtos;
	}

	@Override
	public void deleteWorkStatus(Long workStsId) {
		WorkStatus workStatus = this.workStatusRepo.findById(workStsId).get();
		this.workStatusRepo.delete(workStatus);
	}

	@Override
	public TeamCodeDto saveTeamCode(TeamCodeDto teamCodeDto) {
		TeamCode convTeamCode = this.modelMapper.map(teamCodeDto, TeamCode.class);
		TeamCode savedTeamCode = this.teamCodeRepo.save(convTeamCode);
		return this.modelMapper.map(savedTeamCode, TeamCodeDto.class);
	}

	@Override
	public List<TeamCodeDto> getAllTeamCode() {
		List<TeamCode> teamCodes = this.teamCodeRepo.findAll();
		List<TeamCodeDto> teamCodeDtos = teamCodes.stream()
				.map((teamCode) -> this.modelMapper.map(teamCode, TeamCodeDto.class)).collect(Collectors.toList());
		return teamCodeDtos;
	}

	@Override
	public void deleteTeamcode(Long teamCodeId) {
		TeamCode teamCodem = this.teamCodeRepo.findById(teamCodeId).get();
		this.teamCodeRepo.delete(teamCodem);
	}

	@Override
	public WaterSourceDto saveWaterSource(WaterSourceDto waterSourceDto) {
		WaterSource convWaterSource = this.modelMapper.map(waterSourceDto, WaterSource.class);
		WaterSource savedWaterSource = this.waterSourceRepo.save(convWaterSource);
		return this.modelMapper.map(savedWaterSource, WaterSourceDto.class);
	}

	@Override
	public List<WaterSourceDto> findAllWaterSource() {
		List<WaterSource> waterSources = this.waterSourceRepo.findAll();
		List<WaterSourceDto> waterSourceDtos = waterSources.stream()
				.map((waterSource) -> this.modelMapper.map(waterSource, WaterSourceDto.class))
				.collect(Collectors.toList());
		return waterSourceDtos;
	}

	@Override
	public void deleteWaterCource(Long wateSourceId) {
		WaterSource waterSource = this.waterSourceRepo.findById(wateSourceId).get();
		this.waterSourceRepo.delete(waterSource);
	}

	@Override
	public SupplierDtlsDto saveSupplierDtls(SupplierDtlsDto supplierDtlsDto) {
		SupplierDtls convSupplierDtls = this.modelMapper.map(supplierDtlsDto, SupplierDtls.class);
		SupplierDtls savedSupplierDtls = this.supplierDtlsRepo.save(convSupplierDtls);
		return this.modelMapper.map(savedSupplierDtls, SupplierDtlsDto.class);
	}

	@Override
	public List<SupplierDtlsDto> getAllSupplierDtls() {
		List<SupplierDtls> supplierDtlss = this.supplierDtlsRepo.findAll();
		List<SupplierDtlsDto> supplierDtlsDtos = supplierDtlss.stream()
				.map((supplier) -> this.modelMapper.map(supplier, SupplierDtlsDto.class)).collect(Collectors.toList());
		return supplierDtlsDtos;
	}

	@Override
	public void deleteSupplierDetails(Long suplyDtlsId) {
		SupplierDtls supplierDtls = this.supplierDtlsRepo.findById(suplyDtlsId).get();
		this.supplierDtlsRepo.delete(supplierDtls);
	}

	@Override
	public boolean isBrandNameExists(String brandName) {
		return this.brandMasterRepo.existsByBrandName(brandName);
	}

	@Override
	public boolean isisDistributionScheduleExists(String distSchedule) {
		return this.disScheduleRepo.existsByDistSchedule(distSchedule);
	}

	@Override
	public boolean isSubDivisionExists(String subdivision) {
		return this.divsubdivRepo.existsBySubdivision(subdivision);
	}

	@Override
	public boolean isServiceStationExists(String serviceStation) {
		return this.divsubdivRepo.existsByServiceStation(serviceStation);
	}

	@Override
	public boolean isisDistributionLocationExists(String distlocation) {
		return this.disLocationRepo.existsByDistlocation(distlocation);
	}

	@Override
	public List<DistributionLocationDto> getDistributLocationBysubDivision(String subDivision) {
		List<DistributionLocation> distributionLocations = this.disLocationRepo.getBySubDivision(subDivision);
		return distributionLocations.stream()
				.map((distLocal) -> this.modelMapper.map(distLocal, DistributionLocationDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isDmaNumberExists(String dmaNumber) {		
		return this.dmaWardRepository.existsByDmaNumber(dmaNumber);
	}

	@Override
	public boolean isWardNumberExists(String wardNumber) {
		return this.dmaWardRepository.existsByWardNumber(wardNumber);
	}

	@Override
	public boolean isExistsEmpPerformanceSts(String performStatus) {
		return this.empPerformRepo.existsByPerformStatus(performStatus);
	}

	@Override
	public boolean isCategoryExists(String categoryName) {
		return this.categoryRepository.existsByCategoryName(categoryName);
	}

	@Override
	public boolean isHsnCodeExists(String hsnCode) {
		return this.hsnCodeRepo.existsByHsnCode(hsnCode);
	}

	@Override
	public boolean isCategoryExistsInHsnCode(String categoryName) {
		return this.hsnCodeRepo.existsByCategoryName(categoryName);
	}

	@Override
	public boolean isLeakageTypeExists(String leakageType) {
		return this.leakageTypeRepository.existsByLeakageType(leakageType);
	}

	@Override
	public boolean isMaintenanceActivityExists(String maintenActivity) {
		return this.maintenActiveRepo.existsByMaintenActivity(maintenActivity);
	}

	@Override
	public EmployeeCategoryDto getCategoryByDept(String department) {
		EmployeeCategory  employeeCategory = this.employeeCategoryRepository.findByDepartment(department);
		return this.modelMapper.map(employeeCategory, EmployeeCategoryDto.class);
	}


}
