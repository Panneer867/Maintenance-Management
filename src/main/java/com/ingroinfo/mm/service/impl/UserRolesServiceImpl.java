package com.ingroinfo.mm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dto.UserRoleIdDto;
import com.ingroinfo.mm.dto.UserRolesDto;
import com.ingroinfo.mm.service.UserRolesService;

@Service
public class UserRolesServiceImpl implements UserRolesService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private void createRole(Long roleId, String PageNumber) {
		try {
			long pageNo = Long.parseLong(PageNumber);

			String sql = "SELECT * FROM MM_ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " AND PAGE_NO =" + pageNo;

			int count = jdbcTemplate.update(sql);

			if (count == 0) {

				String sql1 = "INSERT INTO MM_ROLE_PRIVILEGES (ROLE_ID,PAGE_NO) VALUES (?, ?)";
				jdbcTemplate.update(sql1, roleId, pageNo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteRole(Long roleId, String PageNumber) {
		try {
			String no[] = PageNumber.split("N");
			long pageNo = Long.parseLong(no[0]);

			String sql = "SELECT * FROM MM_ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " AND PAGE_NO =" + pageNo;

			int count = jdbcTemplate.update(sql);

			if (count > 0) {
				String sql2 = "DELETE FROM MM_ROLE_PRIVILEGES WHERE ROLE_ID= ? AND PAGE_NO = ?";
				jdbcTemplate.update(sql2, roleId, pageNo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void AssignRoles(UserRolesDto dto) {
		/*
		 * try { for (Field field : dto.getClass().getDeclaredFields()) {
		 * field.setAccessible(true); Object value = field.get(dto);
		 * System.out.println(String.format("%-20s : %-5s", field.getName(), value)); }
		 * } catch (IllegalAccessException e) { e.printStackTrace(); }
		 */

		Long roleId = dto.getRoleId();

		/* Admin Pages */

		if (dto.getAdminpage().contains("N")) {
			deleteRole(roleId, dto.getAdminpage());
		} else {
			createRole(roleId, dto.getAdminpage());
		}
		if (dto.getCompanyManagement().contains("N")) {
			deleteRole(roleId, dto.getCompanyManagement());
		} else {
			createRole(roleId, dto.getCompanyManagement());
		}
		if (dto.getCreateCompany().contains("N")) {
			deleteRole(roleId, dto.getCreateCompany());
		} else {
			createRole(roleId, dto.getCreateCompany());
		}
		if (dto.getEditCompany().contains("N")) {
			deleteRole(roleId, dto.getEditCompany());
		} else {
			createRole(roleId, dto.getEditCompany());
		}
		if (dto.getViewCompany().contains("N")) {
			deleteRole(roleId, dto.getViewCompany());
		} else {
			createRole(roleId, dto.getViewCompany());
		}
		if (dto.getDeleteCompany().contains("N")) {
			deleteRole(roleId, dto.getDeleteCompany());
		} else {
			createRole(roleId, dto.getDeleteCompany());
		}
		if (dto.getBranchManagement().contains("N")) {
			deleteRole(roleId, dto.getBranchManagement());
		} else {
			createRole(roleId, dto.getBranchManagement());
		}
		if (dto.getCreateBranch().contains("N")) {
			deleteRole(roleId, dto.getCreateBranch());
		} else {
			createRole(roleId, dto.getCreateBranch());
		}
		if (dto.getEditBranch().contains("N")) {
			deleteRole(roleId, dto.getEditBranch());
		} else {
			createRole(roleId, dto.getEditBranch());
		}
		if (dto.getViewBranch().contains("N")) {
			deleteRole(roleId, dto.getViewBranch());
		} else {
			createRole(roleId, dto.getViewBranch());
		}
		if (dto.getDeleteBranch().contains("N")) {
			deleteRole(roleId, dto.getDeleteBranch());
		} else {
			createRole(roleId, dto.getDeleteBranch());
		}
		if (dto.getUserManagement().contains("N")) {
			deleteRole(roleId, dto.getUserManagement());
		} else {
			createRole(roleId, dto.getUserManagement());
		}
		if (dto.getCreateUser().contains("N")) {
			deleteRole(roleId, dto.getCreateUser());
		} else {
			createRole(roleId, dto.getCreateUser());
		}
		if (dto.getEditUser().contains("N")) {
			deleteRole(roleId, dto.getEditUser());
		} else {
			createRole(roleId, dto.getEditUser());
		}
		if (dto.getDeleteUser().contains("N")) {
			deleteRole(roleId, dto.getDeleteUser());
		} else {
			createRole(roleId, dto.getDeleteUser());
		}
		if (dto.getRoleMaster().contains("N")) {
			deleteRole(roleId, dto.getRoleMaster());
		} else {
			createRole(roleId, dto.getRoleMaster());
		}
		if (dto.getCreateRole().contains("N")) {
			deleteRole(roleId, dto.getCreateRole());
		} else {
			createRole(roleId, dto.getCreateRole());
		}
		if (dto.getEditRole().contains("N")) {
			deleteRole(roleId, dto.getEditRole());
		} else {
			createRole(roleId, dto.getEditRole());
		}
		if (dto.getDeleteRole().contains("N")) {
			deleteRole(roleId, dto.getDeleteRole());
		} else {
			createRole(roleId, dto.getDeleteRole());
		}
		if (dto.getUserRoles().contains("N")) {
			deleteRole(roleId, dto.getUserRoles());
		} else {
			createRole(roleId, dto.getUserRoles());
		}
		if (dto.getUpdateUserRoles().contains("N")) {
			deleteRole(roleId, dto.getUpdateUserRoles());
		} else {
			createRole(roleId, dto.getUpdateUserRoles());
		}
		if (dto.getImportExport().contains("N")) {
			deleteRole(roleId, dto.getImportExport());
		} else {
			createRole(roleId, dto.getImportExport());
		}
		if (dto.getDeviceControl().contains("N")) {
			deleteRole(roleId, dto.getDeviceControl());
		} else {
			createRole(roleId, dto.getDeviceControl());
		}
		if (dto.getServerBackup().contains("N")) {
			deleteRole(roleId, dto.getServerBackup());
		} else {
			createRole(roleId, dto.getServerBackup());
		}

		/* Task Management */

		if (dto.getTaskJe().contains("N")) {
			deleteRole(roleId, dto.getTaskJe());
		} else {
			createRole(roleId, dto.getTaskJe());
		}
		if (dto.getTaskAee().contains("N")) {
			deleteRole(roleId, dto.getTaskAee());
		} else {
			createRole(roleId, dto.getTaskAee());
		}
		if (dto.getTaskEe().contains("N")) {
			deleteRole(roleId, dto.getTaskEe());
		} else {
			createRole(roleId, dto.getTaskEe());
		}
		if (dto.getTaskCommissioner().contains("N")) {
			deleteRole(roleId, dto.getTaskCommissioner());
		} else {
			createRole(roleId, dto.getTaskCommissioner());
		}
		if (dto.getTaskWorkcomplete().contains("N")) {
			deleteRole(roleId, dto.getTaskWorkcomplete());
		} else {
			createRole(roleId, dto.getTaskWorkcomplete());
		}
		if (dto.getTaskJobcard().contains("N")) {
			deleteRole(roleId, dto.getTaskJobcard());
		} else {
			createRole(roleId, dto.getTaskJobcard());
		}
		if (dto.getTaskComplainthistory().contains("N")) {
			deleteRole(roleId, dto.getTaskComplainthistory());
		} else {
			createRole(roleId, dto.getTaskComplainthistory());
		}

		/* Asset Management */

		if (dto.getAssetManagement().contains("N")) {
			deleteRole(roleId, dto.getAssetManagement());
		} else {
			createRole(roleId, dto.getAssetManagement());
		}

		/* Stock Management */

		if (dto.getStockAvailable().contains("N")) {
			deleteRole(roleId, dto.getStockAvailable());
		} else {
			createRole(roleId, dto.getStockAvailable());
		}
		if (dto.getInwardMatierals().contains("N")) {
			deleteRole(roleId, dto.getInwardMatierals());
		} else {
			createRole(roleId, dto.getInwardMatierals());
		}
		if (dto.getInwardSpares().contains("N")) {
			deleteRole(roleId, dto.getInwardSpares());
		} else {
			createRole(roleId, dto.getInwardSpares());
		}
		if (dto.getInwardTools().contains("N")) {
			deleteRole(roleId, dto.getInwardTools());
		} else {
			createRole(roleId, dto.getInwardTools());
		}
		if (dto.getOutwardMaterials().contains("N")) {
			deleteRole(roleId, dto.getOutwardMaterials());
		} else {
			createRole(roleId, dto.getOutwardMaterials());
		}
		if (dto.getOutwardSpares().contains("N")) {
			deleteRole(roleId, dto.getOutwardSpares());
		} else {
			createRole(roleId, dto.getOutwardSpares());
		}
		if (dto.getOutwardTools().contains("N")) {
			deleteRole(roleId, dto.getOutwardTools());
		} else {
			createRole(roleId, dto.getOutwardTools());
		}
		if (dto.getMatieralsReturn().contains("N")) {
			deleteRole(roleId, dto.getMatieralsReturn());
		} else {
			createRole(roleId, dto.getMatieralsReturn());
		}
		if (dto.getSparesReturn().contains("N")) {
			deleteRole(roleId, dto.getSparesReturn());
		} else {
			createRole(roleId, dto.getSparesReturn());
		}
		if (dto.getToolsReturn().contains("N")) {
			deleteRole(roleId, dto.getToolsReturn());
		} else {
			createRole(roleId, dto.getToolsReturn());
		}
		if (dto.getRejectDamageReturn().contains("N")) {
			deleteRole(roleId, dto.getRejectDamageReturn());
		} else {
			createRole(roleId, dto.getRejectDamageReturn());
		}
		if (dto.getStockApprovals().contains("N")) {
			deleteRole(roleId, dto.getStockApprovals());
		} else {
			createRole(roleId, dto.getStockApprovals());
		}

		/* Work Order */

		if (dto.getGenerateWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getGenerateWorkOrder());
		} else {
			createRole(roleId, dto.getGenerateWorkOrder());
		}
		if (dto.getHoldWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getHoldWorkOrder());
		} else {
			createRole(roleId, dto.getHoldWorkOrder());
		}
		if (dto.getCancelWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getCancelWorkOrder());
		} else {
			createRole(roleId, dto.getCancelWorkOrder());
		}
		if (dto.getWorkEstmation().contains("N")) {
			deleteRole(roleId, dto.getWorkEstmation());
		} else {
			createRole(roleId, dto.getWorkEstmation());
		}

		/* Employee Management */

		if (dto.getEmployeeDashBoard().contains("N")) {
			deleteRole(roleId, dto.getEmployeeDashBoard());
		} else {
			createRole(roleId, dto.getEmployeeDashBoard());
		}
		if (dto.getEmployeeMaster().contains("N")) {
			deleteRole(roleId, dto.getEmployeeMaster());
		} else {
			createRole(roleId, dto.getEmployeeMaster());
		}
		if (dto.getAttendance().contains("N")) {
			deleteRole(roleId, dto.getAttendance());
		} else {
			createRole(roleId, dto.getAttendance());
		}
		if (dto.getSalary().contains("N")) {
			deleteRole(roleId, dto.getSalary());
		} else {
			createRole(roleId, dto.getSalary());
		}
		if (dto.getInspection().contains("N")) {
			deleteRole(roleId, dto.getInspection());
		} else {
			createRole(roleId, dto.getInspection());
		}
		if (dto.getLeave().contains("N")) {
			deleteRole(roleId, dto.getLeave());
		} else {
			createRole(roleId, dto.getLeave());
		}

		/* Billing Management */

		if (dto.getConsMasterDetails().contains("N")) {
			deleteRole(roleId, dto.getConsMasterDetails());
		} else {
			createRole(roleId, dto.getConsMasterDetails());
		}
		if (dto.getConsTransDetails().contains("N")) {
			deleteRole(roleId, dto.getConsTransDetails());
		} else {
			createRole(roleId, dto.getConsTransDetails());
		}
		if (dto.getMeterDetails().contains("N")) {
			deleteRole(roleId, dto.getMeterDetails());
		} else {
			createRole(roleId, dto.getMeterDetails());
		}
		/*
		 * if (dto.getWardsConsumption().contains("N")) { deleteRole(roleId,
		 * dto.getWardsConsumption()); } else { createRole(roleId,
		 * dto.getWardsConsumption()); } if (dto.getDmaPayment().contains("N")) {
		 * deleteRole(roleId, dto.getDmaPayment()); } else { createRole(roleId,
		 * dto.getDmaPayment()); } if (dto.getDcb().contains("N")) { deleteRole(roleId,
		 * dto.getDcb()); } else { createRole(roleId, dto.getDcb()); }
		 */

		/* Pump Management */

		if (dto.getPumpDashboard().contains("N")) {
			deleteRole(roleId, dto.getPumpDashboard());
		} else {
			createRole(roleId, dto.getPumpDashboard());
		}
		if (dto.getPumpMaintenance().contains("N")) {
			deleteRole(roleId, dto.getPumpMaintenance());
		} else {
			createRole(roleId, dto.getPumpMaintenance());
		} /*
			 * if (dto.getPumpMaintenanceIndent().contains("N")) { deleteRole(roleId,
			 * dto.getPumpMaintenanceIndent()); } else { createRole(roleId,
			 * dto.getPumpMaintenanceIndent()); }
			 * 
			 * if (dto.getPumpMaintenanceUpdate().contains("N")) { deleteRole(roleId,
			 * dto.getPumpMaintenanceUpdate()); } else { createRole(roleId,
			 * dto.getPumpMaintenanceUpdate()); } if
			 * (dto.getPumpMaintenanceInspection().contains("N")) { deleteRole(roleId,
			 * dto.getPumpMaintenanceInspection()); } else { createRole(roleId,
			 * dto.getPumpMaintenanceInspection()); } if
			 * (dto.getPumpMaintenanceHistory().contains("N")) { deleteRole(roleId,
			 * dto.getPumpMaintenanceHistory()); } else { createRole(roleId,
			 * dto.getPumpMaintenanceHistory()); }
			 */

		/* Pipe Management */

		if (dto.getPipeDashboard().contains("N")) {
			deleteRole(roleId, dto.getPipeDashboard());
		} else {
			createRole(roleId, dto.getPipeDashboard());
		}
		if (dto.getPipeMaintenanceIndent().contains("N")) {
			deleteRole(roleId, dto.getPipeMaintenanceIndent());
		} else {
			createRole(roleId, dto.getPipeMaintenanceIndent());
		}
		if (dto.getPipeMaintenanceUpdate().contains("N")) {
			deleteRole(roleId, dto.getPipeMaintenanceUpdate());
		} else {
			createRole(roleId, dto.getPipeMaintenanceUpdate());
		}
		if (dto.getPipeMaintenanceInspection().contains("N")) {
			deleteRole(roleId, dto.getPipeMaintenanceInspection());
		} else {
			createRole(roleId, dto.getPipeMaintenanceInspection());
		}
		if (dto.getPipeMaintenanceHistory().contains("N")) {
			deleteRole(roleId, dto.getPipeMaintenanceHistory());
		} else {
			createRole(roleId, dto.getPipeMaintenanceHistory());
		}

		/* Vehicle Management */

		if (dto.getVehicleTrackingMangement().contains("N")) {
			deleteRole(roleId, dto.getVehicleTrackingMangement());
		} else {
			createRole(roleId, dto.getVehicleTrackingMangement());
		}
		if (dto.getVehicleHistory().contains("N")) {
			deleteRole(roleId, dto.getVehicleHistory());
		} else {
			createRole(roleId, dto.getVehicleHistory());
		}
		/*
		 * if (dto.getVehicleFuelManagement().contains("N")) { deleteRole(roleId,
		 * dto.getVehicleFuelManagement()); } else { createRole(roleId,
		 * dto.getVehicleFuelManagement()); }
		 */
		if (dto.getVehicleIndex().contains("N")) {
			deleteRole(roleId, dto.getVehicleIndex());
		} else {
			createRole(roleId, dto.getVehicleIndex());
		}
		if (dto.getVehicleManagementIndex().contains("N")) {
			deleteRole(roleId, dto.getVehicleManagementIndex());
		} else {
			createRole(roleId, dto.getVehicleManagementIndex());
		}
		if (dto.getVehicleInspection().contains("N")) {
			deleteRole(roleId, dto.getVehicleInspection());
		} else {
			createRole(roleId, dto.getVehicleInspection());
		}
		if (dto.getVehicleWorkOrderView().contains("N")) {
			deleteRole(roleId, dto.getVehicleWorkOrderView());
		} else {
			createRole(roleId, dto.getVehicleWorkOrderView());
		}

		/* Leakage Management */

		if (dto.getLeakageDashboard().contains("N")) {
			deleteRole(roleId, dto.getLeakageDashboard());
		} else {
			createRole(roleId, dto.getLeakageDashboard());
		}
		if (dto.getLeakageIndent().contains("N")) {
			deleteRole(roleId, dto.getLeakageIndent());
		} else {
			createRole(roleId, dto.getLeakageIndent());
		}
		if (dto.getLeakageMaintenanceUpdate().contains("N")) {
			deleteRole(roleId, dto.getLeakageMaintenanceUpdate());
		} else {
			createRole(roleId, dto.getLeakageMaintenanceUpdate());
		}
		if (dto.getLeakageWorkInspection().contains("N")) {
			deleteRole(roleId, dto.getLeakageWorkInspection());
		} else {
			createRole(roleId, dto.getLeakageWorkInspection());
		}
		if (dto.getLeakageLeakageHistory().contains("N")) {
			deleteRole(roleId, dto.getLeakageLeakageHistory());
		} else {
			createRole(roleId, dto.getLeakageLeakageHistory());
		}

		/* Meter Management */

		/*
		 * if (dto.getTestingMaintenance().contains("N")) { deleteRole(roleId,
		 * dto.getTestingMaintenance()); } else { createRole(roleId,
		 * dto.getTestingMaintenance()); }
		 */
		if (dto.getMeterReplace().contains("N")) {
			deleteRole(roleId, dto.getMeterReplace());
		} else {
			createRole(roleId, dto.getMeterReplace());
		}

		/* Hand Pump Management */

		if (dto.getHandPumpIndex().contains("N")) {
			deleteRole(roleId, dto.getHandPumpIndex());
		} else {
			createRole(roleId, dto.getHandPumpIndex());
		}
		if (dto.getHandPumpIndent().contains("N")) {
			deleteRole(roleId, dto.getHandPumpIndent());
		} else {
			createRole(roleId, dto.getHandPumpIndent());
		}
		if (dto.getHandPumpWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getHandPumpWorkOrder());
		} else {
			createRole(roleId, dto.getHandPumpWorkOrder());
		}
		if (dto.getHandPumpWorkUpdate().contains("N")) {
			deleteRole(roleId, dto.getHandPumpWorkUpdate());
		} else {
			createRole(roleId, dto.getHandPumpWorkUpdate());
		}
		if (dto.getHandPumpHandPumpInspection().contains("N")) {
			deleteRole(roleId, dto.getHandPumpHandPumpInspection());
		} else {
			createRole(roleId, dto.getHandPumpHandPumpInspection());
		}
		if (dto.getHandPumpMaintenanceHistory().contains("N")) {
			deleteRole(roleId, dto.getHandPumpMaintenanceHistory());
		} else {
			createRole(roleId, dto.getHandPumpMaintenanceHistory());
		}

		/* Borewell Management */

		if (dto.getBorewellIndex().contains("N")) {
			deleteRole(roleId, dto.getBorewellIndex());
		} else {
			createRole(roleId, dto.getBorewellIndex());
		}
		if (dto.getBorewellIndent().contains("N")) {
			deleteRole(roleId, dto.getBorewellIndent());
		} else {
			createRole(roleId, dto.getBorewellIndent());
		}
		if (dto.getBorewellWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getBorewellWorkOrder());
		} else {
			createRole(roleId, dto.getBorewellWorkOrder());
		}
		if (dto.getBorewellWorkUpdate().contains("N")) {
			deleteRole(roleId, dto.getBorewellWorkUpdate());
		} else {
			createRole(roleId, dto.getBorewellWorkUpdate());
		}
		if (dto.getBorewellHandPumpInspection().contains("N")) {
			deleteRole(roleId, dto.getBorewellHandPumpInspection());
		} else {
			createRole(roleId, dto.getBorewellHandPumpInspection());
		}
		if (dto.getBorewellMaintenanceHistory().contains("N")) {
			deleteRole(roleId, dto.getBorewellMaintenanceHistory());
		} else {
			createRole(roleId, dto.getBorewellMaintenanceHistory());
		}

		/* glsroht Management */

		if (dto.getGlsrohtIndex().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtIndex());
		} else {
			createRole(roleId, dto.getGlsrohtIndex());
		}
		if (dto.getGlsrohtIndent().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtIndent());
		} else {
			createRole(roleId, dto.getGlsrohtIndent());
		}
		if (dto.getGlsrohtWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtWorkOrder());
		} else {
			createRole(roleId, dto.getGlsrohtWorkOrder());
		}
		if (dto.getGlsrohtWorkUpdate().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtWorkUpdate());
		} else {
			createRole(roleId, dto.getGlsrohtWorkUpdate());
		}
		if (dto.getGlsrohtHandPumpInspection().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtHandPumpInspection());
		} else {
			createRole(roleId, dto.getGlsrohtHandPumpInspection());
		}
		if (dto.getGlsrohtMaintenanceHistory().contains("N")) {
			deleteRole(roleId, dto.getGlsrohtMaintenanceHistory());
		} else {
			createRole(roleId, dto.getGlsrohtMaintenanceHistory());
		}

		/* Levels Control */

		if (dto.getLevelsControlIndex().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlIndex());
		} else {
			createRole(roleId, dto.getLevelsControlIndex());
		}
		if (dto.getLevelsControlIndent().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlIndent());
		} else {
			createRole(roleId, dto.getLevelsControlIndent());
		}
		if (dto.getLevelsControlWorkOrder().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlWorkOrder());
		} else {
			createRole(roleId, dto.getLevelsControlWorkOrder());
		}
		if (dto.getLevelsControlWorkUpdate().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlWorkUpdate());
		} else {
			createRole(roleId, dto.getLevelsControlWorkUpdate());
		}
		if (dto.getLevelsControlInspection().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlInspection());
		} else {
			createRole(roleId, dto.getLevelsControlInspection());
		}
		if (dto.getLevelsControlHistory().contains("N")) {
			deleteRole(roleId, dto.getLevelsControlHistory());
		} else {
			createRole(roleId, dto.getLevelsControlHistory());
		}

		/* Water Storage */

		/*
		 * if (dto.getWaterStorageGlsr().contains("N")) { deleteRole(roleId,
		 * dto.getWaterStorageGlsr()); } else { createRole(roleId,
		 * dto.getWaterStorageGlsr()); } if (dto.getWaterStorageoht().contains("N")) {
		 * deleteRole(roleId, dto.getWaterStorageoht()); } else { createRole(roleId,
		 * dto.getWaterStorageoht()); }
		 */

		/* Contact Management */

		if (dto.getContactManagement().contains("N")) {
			deleteRole(roleId, dto.getContactManagement());
		} else {
			createRole(roleId, dto.getContactManagement());
		}

		/* Approvals */

		if (dto.getApprovals().contains("N")) {
			deleteRole(roleId, dto.getApprovals());
		} else {
			createRole(roleId, dto.getApprovals());
		}

		/* MIS Reports */

		if (dto.getDailyReport().contains("N")) {
			deleteRole(roleId, dto.getDailyReport());
		} else {
			createRole(roleId, dto.getDailyReport());
		}
		if (dto.getMonthlyReport().contains("N")) {
			deleteRole(roleId, dto.getMonthlyReport());
		} else {
			createRole(roleId, dto.getMonthlyReport());
		}

		/* Masters */

		if (dto.getMasters().contains("N")) {
			deleteRole(roleId, dto.getMasters());
		} else {
			createRole(roleId, dto.getMasters());
		}

	}

	@Override
	public UserRolesDto getUserRoles(Long roleId) {
		UserRolesDto pages = new UserRolesDto();

		try {
			String sql = "SELECT * FROM MM_ROLE_PRIVILEGES WHERE ROLE_ID = " + roleId + " ORDER BY PAGE_NO";
			int count = jdbcTemplate.update(sql);

			if (count > 0) {
				List<UserRoleIdDto> userRoles = jdbcTemplate.query(sql,
						BeanPropertyRowMapper.newInstance(UserRoleIdDto.class));

				for (int i = 0; i < userRoles.size(); i++) {
					int pageNo = userRoles.get(i).getPageNo();

					/* Admin Pages */

					if (pageNo == 100) {
						pages.setAdminpage(String.valueOf(pageNo));
					} else if (pageNo == 101) {
						pages.setCompanyManagement(String.valueOf(pageNo));
					} else if (pageNo == 102) {
						pages.setCreateCompany(String.valueOf(pageNo));
					} else if (pageNo == 103) {
						pages.setEditCompany(String.valueOf(pageNo));
					} else if (pageNo == 104) {
						pages.setViewCompany(String.valueOf(pageNo));
					} else if (pageNo == 105) {
						pages.setDeleteCompany(String.valueOf(pageNo));
					} else if (pageNo == 106) {
						pages.setBranchManagement(String.valueOf(pageNo));
					} else if (pageNo == 107) {
						pages.setCreateBranch(String.valueOf(pageNo));
					} else if (pageNo == 108) {
						pages.setEditBranch(String.valueOf(pageNo));
					} else if (pageNo == 109) {
						pages.setViewBranch(String.valueOf(pageNo));
					} else if (pageNo == 110) {
						pages.setDeleteBranch(String.valueOf(pageNo));
					} else if (pageNo == 111) {
						pages.setUserManagement(String.valueOf(pageNo));
					} else if (pageNo == 112) {
						pages.setCreateUser(String.valueOf(pageNo));
					} else if (pageNo == 113) {
						pages.setEditUser(String.valueOf(pageNo));
					} else if (pageNo == 114) {
						pages.setDeleteUser(String.valueOf(pageNo));
					} else if (pageNo == 115) {
						pages.setRoleMaster(String.valueOf(pageNo));
					} else if (pageNo == 116) {
						pages.setCreateRole(String.valueOf(pageNo));
					} else if (pageNo == 117) {
						pages.setEditRole(String.valueOf(pageNo));
					} else if (pageNo == 118) {
						pages.setDeleteRole(String.valueOf(pageNo));
					} else if (pageNo == 119) {
						pages.setUserRoles(String.valueOf(pageNo));
					} else if (pageNo == 120) {
						pages.setUpdateUserRoles(String.valueOf(pageNo));
					} else if (pageNo == 121) {
						pages.setImportExport(String.valueOf(pageNo));
					} else if (pageNo == 122) {
						pages.setDeviceControl(String.valueOf(pageNo));
					} else if (pageNo == 123) {
						pages.setServerBackup(String.valueOf(pageNo));
					}

					/* Task Pages */

					else if (pageNo == 200) {
						pages.setTaskJe(String.valueOf(pageNo));
					} else if (pageNo == 201) {
						pages.setTaskAee(String.valueOf(pageNo));
					} else if (pageNo == 202) {
						pages.setTaskEe(String.valueOf(pageNo));
					} else if (pageNo == 203) {
						pages.setTaskCommissioner(String.valueOf(pageNo));
					} else if (pageNo == 204) {
						pages.setTaskWorkcomplete(String.valueOf(pageNo));
					} else if (pageNo == 205) {
						pages.setTaskJobcard(String.valueOf(pageNo));
					} else if (pageNo == 206) {
						pages.setTaskComplainthistory(String.valueOf(pageNo));
					}

					/* Asset Management */

					else if (pageNo == 300) {
						pages.setAssetManagement(String.valueOf(pageNo));
					}

					/* Stock Management */

					else if (pageNo == 400) {
						pages.setStockAvailable(String.valueOf(pageNo));
					} else if (pageNo == 401) {
						pages.setInwardMatierals(String.valueOf(pageNo));
					} else if (pageNo == 402) {
						pages.setInwardSpares(String.valueOf(pageNo));
					} else if (pageNo == 403) {
						pages.setInwardTools(String.valueOf(pageNo));
					} else if (pageNo == 404) {
						pages.setOutwardMaterials(String.valueOf(pageNo));
					} else if (pageNo == 405) {
						pages.setOutwardSpares(String.valueOf(pageNo));
					} else if (pageNo == 406) {
						pages.setOutwardTools(String.valueOf(pageNo));
					} else if (pageNo == 407) {
						pages.setMatieralsReturn(String.valueOf(pageNo));
					} else if (pageNo == 408) {
						pages.setSparesReturn(String.valueOf(pageNo));
					} else if (pageNo == 409) {
						pages.setToolsReturn(String.valueOf(pageNo));
					} else if (pageNo == 410) {
						pages.setRejectDamageReturn(String.valueOf(pageNo));
					} else if (pageNo == 411) {
						pages.setStockApprovals(String.valueOf(pageNo));
					}

					/* Work Order */

					else if (pageNo == 500) {
						pages.setGenerateWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 501) {
						pages.setHoldWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 502) {
						pages.setCancelWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 503) {
						pages.setWorkEstmation(String.valueOf(pageNo));
					}

					/* Employee Management */

					else if (pageNo == 600) {
						pages.setEmployeeDashBoard(String.valueOf(pageNo));
					} else if (pageNo == 601) {
						pages.setEmployeeMaster(String.valueOf(pageNo));
					} else if (pageNo == 602) {
						pages.setAttendance(String.valueOf(pageNo));
					} else if (pageNo == 603) {
						pages.setSalary(String.valueOf(pageNo));
					} else if (pageNo == 604) {
						pages.setInspection(String.valueOf(pageNo));
					} else if (pageNo == 605) {
						pages.setLeave(String.valueOf(pageNo));
					}

					/* Billing Management */

					else if (pageNo == 700) {
						pages.setConsMasterDetails(String.valueOf(pageNo));
					} else if (pageNo == 701) {
						pages.setConsTransDetails(String.valueOf(pageNo));
					} else if (pageNo == 702) {
						pages.setMeterDetails(String.valueOf(pageNo));
					} /*
						 * else if (pageNo == 703) { pages.setWardsConsumption(String.valueOf(pageNo));
						 * } else if (pageNo == 704) { pages.setDmaPayment(String.valueOf(pageNo)); }
						 * else if (pageNo == 705) { pages.setDcb(String.valueOf(pageNo)); }
						 */

					/* Pump Management */

					else if (pageNo == 800) {
						pages.setPumpDashboard(String.valueOf(pageNo));
					} else if (pageNo == 801) {
						pages.setPumpMaintenance(String.valueOf(pageNo));
					} /*
						 * else if (pageNo == 802) {
						 * pages.setPumpMaintenanceIndent(String.valueOf(pageNo)); } else if (pageNo ==
						 * 803) { pages.setPumpMaintenanceUpdate(String.valueOf(pageNo)); } else if
						 * (pageNo == 804) { pages.setPumpMaintenanceInspection(String.valueOf(pageNo));
						 * } else if (pageNo == 805) {
						 * pages.setPumpMaintenanceHistory(String.valueOf(pageNo)); }
						 */

					/* Pipe Management */

					else if (pageNo == 900) {
						pages.setPipeDashboard(String.valueOf(pageNo));
					} else if (pageNo == 901) {
						pages.setPipeMaintenanceIndent(String.valueOf(pageNo));
					} else if (pageNo == 902) {
						pages.setPipeMaintenanceUpdate(String.valueOf(pageNo));
					} else if (pageNo == 903) {
						pages.setPipeMaintenanceInspection(String.valueOf(pageNo));
					} else if (pageNo == 904) {
						pages.setPipeMaintenanceHistory(String.valueOf(pageNo));
					}

					/* Vehicle Management */

					else if (pageNo == 1100) {
						pages.setVehicleTrackingMangement(String.valueOf(pageNo));
					} else if (pageNo == 1101) {
						pages.setVehicleHistory(String.valueOf(pageNo));
					} /*
						 * else if (pageNo == 1102) {
						 * pages.setVehicleFuelManagement(String.valueOf(pageNo)); }
						 */ else if (pageNo == 1103) {
						pages.setVehicleIndex(String.valueOf(pageNo));
					} else if (pageNo == 1104) {
						pages.setVehicleManagementIndex(String.valueOf(pageNo));
					} else if (pageNo == 1105) {
						pages.setVehicleInspection(String.valueOf(pageNo));
					} else if (pageNo == 1106) {
						pages.setVehicleWorkOrderView(String.valueOf(pageNo));
					}

					/* Leakage Management */

					else if (pageNo == 1200) {
						pages.setLeakageDashboard(String.valueOf(pageNo));
					} else if (pageNo == 1201) {
						pages.setLeakageIndent(String.valueOf(pageNo));
					} else if (pageNo == 1202) {
						pages.setLeakageMaintenanceUpdate(String.valueOf(pageNo));
					} else if (pageNo == 1203) {
						pages.setLeakageWorkInspection(String.valueOf(pageNo));
					} else if (pageNo == 1204) {
						pages.setLeakageLeakageHistory(String.valueOf(pageNo));
					}

					/* Meter Management */

					/*
					 * else if (pageNo == 1300) {
					 * pages.setTestingMaintenance(String.valueOf(pageNo)); }
					 */else if (pageNo == 1301) {
						pages.setMeterReplace(String.valueOf(pageNo));
					}

					/* Hand Pump Management */

					else if (pageNo == 1400) {
						pages.setHandPumpIndex(String.valueOf(pageNo));
					} else if (pageNo == 1401) {
						pages.setHandPumpIndent(String.valueOf(pageNo));
					} else if (pageNo == 1402) {
						pages.setHandPumpWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 1403) {
						pages.setHandPumpWorkUpdate(String.valueOf(pageNo));
					} else if (pageNo == 1404) {
						pages.setHandPumpHandPumpInspection(String.valueOf(pageNo));
					} else if (pageNo == 1405) {
						pages.setHandPumpMaintenanceHistory(String.valueOf(pageNo));
					}

					/* Borewell Management */

					else if (pageNo == 1500) {
						pages.setBorewellIndex(String.valueOf(pageNo));
					} else if (pageNo == 1501) {
						pages.setBorewellIndent(String.valueOf(pageNo));
					} else if (pageNo == 1502) {
						pages.setBorewellWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 1503) {
						pages.setBorewellWorkUpdate(String.valueOf(pageNo));
					} else if (pageNo == 1504) {
						pages.setBorewellHandPumpInspection(String.valueOf(pageNo));
					} else if (pageNo == 1505) {
						pages.setBorewellMaintenanceHistory(String.valueOf(pageNo));
					}

					/* glsroht Management */

					else if (pageNo == 1600) {
						pages.setGlsrohtIndex(String.valueOf(pageNo));
					} else if (pageNo == 1601) {
						pages.setGlsrohtIndent(String.valueOf(pageNo));
					} else if (pageNo == 1602) {
						pages.setGlsrohtWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 1603) {
						pages.setGlsrohtWorkUpdate(String.valueOf(pageNo));
					} else if (pageNo == 1604) {
						pages.setGlsrohtHandPumpInspection(String.valueOf(pageNo));
					} else if (pageNo == 1605) {
						pages.setGlsrohtMaintenanceHistory(String.valueOf(pageNo));
					}

					/* Levels Control */

					else if (pageNo == 1700) {
						pages.setLevelsControlIndex(String.valueOf(pageNo));
					} else if (pageNo == 1701) {
						pages.setLevelsControlIndent(String.valueOf(pageNo));
					} else if (pageNo == 1702) {
						pages.setLevelsControlWorkOrder(String.valueOf(pageNo));
					} else if (pageNo == 1703) {
						pages.setLevelsControlWorkUpdate(String.valueOf(pageNo));
					} else if (pageNo == 1704) {
						pages.setLevelsControlInspection(String.valueOf(pageNo));
					} else if (pageNo == 1705) {
						pages.setLevelsControlHistory(String.valueOf(pageNo));
					}

					/* Water Storage */

					/*
					 * else if (pageNo == 1800) { pages.setWaterStorageGlsr(String.valueOf(pageNo));
					 * } else if (pageNo == 1801) {
					 * pages.setWaterStorageoht(String.valueOf(pageNo)); }
					 */

					/* Contact Management */

					else if (pageNo == 1900) {
						pages.setContactManagement(String.valueOf(pageNo));
					}

					/* Approvals */

					else if (pageNo == 2100) {
						pages.setApprovals(String.valueOf(pageNo));
					}

					/* MIS Reports */

					else if (pageNo == 2200) {
						pages.setDailyReport(String.valueOf(pageNo));
					} else if (pageNo == 2201) {
						pages.setMonthlyReport(String.valueOf(pageNo));
					}

					/* Masters */

					else if (pageNo == 2300) {
						pages.setMasters(String.valueOf(pageNo));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pages;
	}

}
