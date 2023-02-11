package com.ingroinfo.mm.backup;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ingroinfo.mm.service.BackupService;

@Component
public class BackupScheduler {

	@Autowired
	private BackupService backupService;

	@PostConstruct
	public void scheduleBackup() {
		backupService.scheduleBackup();
	}

}
