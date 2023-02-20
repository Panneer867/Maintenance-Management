package com.ingroinfo.mm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesDto {

	private Long roleId;
	private String adminpage; // 100

	private String companyManagement; // 101
	private String createCompany; // 102
	private String editCompany; // 103
	private String viewCompany; // 104
	private String deleteCompany; // 105

	private String branchManagement; // 106
	private String createBranch; // 107
	private String editBranch; // 108
	private String viewBranch; // 109
	private String deleteBranch; // 110

	private String userManagement; // 111
	private String createUser; // 112
	private String editUser; // 113
	private String deleteUser; // 114

	private String roleMaster; // 115
	private String createRole; // 116
	private String editRole; // 117
	private String deleteRole; // 118

	private String userRoles; // 119
	private String updateUserRoles; // 120

	private String importExport; // 121
	private String deviceControl; // 122
	private String serverBackup; // 123

	private String taskJe; // 200
	private String taskAee; // 201
	private String taskEe; // 202
	private String taskCommissioner; // 203
	private String taskWorkcomplete; // 204
	private String taskJobcard; // 205
	private String taskComplainthistory; // 206

	private String assetManagement; // 300

	private String stockAvailable; // 400
	private String inwardMatierals; // 401
	private String inwardTools; // 402
	private String outwardMaterials; // 403
	private String outwardSpares; // 404
	private String outwardTools; // 405
	private String matieralsReturn; // 406
	private String sparesReturn; // 407
	private String toolsReturn; // 408
	private String rejectDamageReturn; // 409
	private String stockApprovals; // 410

	private String generateWorkOrder; // 500
	private String holdWorkOrder; // 501
	private String cancelWorkOrder; // 502
	private String workEstmation; // 503

	private String employeeDashBoard; // 600
	private String employeeMaster; // 601
	private String attendance; // 602
	private String salary; // 603
	private String inspection; // 604
	private String leave; // 605

	private String consMasterDetails; // 700
	private String consTransDetails; // 701
	private String meterDetails; // 702
	private String wardsConsumption; // 703
	private String dmaPayment; // 704
	private String dcb; // 705

	private String pumpDashboard; // 800
	private String pumpMaintenance; // 801
	private String pumpMaintenanceIndent; // 802
	private String pumpMaintenanceUpdate; // 803
	private String pumpMaintenanceInspection; // 804
	private String pumpMaintenanceHistory; // 805

	private String pipeDashboard; // 900
	private String pipeMaintenance; // 901
	private String pipeMaintenanceIndent; // 902
	private String pipeMaintenanceUpdate; // 903
	private String pipeMaintenanceInspection; // 904
	private String pipeMaintenanceHistory; // 905

	private String vehicleTrackingMangement; // 1100
	private String vehicleHistory; // 1101
	private String vehicleFuelManagement; // 1102
	private String vehicleIndex; // 1103
	private String vehicleManagementIndex; // 1104
	private String vehicleInspection; // 1105
	private String vehicleWorkOrderView; // 1106

	private String leakageDashboard; // 1200
	private String leakageIndent; // 1201
	private String leakageMaintenanceUpdate; // 1202
	private String leakageWorkInspection; // 1203
	private String leakageLeakageHistory; // 1204

	private String testingMaintenance; // 1300
	private String meterReplace; // 1301

	private String handPumpIndex; // 1400
	private String handPumpIndent; // 1401
	private String handPumpWorkOrder; // 1402
	private String handPumpWorkUpdate; // 1403
	private String handPumpHandPumpInspection; // 1404
	private String handPumpMaintenanceHistory; // 1405

	private String borewellIndex; // 1500
	private String borewellIndent; // 1501
	private String borewellWorkOrder; // 1503
	private String borewellWorkUpdate; // 1504
	private String borewellHandPumpInspection; // 1505
	private String borewellMaintenanceHistory; // 1506

	private String glsrohtIndex; // 1600
	private String glsrohtIndent; // 1601
	private String glsrohtWorkOrder; // 1602
	private String glsrohtWorkUpdate; // 1603
	private String glsrohtHandPumpInspection; // 1604
	private String glsrohtMaintenanceHistory; // 1605

	private String levelsControlIndex; // 1700
	private String levelsControlIndent; // 1701
	private String levelsControlWorkOrder; // 1702
	private String levelsControlWorkUpdate; // 1703
	private String levelsControlInspection; // 1704
	private String levelsControlHistory; // 1705

	private String waterStorageGlsr; // 1800
	private String waterStorageoht; // 1801

	private String contactManagement; // 1900

	private String approvals; // 2100

	private String dailyReport; // 2200
	private String monthlyReport; // 2201

	private String masters; // 2300
}
