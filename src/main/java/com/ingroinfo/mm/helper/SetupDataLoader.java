package com.ingroinfo.mm.helper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.ingroinfo.mm.dao.BankRepository;
import com.ingroinfo.mm.dao.CityRepository;
import com.ingroinfo.mm.dao.PrivilegeRepository;
import com.ingroinfo.mm.dao.RoleRepository;
import com.ingroinfo.mm.dao.StateRepository;
import com.ingroinfo.mm.dao.UserRepository;
import com.ingroinfo.mm.entity.Bank;
import com.ingroinfo.mm.entity.City;
import com.ingroinfo.mm.entity.Role;
import com.ingroinfo.mm.entity.State;
import com.ingroinfo.mm.entity.User;
import com.ingroinfo.mm.entity.Privilege;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		/* All Privileges */

		/* Admin Pages */

		Privilege adminpage = createPrivilegeIfNotFound(100, "ADMIN_HOME");
		Privilege companyManagement = createPrivilegeIfNotFound(101, "COMPANY_MANAGEMENT");
		Privilege createCompany = createPrivilegeIfNotFound(102, "CREATE_COMPANY");
		Privilege editCompany = createPrivilegeIfNotFound(103, "EDIT_COMPANY");
		Privilege viewCompany = createPrivilegeIfNotFound(104, "VIEW_COMPANY");
		Privilege deleteCompany = createPrivilegeIfNotFound(105, "DELETE_COMPANY");
		Privilege branchManagement = createPrivilegeIfNotFound(106, "BRANCH_MANAGEMENT");
		Privilege createBranch = createPrivilegeIfNotFound(107, "CREATE_BRANCH");
		Privilege editBranch = createPrivilegeIfNotFound(108, "EDIT_BRANCH");
		Privilege viewBranch = createPrivilegeIfNotFound(109, "VIEW_BRANCH");
		Privilege deleteBranch = createPrivilegeIfNotFound(110, "DELETE_BRANCH");
		Privilege userManagement = createPrivilegeIfNotFound(111, "USER_MANAGEMENT");
		Privilege createUser = createPrivilegeIfNotFound(112, "CREATE_USER");
		Privilege editUser = createPrivilegeIfNotFound(113, "EDIT_USER");
		Privilege deleteUser = createPrivilegeIfNotFound(114, "DELETE_USER");
		Privilege roleManagement = createPrivilegeIfNotFound(115, "ROLE_MANAGEMENT");
		Privilege createRole = createPrivilegeIfNotFound(116, "CREATE_ROLE");
		Privilege editRole = createPrivilegeIfNotFound(117, "EDIT_ROLE");
		Privilege deleteRole = createPrivilegeIfNotFound(118, "DELETE_ROLE");
		Privilege userRoles = createPrivilegeIfNotFound(119, "USER_ROLES");
		Privilege updateUserRoles = createPrivilegeIfNotFound(120, "EDIT_USER_ROLES");
		Privilege importExport = createPrivilegeIfNotFound(121, "IMPORT_EXPORT");
		Privilege deviceControl = createPrivilegeIfNotFound(122, "DEVICE_CONTROL");
		Privilege serverBackup = createPrivilegeIfNotFound(123, "SERVER_BACKUP");

		/* Task Management Pages */

		Privilege taskJe = createPrivilegeIfNotFound(200, "TASK_JE");
		Privilege taskAee = createPrivilegeIfNotFound(201, "TASK_AEE");
		Privilege taskEe = createPrivilegeIfNotFound(202, "TASK_EE");
		Privilege taskCommissioner = createPrivilegeIfNotFound(203, "TASK_COMMISSIONER");
		Privilege taskWorkcomplete = createPrivilegeIfNotFound(204, "TASK_WORKCOMPLETE");		
		Privilege taskComplainthistory = createPrivilegeIfNotFound(206, "TASK_COMPLAINTHISTORY");

		/* Asset Management User Roles */

		Privilege assestManagement = createPrivilegeIfNotFound(300, "ASSET_MANAGEMENT");

		/* Stock Management User Roles */

		Privilege stockAvailable = createPrivilegeIfNotFound(400, "STOCKS_AVAILABLE");
		Privilege inwardMatierals = createPrivilegeIfNotFound(401, "INWARD_MATERIALS");
		Privilege inwardSpares = createPrivilegeIfNotFound(402, "INWARD_SPARES");
		Privilege inwardTools = createPrivilegeIfNotFound(403, "INWARD_TOOLS");
		Privilege outwardStocks = createPrivilegeIfNotFound(404, "OUTWARD_STOCKS");
		Privilege stocksReturn = createPrivilegeIfNotFound(405, "STOCKS_RETURN");
		Privilege stockApprovals = createPrivilegeIfNotFound(406, "STOCKS_APPROVALS");

		/* Work Order User Roles */

		Privilege generateWorkOrder = createPrivilegeIfNotFound(500, "GENERATE_WORKORDER");
		Privilege holdWorkOrder = createPrivilegeIfNotFound(501, "HOLD_WORKORDER");
		Privilege cancelWorkOrder = createPrivilegeIfNotFound(502, "CANCEL_WORKORDER");
		Privilege workEstmation = createPrivilegeIfNotFound(503, "WORK_ESTIMATION");

		/* Employee Management User Roles */

		Privilege employeeDashBoard = createPrivilegeIfNotFound(600, "EMPLOYEE_DASHBOARD");
		Privilege employeeMaster = createPrivilegeIfNotFound(601, "EMPLOYEE_MASTER");
		Privilege attendance = createPrivilegeIfNotFound(602, "EMPLOYEE_ATTENDANCE");
		Privilege salary = createPrivilegeIfNotFound(603, "EMPLOYEE_SALARY");
		Privilege inspection = createPrivilegeIfNotFound(604, "EMPLOYEE_INSPECTION");
		Privilege leave = createPrivilegeIfNotFound(605, "EMPLOYEE_LEAVE");

		/* Billing Management User Roles */

		Privilege consMasterDetails = createPrivilegeIfNotFound(700, "CONSUMER_MASTER");
		Privilege consTransDetails = createPrivilegeIfNotFound(701, "CONSUMER_TRANSACTION");
		Privilege meterDetails = createPrivilegeIfNotFound(702, "METER_DETAILS");

		/* Pump Management User Roles */

		Privilege pumpDashboard = createPrivilegeIfNotFound(800, "PUMP_DASHBOARD");
		Privilege pumpMaintenanceIndex = createPrivilegeIfNotFound(801, "PUMP_INDEX");
		Privilege pumpMaintenanceIndent = createPrivilegeIfNotFound(802, "PUMP_INDENT");
		Privilege pumpMaintenanceView = createPrivilegeIfNotFound(803, "PUMP_VIEW");
		Privilege pumpMaintenanceUpdate = createPrivilegeIfNotFound(804, "PUMP_UPDATE");
		Privilege pumpMaintenanceInspection = createPrivilegeIfNotFound(805, "PUMP_INSPECTION");
		Privilege pumpMaintenanceHistory = createPrivilegeIfNotFound(806, "PUMP_HISTORY");

		/* Pipe Management User Roles */

		Privilege pipeDashboard = createPrivilegeIfNotFound(900, "PIPE_DASHBOARD");
		Privilege pipeMaintenanceIndex = createPrivilegeIfNotFound(901, "PIPE_INDEX");
		Privilege pipeMaintenanceIndent = createPrivilegeIfNotFound(902, "PIPE_INDENT");
		Privilege pipeMaintenanceView = createPrivilegeIfNotFound(903, "PIPE_VIEW");
		Privilege pipeMaintenanceUpdate = createPrivilegeIfNotFound(904, "PIPE_UPDATE");
		Privilege pipeMaintenanceInspection = createPrivilegeIfNotFound(905, "PIPE_INSPECTION");
		Privilege pipeMaintenanceHistory = createPrivilegeIfNotFound(906, "PIPE_HISTORY");

		/* Vehicle Management User Roles */

		Privilege vehicleTrackingMangement = createPrivilegeIfNotFound(1100, "VEHICLE_MANAGEMENT");
		Privilege vehicleHistory = createPrivilegeIfNotFound(1101, "VEHICLE_HISTORY");
		Privilege vehicleIndex = createPrivilegeIfNotFound(1103, "VEHICLE_INDEX");
		Privilege vehicleManagementIndex = createPrivilegeIfNotFound(1104, "VEHICLE_MANAGEMENT_INDEX");
		Privilege vehicleInspection = createPrivilegeIfNotFound(1105, "VEHICLE_INSPECTION");
		Privilege vehicleWorkOrderView = createPrivilegeIfNotFound(1106, "VEHICLE_WORKORDER");

		/* Leakage Management User Roles */

		Privilege leakageDashboard = createPrivilegeIfNotFound(1200, "LEAKAGE_DASHBOARD");
		Privilege leakageIndent = createPrivilegeIfNotFound(1201, "LEAKAGE_INDENET");
		Privilege leakageMaintenanceUpdate = createPrivilegeIfNotFound(1202, "LEAKAGE_MAINTENANCE");
		Privilege leakageWorkInspection = createPrivilegeIfNotFound(1203, "LEAKAGE_WORK_INSPECTION");
		Privilege leakageLeakageHistory = createPrivilegeIfNotFound(1204, "LEAKAGE_HISTORY");

		/* Meter Management User Roles */

		Privilege meterReplace = createPrivilegeIfNotFound(1301, "METER_REPLACEMENT");

		/* Hand Pump Management User Roles */

		Privilege handPumpIndex = createPrivilegeIfNotFound(1400, "HANDPUMP_INDEX");
		Privilege handPumpIndent = createPrivilegeIfNotFound(1401, "HANDPUMP_INDENT");
		Privilege handPumpWorkOrder = createPrivilegeIfNotFound(1402, "HANDPUMP_WORKORDER");
		Privilege handPumpWorkUpdate = createPrivilegeIfNotFound(1403, "HANDPUMP_WORKUPDATE");
		Privilege handPumpHandPumpInspection = createPrivilegeIfNotFound(1404, "HANDPUMP_INSPECTION");
		Privilege handPumpMaintenanceHistory = createPrivilegeIfNotFound(1405, "HANDPUMP_HISTORY");

		/* Borewell Management User Roles */

		Privilege borewellIndex = createPrivilegeIfNotFound(1500, "BOREWELL_INDEX");
		Privilege borewellIndent = createPrivilegeIfNotFound(1501, "BOREWELL_INDENT");
		Privilege borewellWorkOrder = createPrivilegeIfNotFound(1502, "BOREWELL_WORKORDER");
		Privilege borewellWorkUpdate = createPrivilegeIfNotFound(1503, "BOREWELL_WORKUPDATE");
		Privilege borewellHandPumpInspection = createPrivilegeIfNotFound(1504, "BOREWELL_INSPECTION");
		Privilege borewellMaintenanceHistory = createPrivilegeIfNotFound(1505, "BOREWELL_HISTORY");

		/* glsroht Management User Roles */

		Privilege glsrohtIndex = createPrivilegeIfNotFound(1600, "GLSR_OHT_INDEX");
		Privilege glsrohtIndent = createPrivilegeIfNotFound(1601, "GLSR_OHT_INDENT");
		Privilege glsrohtWorkOrder = createPrivilegeIfNotFound(1602, "GLSR_OHT_WORKORDER");
		Privilege glsrohtWorkUpdate = createPrivilegeIfNotFound(1603, "GLSR_OHT_WORKUPDATE");
		Privilege glsrohtHandPumpInspection = createPrivilegeIfNotFound(1604, "GLSR_OHT_INSPECTION");
		Privilege glsrohtMaintenanceHistory = createPrivilegeIfNotFound(1605, "GLSR_OHT_HISTORY");

		/* Levels Control User Roles */

		Privilege levelsControlIndex = createPrivilegeIfNotFound(1700, "LEVELS_CONTROL_INDEX");
		Privilege levelsControlIndent = createPrivilegeIfNotFound(1701, "LEVELS_CONTROL_INDENT");
		Privilege levelsControlWorkOrder = createPrivilegeIfNotFound(1702, "LEVELS_CONTROL_WORKORDER");
		Privilege levelsControlWorkUpdate = createPrivilegeIfNotFound(1703, "LEVELS_CONTROL_WORKUPDATE");
		Privilege levelsControlInspection = createPrivilegeIfNotFound(1704, "LEVELS_CONTROL_INSPECTION");
		Privilege levelsControlHistory = createPrivilegeIfNotFound(1705, "LEVELS_CONTROL_HISTORY");

		/* Contact Management User Roles */

		Privilege contactManagement = createPrivilegeIfNotFound(1900, "CONTACT_MANAGEMENT");

		/* Approvals User Roles */

		Privilege approvals = createPrivilegeIfNotFound(2100, "APPROVALS");

		/* MIS Reports User Roles */

		Privilege dailyReport = createPrivilegeIfNotFound(2200, "MIS_DAILY_REPORT");
		Privilege monthlyReport = createPrivilegeIfNotFound(2201, "MIS_MONTHLY_REPORT");

		/* Masters User Roles */

		Privilege masters = createPrivilegeIfNotFound(2300, "MASTERS");

		/* Assigning Privileges */

		List<Privilege> adminPrivileges = Arrays.asList(adminpage, companyManagement, createCompany, editCompany,
				viewCompany, deleteCompany, branchManagement, createBranch, editBranch, viewBranch, deleteBranch,
				userManagement, createUser, editUser, deleteUser, roleManagement, createRole, editRole, deleteRole,
				userRoles, updateUserRoles, importExport, deviceControl, serverBackup, taskJe, taskAee, taskEe,
				taskCommissioner, taskWorkcomplete, taskComplainthistory, assestManagement, stockAvailable,
				inwardMatierals, inwardSpares, inwardTools, outwardStocks, stocksReturn, stockApprovals,
				generateWorkOrder, holdWorkOrder, cancelWorkOrder, workEstmation, employeeDashBoard, employeeMaster,
				attendance, salary, inspection, leave, consMasterDetails, consTransDetails, meterDetails, pumpDashboard,
				pumpMaintenanceIndex, pumpMaintenanceIndent, pumpMaintenanceView, pumpMaintenanceUpdate,
				pumpMaintenanceInspection, pumpMaintenanceHistory, pipeDashboard, pipeMaintenanceIndex,
				pipeMaintenanceIndent, pipeMaintenanceView, pipeMaintenanceUpdate, pipeMaintenanceInspection,
				pipeMaintenanceHistory, vehicleTrackingMangement, vehicleHistory, vehicleIndex, vehicleManagementIndex,
				vehicleInspection, vehicleWorkOrderView, leakageDashboard, leakageIndent, leakageMaintenanceUpdate,
				leakageWorkInspection, leakageLeakageHistory, leakageLeakageHistory, meterReplace, handPumpIndex,
				handPumpIndent, handPumpWorkOrder, handPumpWorkUpdate, handPumpHandPumpInspection,
				handPumpMaintenanceHistory, borewellIndex, borewellIndent, borewellWorkOrder, borewellWorkUpdate,
				borewellHandPumpInspection, borewellMaintenanceHistory, glsrohtIndex, glsrohtIndent, glsrohtWorkOrder,
				glsrohtWorkUpdate, glsrohtHandPumpInspection, glsrohtMaintenanceHistory, levelsControlIndex,
				levelsControlIndent, levelsControlWorkOrder, levelsControlWorkUpdate, levelsControlInspection,
				levelsControlHistory, contactManagement, approvals, dailyReport, monthlyReport, masters);

		List<Privilege> companyPrivileges = Arrays.asList(adminpage, branchManagement, createBranch, editBranch,
				viewBranch, deleteBranch, userManagement, createUser, editUser, deleteUser, roleManagement, createRole,
				editRole, deleteRole, userRoles, updateUserRoles, importExport, deviceControl, serverBackup, taskJe,
				taskAee, taskEe, taskCommissioner, taskWorkcomplete, taskComplainthistory,
				assestManagement, stockAvailable, inwardMatierals, inwardSpares, inwardTools, outwardStocks,
				stocksReturn, stockApprovals, generateWorkOrder, holdWorkOrder, cancelWorkOrder, workEstmation,
				employeeDashBoard, employeeMaster, attendance, salary, inspection, leave, consMasterDetails,
				consTransDetails, meterDetails, pumpDashboard, pumpMaintenanceIndex, pumpMaintenanceView,
				pumpMaintenanceUpdate, pumpMaintenanceInspection, pumpMaintenanceHistory, pumpMaintenanceIndent,
				pipeDashboard, pipeMaintenanceIndex, pipeMaintenanceIndent, pipeMaintenanceView, pipeMaintenanceUpdate,
				pipeMaintenanceInspection, pipeMaintenanceHistory, vehicleTrackingMangement, vehicleHistory,
				vehicleIndex, vehicleManagementIndex, vehicleInspection, vehicleWorkOrderView, leakageDashboard,
				leakageIndent, leakageMaintenanceUpdate, leakageWorkInspection, leakageLeakageHistory,
				leakageLeakageHistory, meterReplace, handPumpIndex, handPumpIndent, handPumpWorkOrder,
				handPumpWorkUpdate, handPumpHandPumpInspection, handPumpMaintenanceHistory, borewellIndex,
				borewellIndent, borewellWorkOrder, borewellWorkUpdate, borewellHandPumpInspection,
				borewellMaintenanceHistory, glsrohtIndex, glsrohtIndent, glsrohtWorkOrder, glsrohtWorkUpdate,
				glsrohtHandPumpInspection, glsrohtMaintenanceHistory, levelsControlIndex, levelsControlIndent,
				levelsControlWorkOrder, levelsControlWorkUpdate, levelsControlInspection, levelsControlHistory,
				contactManagement, approvals, dailyReport, monthlyReport, masters);

		List<Privilege> branchPrivileges = Arrays.asList(adminpage, userManagement, createUser, editUser, deleteUser,
				roleManagement, createRole, editRole, deleteRole, userRoles, updateUserRoles, importExport,
				deviceControl, serverBackup, taskJe, taskAee, taskEe, taskCommissioner, taskWorkcomplete,
				taskComplainthistory, assestManagement, stockAvailable, inwardMatierals, inwardSpares, inwardTools,
				outwardStocks, stocksReturn, stockApprovals, generateWorkOrder, holdWorkOrder, cancelWorkOrder,
				workEstmation, employeeDashBoard, employeeMaster, attendance, salary, inspection, leave,
				consMasterDetails, consTransDetails, meterDetails, pumpDashboard, pumpMaintenanceIndex,
				pumpMaintenanceView, pumpMaintenanceUpdate, pumpMaintenanceInspection, pumpMaintenanceHistory,
				pumpMaintenanceIndent, pipeDashboard, pipeMaintenanceIndex, pipeMaintenanceIndent, pipeMaintenanceView,
				pipeMaintenanceUpdate, pipeMaintenanceInspection, pipeMaintenanceHistory, vehicleTrackingMangement,
				vehicleHistory, vehicleIndex, vehicleManagementIndex, vehicleInspection, vehicleWorkOrderView,
				leakageDashboard, leakageIndent, leakageMaintenanceUpdate, leakageWorkInspection, leakageLeakageHistory,
				leakageLeakageHistory, meterReplace, handPumpIndex, handPumpIndent, handPumpWorkOrder,
				handPumpWorkUpdate, handPumpHandPumpInspection, handPumpMaintenanceHistory, borewellIndex,
				borewellIndent, borewellWorkOrder, borewellWorkUpdate, borewellHandPumpInspection,
				borewellMaintenanceHistory, glsrohtIndex, glsrohtIndent, glsrohtWorkOrder, glsrohtWorkUpdate,
				glsrohtHandPumpInspection, glsrohtMaintenanceHistory, levelsControlIndex, levelsControlIndent,
				levelsControlWorkOrder, levelsControlWorkUpdate, levelsControlInspection, levelsControlHistory,
				contactManagement, approvals, dailyReport, monthlyReport, masters);

		/* Roles */

		Role admin = createRoleIfNotFound("ROLE_ADMIN", "Admin has full access", adminPrivileges);
		createRoleIfNotFound("ROLE_COMPANY", "Company level access", companyPrivileges);
		createRoleIfNotFound("ROLE_BRANCH", "Branch level access", branchPrivileges);

		/* States */

		statesIfNotFound("ANDAMAN & NICOBAR ISLANDS");
		statesIfNotFound("ANDHRA PRADESH");
		statesIfNotFound("ASSAM");
		statesIfNotFound("BIHAR");
		statesIfNotFound("CHHATTISGARH");
		statesIfNotFound("DELHI");
		statesIfNotFound("GOA");
		statesIfNotFound("GUJARAT");
		statesIfNotFound("HARYANA");
		statesIfNotFound("HIMACHAL PRADESH");
		statesIfNotFound("JAMMU & KASHMIR");
		statesIfNotFound("JHARKHAND");
		statesIfNotFound("KARNATAKA");
		statesIfNotFound("KERALA");
		statesIfNotFound("MADHYA PRADESH");
		statesIfNotFound("MAHARASHTRA");
		statesIfNotFound("MANIPUR");
		statesIfNotFound("MEGHALAYA");
		statesIfNotFound("MIZORAM");
		statesIfNotFound("NCR");
		statesIfNotFound("NEPAL");
		statesIfNotFound("NEW DELHI");
		statesIfNotFound("ODISHA");
		statesIfNotFound("PUNJAB");
		statesIfNotFound("RAJASTHAN");
		statesIfNotFound("SIKKIM");
		statesIfNotFound("TAMIL NADU");
		statesIfNotFound("TRIPURA");
		statesIfNotFound("UNION TERRITORY OF DADRA & NAGAR HAVELI");
		statesIfNotFound("UNION TERRITORY OF LAKSHADWEEP");
		statesIfNotFound("UTTAR PRADESH");
		statesIfNotFound("UTTARAKHAND");
		statesIfNotFound("UTTARANCHAL");
		statesIfNotFound("WEST BENGAL");

		/* Cities */

		citiesIfNotFound("AGARTALA", 28);
		citiesIfNotFound("AGRA", 31);
		citiesIfNotFound("AHMEDABAD", 8);
		citiesIfNotFound("AIZWAL", 19);
		citiesIfNotFound("AJMER", 25);
		citiesIfNotFound("ALIBAUG", 16);
		citiesIfNotFound("ALLAHABAD", 31);
		citiesIfNotFound("ALLEPPEY", 14);
		citiesIfNotFound("ALMORA", 33);
		citiesIfNotFound("ALSISAR", 25);
		citiesIfNotFound("ALWAR", 25);
		citiesIfNotFound("AMBALA", 9);
		citiesIfNotFound("AMLA", 15);
		citiesIfNotFound("AMRITSAR", 24);
		citiesIfNotFound("ANAND", 8);
		citiesIfNotFound("ANKLESHWAR", 8);
		citiesIfNotFound("ASHTAMUDI", 14);
		citiesIfNotFound("AULI", 10);
		citiesIfNotFound("AURANGABAD", 16);
		citiesIfNotFound("BADDI", 10);
		citiesIfNotFound("BADRINATH", 33);
		citiesIfNotFound("BALASINOR", 8);
		citiesIfNotFound("BALRAMPUR", 31);
		citiesIfNotFound("BAMBORA", 25);
		citiesIfNotFound("BANDHAVGARH", 15);
		citiesIfNotFound("BANDIPUR", 13);
		citiesIfNotFound("BANGALORE", 13);
		citiesIfNotFound("BARBIL", 23);
		citiesIfNotFound("BAREILLY", 31);
		citiesIfNotFound("BEHROR", 25);
		citiesIfNotFound("BELGAUM", 13);
		citiesIfNotFound("BERHAMPUR", 23);
		citiesIfNotFound("BETALGHAT", 33);
		citiesIfNotFound("BHANDARDARA", 16);
		citiesIfNotFound("BHARATPUR", 25);
		citiesIfNotFound("BHARUCH", 8);
		citiesIfNotFound("BHAVANGADH", 8);
		citiesIfNotFound("BHAVNAGAR", 8);
		citiesIfNotFound("BHILAI", 5);
		citiesIfNotFound("BHIMTAL", 33);
		citiesIfNotFound("BHOPAL", 15);
		citiesIfNotFound("BHUBANESHWAR", 23);
		citiesIfNotFound("BHUJ", 8);
		citiesIfNotFound("BIKANER", 25);
		citiesIfNotFound("BINSAR", 33);
		citiesIfNotFound("BODHGAYA", 4);
		citiesIfNotFound("BUNDI", 25);
		citiesIfNotFound("CALICUT", 14);
		citiesIfNotFound("CANANNORE", 14);
		citiesIfNotFound("CHAIL", 10);
		citiesIfNotFound("CHAMBA", 33);
		citiesIfNotFound("CHANDIGARH", 24);
		citiesIfNotFound("CHENNAI", 27);
		citiesIfNotFound("CHIKMAGALUR", 13);
		citiesIfNotFound("CHIPLUN", 16);
		citiesIfNotFound("CHITRAKOOT", 15);
		citiesIfNotFound("CHITTORGARH", 25);
		citiesIfNotFound("COIMBATORE", 27);
		citiesIfNotFound("COONOOR", 27);
		citiesIfNotFound("COORG", 13);
		citiesIfNotFound("CORBETT NATIONAL PARK", 33);
		citiesIfNotFound("CUTTACK", 23);
		citiesIfNotFound("DABHOSA", 16);
		citiesIfNotFound("DALHOUSIE", 10);
		citiesIfNotFound("DAMAN", 29);
		citiesIfNotFound("DANDELI", 13);
		citiesIfNotFound("DAPOLI", 16);
		citiesIfNotFound("DARJEELING", 34);
		citiesIfNotFound("DAUSA", 25);
		citiesIfNotFound("DEHRADUN", 31);
		citiesIfNotFound("DHARAMSHALA", 10);
		citiesIfNotFound("DIBRUGARH", 3);
		citiesIfNotFound("DIGHA", 34);
		citiesIfNotFound("DIU", 29);
		citiesIfNotFound("DIVE AGAR", 16);
		citiesIfNotFound("DOOARS", 34);
		citiesIfNotFound("DURGAPUR", 34);
		citiesIfNotFound("DURSHET", 16);
		citiesIfNotFound("DWARKA", 8);
		citiesIfNotFound("FARIDABAD", 9);
		citiesIfNotFound("FIROZABAD", 31);
		citiesIfNotFound("GANAPATIPULE", 16);
		citiesIfNotFound("GANDHIDHAM", 8);
		citiesIfNotFound("GANDHINAGAR", 8);
		citiesIfNotFound("GANGOTRI", 33);
		citiesIfNotFound("GANGTOK", 26);
		citiesIfNotFound("GARHMUKTESHWAR", 31);
		citiesIfNotFound("GARHWAL", 33);
		citiesIfNotFound("GAYA", 4);
		citiesIfNotFound("GHAZIABAD", 22);
		citiesIfNotFound("GOA", 7);
		citiesIfNotFound("GOKHARNA", 14);
		citiesIfNotFound("GONDAL", 8);
		citiesIfNotFound("GORAKHPUR", 31);
		citiesIfNotFound("GREATER NOIDA", 20);
		citiesIfNotFound("GULMARG", 11);
		citiesIfNotFound("GURGAON", 9);
		citiesIfNotFound("GURUVAYOOR", 14);
		citiesIfNotFound("GUWAHATI", 3);
		citiesIfNotFound("GWALIOR", 15);
		citiesIfNotFound("HALEBID", 13);
		citiesIfNotFound("HAMPI", 13);
		citiesIfNotFound("HANSI", 9);
		citiesIfNotFound("HARIDWAR", 31);
		citiesIfNotFound("HASSAN", 13);
		citiesIfNotFound("HOSPET", 13);
		citiesIfNotFound("HOSUR", 27);
		citiesIfNotFound("HUBLI", 13);
		citiesIfNotFound("HYDERABAD", 2);
		citiesIfNotFound("IDUKKI", 14);
		citiesIfNotFound("IGATPURI", 16);
		citiesIfNotFound("IMPHAL", 17);
		citiesIfNotFound("INDORE", 15);
		citiesIfNotFound("JABALPUR", 15);
		citiesIfNotFound("JAIPUR", 25);
		citiesIfNotFound("JAISALMER", 25);
		citiesIfNotFound("JALANDHAR", 24);
		citiesIfNotFound("JALGAON", 16);
		citiesIfNotFound("JAMBUGODHA", 8);
		citiesIfNotFound("JAMMU", 11);
		citiesIfNotFound("JAMNAGAR", 8);
		citiesIfNotFound("JAMSHEDPUR", 12);
		citiesIfNotFound("JAWHAR", 16);
		citiesIfNotFound("JHANSI", 31);
		citiesIfNotFound("JODHPUR", 25);
		citiesIfNotFound("JOJAWAR", 25);
		citiesIfNotFound("JORHAT", 3);
		citiesIfNotFound("JUNAGADH", 8);
		citiesIfNotFound("KABINI", 13);
		citiesIfNotFound("KALIMPONG", 34);
		citiesIfNotFound("KANATAL", 33);
		citiesIfNotFound("KANCHIPURAM", 27);
		citiesIfNotFound("KANHA", 15);
		citiesIfNotFound("KANPUR", 31);
		citiesIfNotFound("KANYAKUMARI", 27);
		citiesIfNotFound("KARGIL", 11);
		citiesIfNotFound("KARJAT", 16);
		citiesIfNotFound("KARNAL", 9);
		citiesIfNotFound("KARUR", 27);
		citiesIfNotFound("KARWAR", 13);
		citiesIfNotFound("KASARGOD", 14);
		citiesIfNotFound("KASAULI", 10);
		citiesIfNotFound("KASHID", 16);
		citiesIfNotFound("KASHIPUR", 33);
		citiesIfNotFound("KATRA", 11);
		citiesIfNotFound("KAUSANI", 32);
		citiesIfNotFound("KAZA", 10);
		citiesIfNotFound("KAZIRANGA", 3);
		citiesIfNotFound("KEDARNATH", 33);
		citiesIfNotFound("KHAJJIAR", 10);
		citiesIfNotFound("KHAJURAHO", 15);
		citiesIfNotFound("KHANDALA", 16);
		citiesIfNotFound("KHIMSAR", 25);
		citiesIfNotFound("KOCHIN", 14);
		citiesIfNotFound("KODAIKANAL", 27);
		citiesIfNotFound("KOLHAPUR", 16);
		citiesIfNotFound("KOLKATA", 34);
		citiesIfNotFound("KOLLAM", 14);
		citiesIfNotFound("KOTA", 25);
		citiesIfNotFound("KOTAGIRI", 27);
		citiesIfNotFound("KOTTAYAM", 14);
		citiesIfNotFound("KOVALAM", 14);
		citiesIfNotFound("KUFRI", 10);
		citiesIfNotFound("KULLU", 10);
		citiesIfNotFound("KUMARAKOM", 14);
		citiesIfNotFound("KUMBAKONAM", 27);
		citiesIfNotFound("KUMBALGARH", 25);
		citiesIfNotFound("KUMILY", 14);
		citiesIfNotFound("KURSEONG", 34);
		citiesIfNotFound("KUSHINAGAR", 31);
		citiesIfNotFound("LACHUNG", 26);
		citiesIfNotFound("LAKSHADWEEP", 30);
		citiesIfNotFound("LEH", 11);
		citiesIfNotFound("LONAVALA", 16);
		citiesIfNotFound("LOTHAL", 8);
		citiesIfNotFound("LUCKNOW", 31);
		citiesIfNotFound("LUDHIANA", 24);
		citiesIfNotFound("LUMBINI", 21);
		citiesIfNotFound("MADURAI", 27);
		citiesIfNotFound("MAHABALESHWAR", 16);
		citiesIfNotFound("MAHABALIPURAM", 27);
		citiesIfNotFound("MALAPPURAM", 14);
		citiesIfNotFound("MALPE", 13);
		citiesIfNotFound("MALSHEJ GHAT", 16);
		citiesIfNotFound("MALVAN", 16);
		citiesIfNotFound("MANALI", 10);
		citiesIfNotFound("MANDAVI", 8);
		citiesIfNotFound("MANDAWA", 25);
		citiesIfNotFound("MANDORMONI", 34);
		citiesIfNotFound("MANESAR", 9);
		citiesIfNotFound("MANGALORE", 13);
		citiesIfNotFound("MANMAD", 16);
		citiesIfNotFound("MARARRI", 14);
		citiesIfNotFound("MARCHULA", 33);
		citiesIfNotFound("MATHERAN", 16);
		citiesIfNotFound("MATHURA", 31);
		citiesIfNotFound("MCLEODGANJ", 10);
		citiesIfNotFound("MOHALI", 24);
		citiesIfNotFound("MORADABAD", 31);
		citiesIfNotFound("MORBI", 8);
		citiesIfNotFound("MOUNT ABU", 25);
		citiesIfNotFound("MUKTESHWAR", 33);
		citiesIfNotFound("MUMBAI", 16);
		citiesIfNotFound("MUNDRA", 16);
		citiesIfNotFound("MUNNAR", 14);
		citiesIfNotFound("MURUD JANJIRA", 16);
		citiesIfNotFound("MUSSOORIE", 33);
		citiesIfNotFound("MYSORE", 13);
		citiesIfNotFound("NADUKANI", 14);
		citiesIfNotFound("NAGAPATTINAM", 27);
		citiesIfNotFound("NAGARHOLE", 13);
		citiesIfNotFound("NAGAUR FORT", 25);
		citiesIfNotFound("NAGOTHANE", 16);
		citiesIfNotFound("NAGPUR", 16);
		citiesIfNotFound("NAHAN", 10);
		citiesIfNotFound("NAINITAL", 31);
		citiesIfNotFound("NALDHERA", 10);
		citiesIfNotFound("NANDED", 16);
		citiesIfNotFound("NAPNE", 16);
		citiesIfNotFound("NASIK", 16);
		citiesIfNotFound("NAVI MUMBAI", 16);
		citiesIfNotFound("NERAL", 16);
		citiesIfNotFound("NEW DELHI", 6);
		citiesIfNotFound("NILGIRIS", 27);
		citiesIfNotFound("NOIDA", 31);
		citiesIfNotFound("OOTY", 27);
		citiesIfNotFound("ORCHHA", 15);
		citiesIfNotFound("OSIAN", 25);
		citiesIfNotFound("PACHMARHI", 15);
		citiesIfNotFound("PAHALGAM", 11);
		citiesIfNotFound("PALAMPUR", 10);
		citiesIfNotFound("PALANPUR", 8);
		citiesIfNotFound("PALI", 25);
		citiesIfNotFound("PALITANA", 8);
		citiesIfNotFound("PALLAKAD", 14);
		citiesIfNotFound("PANCHGANI", 16);
		citiesIfNotFound("PANCHKULA", 9);
		citiesIfNotFound("PANHALA", 16);
		citiesIfNotFound("PANNA", 15);
		citiesIfNotFound("PANTNAGAR", 33);
		citiesIfNotFound("PANVEL", 16);
		citiesIfNotFound("PARWANOO", 10);
		citiesIfNotFound("PATHANKOT", 11);
		citiesIfNotFound("PATIALA", 24);
		citiesIfNotFound("PATNA", 4);
		citiesIfNotFound("PATNITOP", 11);
		citiesIfNotFound("PELLING", 26);
		citiesIfNotFound("PENCH", 15);
		citiesIfNotFound("PHAGWARA", 24);
		citiesIfNotFound("PHALODI", 25);
		citiesIfNotFound("PINJORE", 9);
		citiesIfNotFound("PONDICHERRY", 27);
		citiesIfNotFound("POOVAR", 14);
		citiesIfNotFound("PORBANDAR", 8);
		citiesIfNotFound("PORT BLAIR", 1);
		citiesIfNotFound("POSHINA", 8);
		citiesIfNotFound("PRAGPUR", 10);
		citiesIfNotFound("PUNE", 16);
		citiesIfNotFound("PURI", 23);
		citiesIfNotFound("PUSKHAR", 25);
		citiesIfNotFound("PUTTAPARTHI", 2);
		citiesIfNotFound("RAI BAREILLY", 31);
		citiesIfNotFound("RAICHAK", 34);
		citiesIfNotFound("RAIPUR", 5);
		citiesIfNotFound("RAJAHMUNDRY", 2);
		citiesIfNotFound("RAJASTHAN", 25);
		citiesIfNotFound("RAJGIR", 4);
		citiesIfNotFound("RAJKOT", 8);
		citiesIfNotFound("RAJPIPLA", 8);
		citiesIfNotFound("RAJSAMAND", 25);
		citiesIfNotFound("RAM NAGAR", 31);
		citiesIfNotFound("RAMESHWARAM", 27);
		citiesIfNotFound("RAMGARH", 25);
		citiesIfNotFound("RANAKPUR", 25);
		citiesIfNotFound("RANCHI", 12);
		citiesIfNotFound("RANIKHET", 33);
		citiesIfNotFound("RANNY", 14);
		citiesIfNotFound("RANTHAMBORE", 25);
		citiesIfNotFound("RATNAGIRI", 16);
		citiesIfNotFound("RAVANGLA", 26);
		citiesIfNotFound("RISHIKESH", 31);
		citiesIfNotFound("RISHYAP", 34);
		citiesIfNotFound("ROHETGARH", 25);
		citiesIfNotFound("ROURKELA", 23);
		citiesIfNotFound("SAJAN", 16);
		citiesIfNotFound("SALEM", 27);
		citiesIfNotFound("SAPUTARA", 8);
		citiesIfNotFound("SASAN GIR", 8);
		citiesIfNotFound("SATTAL", 33);
		citiesIfNotFound("SAWAI MADHOPUR", 25);
		citiesIfNotFound("SAWANTWADI", 16);
		citiesIfNotFound("SECUNDERABAD", 2);
		citiesIfNotFound("SHARAVANBELGOLA", 13);
		citiesIfNotFound("SHILLONG", 18);
		citiesIfNotFound("SHIMLA", 10);
		citiesIfNotFound("SHIMLIPAL", 23);
		citiesIfNotFound("SHIRDI", 16);
		citiesIfNotFound("SHIVANASAMUDRA", 13);
		citiesIfNotFound("SIANA", 25);
		citiesIfNotFound("SILIGURI", 34);
		citiesIfNotFound("SILVASSA", 29);
		citiesIfNotFound("SIVAGANGA", 27);
		citiesIfNotFound("SOLAN", 10);
		citiesIfNotFound("SONAULI", 31);
		citiesIfNotFound("SRINAGAR", 11);
		citiesIfNotFound("SUNDERBAN", 34);
		citiesIfNotFound("SURAT", 8);
		citiesIfNotFound("TANJORE", 27);
		citiesIfNotFound("TAPOLA", 16);
		citiesIfNotFound("TARAPITH", 34);
		citiesIfNotFound("THANE", 16);
		citiesIfNotFound("THEKKADY", 14);
		citiesIfNotFound("THIRUVANANTHAPURAM", 14);
		citiesIfNotFound("THIRVANNAMALAI", 27);
		citiesIfNotFound("THRISSUR", 14);
		citiesIfNotFound("TIRUCHIRAPALLI", 27);
		citiesIfNotFound("TIRUPATI", 2);
		citiesIfNotFound("TIRUPUR", 27);
		citiesIfNotFound("UDAIPUR", 25);
		citiesIfNotFound("UDHAMPUR", 11);
		citiesIfNotFound("UDUPI", 13);
		citiesIfNotFound("UJJAIN", 15);
		citiesIfNotFound("UTTARKASHI", 33);
		citiesIfNotFound("VADODARA", 8);
		citiesIfNotFound("VAGAMON", 14);
		citiesIfNotFound("VAPI", 8);
		citiesIfNotFound("VARANASI", 31);
		citiesIfNotFound("VARKALA", 14);
		citiesIfNotFound("VELANKANNI", 27);
		citiesIfNotFound("VELLORE", 27);
		citiesIfNotFound("VERAVAL", 16);
		citiesIfNotFound("VIJAYAWADA", 2);
		citiesIfNotFound("VIKRAMGADH", 16);
		citiesIfNotFound("VISHAKAPATNAM", 2);
		citiesIfNotFound("WANKANER", 8);
		citiesIfNotFound("WAYANAD", 14);
		citiesIfNotFound("YAMUNOTRI", 33);
		citiesIfNotFound("YERCAUD", 27);
		citiesIfNotFound("YUKSOM", 26);

		/* Banks */

		banksIfNotFound("AXIS BANK LTD");
		banksIfNotFound("BANDHAN BANK LTD");
		banksIfNotFound("BANK OF BARODA");
		banksIfNotFound("BANK OF INDIA");
		banksIfNotFound("BANK OF MAHARASHTRA");
		banksIfNotFound("CANARA BANK");
		banksIfNotFound("CENTRAL BANK OF INDIA");
		banksIfNotFound("BANK UNION BANK LTD");
		banksIfNotFound("CSB BANK LTD");
		banksIfNotFound("DCB BANK LTD");
		banksIfNotFound("DHANLAXMI BANK LTD");
		banksIfNotFound("FEDERAL BANK LTD");
		banksIfNotFound("HDFC BANK LTD");
		banksIfNotFound("ICICI BANK LTD");
		banksIfNotFound("IDBI BANK LTD");
		banksIfNotFound("IDFC FIRST BANK LTD");
		banksIfNotFound("INDIAN BANK");
		banksIfNotFound("INDIAN OVERSEAS BANK");
		banksIfNotFound("INDUSLND BANK LTD");
		banksIfNotFound("JAMMU & KASHMIR BANK LTD");
		banksIfNotFound("KARNATAKA BANK LTD");
		banksIfNotFound("KARUR VYSYA BANK LTD");
		banksIfNotFound("KOTAK MAHINDRA BANK LTD");
		banksIfNotFound("NAINITAL BANK LTD");
		banksIfNotFound("PUNJAB & SIND BANK");
		banksIfNotFound("PUNJAB NATIONAL BANK");
		banksIfNotFound("RBL BANK LTD");
		banksIfNotFound("SOUTH INDIAN BANK LTD");
		banksIfNotFound("STATE BANK OF INDIA");
		banksIfNotFound("TAMILNAD MERCANTILE BANK LTD");
		banksIfNotFound("UCO BANK");
		banksIfNotFound("UNION BANK OF INDIA");
		banksIfNotFound("YES BANK LTD");

		/* User */

		createAdminIfNotFound("Admin", "Admin", "Admin", "ingroinfo@gmail.com", "999999999", "A", "Admin", 1L,
				Arrays.asList(admin));

		alreadySetup = true;
	}

	@Transactional
	User createAdminIfNotFound(String name, String username, String password, String email, String mobile,
			String userType, String designation, Long ubarmsUserId, Collection<Role> roles) {

		User user = userRepository.findByUsername(username);
		String encryptPassword = getEncodedPassword(password);

		if (user == null) {
			user = new User(name, username, encryptPassword, email, mobile, userType, designation, ubarmsUserId);
			user.setRoles(roles);
			userRepository.save(user);
		}
		return user;
	}

	@Transactional
	Privilege createPrivilegeIfNotFound(int pageNo, String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(pageNo, name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(String name, String description, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name, description);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

	@Transactional
	void statesIfNotFound(String stateName) {

		State state = stateRepository.findByName(stateName);
		if (state == null) {
			state = new State(stateName);
			stateRepository.save(state);
		}
	}

	@Transactional
	void citiesIfNotFound(String cityName, Integer state) {

		City city = cityRepository.findByName(cityName);
		if (city == null) {
			State getState = stateRepository.findById(state).get();
			city = new City(cityName, getState);
			cityRepository.save(city);
		}
	}

	@Transactional
	void banksIfNotFound(String bankName) {

		Bank bank = bankRepository.findByName(bankName);
		if (bank == null) {
			bank = new Bank(bankName);
			bankRepository.save(bank);
		}
	}		

}
