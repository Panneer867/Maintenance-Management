package com.ingroinfo.mm.service;


import com.ingroinfo.mm.entity.Backup;

public interface BackupService {

	void startBackupThread();

	void saveBackupSchedule(Backup backup);

	void generateBackup(String path);
}
