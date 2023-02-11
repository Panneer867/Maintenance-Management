package com.ingroinfo.mm.service;


import com.ingroinfo.mm.entity.Backup;

public interface BackupService {

	void scheduleBackup();

	void saveBackupSchedule(Backup backup);

}
