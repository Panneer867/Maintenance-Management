//Swal Messages While Deleting Masters

function deleteDeptMaster(depMasterId) {
	swal({
		text: "Are you sure ? You Want To Delete This Department !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteDeptMaster/" + depMasterId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteCategory(catid) {
	swal({
		text: "Are you sure ? You Want To Delete This Category !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteCategory/" + catid;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteDistrutLocation(disLocId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/delteDistLocation/" + disLocId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteDistributionSchedule(disScheduleId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteDistSchedule/" + disScheduleId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteDivSubdiv(divsubId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteDivSubdiv/" + divsubId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteDmaWard(dmaWardId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteDmaWard/" + dmaWardId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteEmplyeePerformance(empPerformId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteEmpPerform/" + empPerformId;
			} else {
				swal("Data is safe !!");
			}
		});
}


function deleteHsnCode(hsnCodeId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteHnsCode/" + hsnCodeId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteMaintenFrequency(maintanFrequId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteMaintenFrequency/" + maintanFrequId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteMaintainsActivity(maintenActiveId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteMaintenActive/" + maintenActiveId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteMaintainsPerformance(maintenPerformId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteMaintenPerform/" + maintenPerformId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteMaintainsType(maintenTypeId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteMaintenType/" + maintenTypeId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteMasterItem(itemMasterId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteMasterItem/" + itemMasterId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteMeterManufacture(mtrmanufacId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteMeterManufact/" + mtrmanufacId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteMeterType(meterTypeId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteMeterType/" + meterTypeId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deletePipeManufacture(pipemanufId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deletePipeManufact/" + pipemanufId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deletePressureType(pressureId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deletePressureType/" + pressureId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteMasterPump(pumpMasterId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deletePumpMaster/" + pumpMasterId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteSafetyPrecution(saftyprecId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteSafetyPrecous/" + saftyprecId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteServiceArea(sericAreaId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteServiceArea/" + sericAreaId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteServiceProgressType(servcProgressId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteServiceProgress/" + servcProgressId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteServiceProvider(servProvId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteServiceProvider/" + servProvId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteSparePartsEquipment(spareequiId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteSpareEquipment/" + spareequiId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}


function deleteStoreBranch(strbraNameId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteStoreBranch/" + strbraNameId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteSupplierDetails(suplyDtlsId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteSupplierDtls/" + suplyDtlsId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteDesignationMaster(desigId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteDesignation/" + desigId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteLikageType(leakageId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteLeakageType/" + leakageId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteTaskStatus(taskstsId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteTaskStatus/" + taskstsId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteTaxMaster(taxMasterId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteTaxmaster/" + taxMasterId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteUnitOfMeasure(unitMeasureId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteUnitMeasure/" + unitMeasureId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteVehicleDetails(vehicleDtlsId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteVehicleDtls/" + vehicleDtlsId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteWorkPriority(workPrioId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteWorkPriority/" + workPrioId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteWorkStatus(workStsId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteWorkStatus/" + workStsId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteTeamcode(teamCodeId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteTeamCode/" + teamCodeId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteWaterSource(wateSourceId) {
	// @ts-ignore
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				// @ts-ignore
				window.location = "/masters/deleteWaterSource/" + wateSourceId;
			} else {
				// @ts-ignore
				swal("Data is safe !!");
			}
		});
}

function deleteEmpCategory(empCategoryId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteEmpCategory/" + empCategoryId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteBrandMaster(brandMasterId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteBrand/" + brandMasterId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteEmplyeeQualification(qulificationId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteEmpQualif/" + qulificationId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteEmployeeStatus(statusId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteEmpStatus/" + statusId;
			} else {
				swal("Data is safe !!");
			}
		});
}

function deleteEmployeeType(empTypeId) {
	swal({
		text: "Are you sure ? You Want To Delete This !!",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				window.location = "/masters/deleteEmptype/" + empTypeId;
			} else {
				swal("Data is safe !!");
			}
		});
}