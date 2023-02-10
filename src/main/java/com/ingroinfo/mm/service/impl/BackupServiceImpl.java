package com.ingroinfo.mm.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.backup.BackupThread;
import com.ingroinfo.mm.dao.BackupScheduleRepository;
import com.ingroinfo.mm.entity.Backup;
import com.ingroinfo.mm.service.BackupService;

@Service
public class BackupServiceImpl implements BackupService {

	@Autowired
	private BackupScheduleRepository backupScheduleRepository;

	@Autowired
	public Environment environment;

	@Override
	public void scheduleBackup() {

		Calendar calendar = Calendar.getInstance();
		List<Backup> backups = backupScheduleRepository.findAll();

		if (!backups.isEmpty()) {
			
			String username = environment.getProperty("spring.datasource.username");
			String password = environment.getProperty("spring.datasource.password");
			String backupTime = backups.get(0).getTime();

			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(backupTime.split(":")[0]));
			calendar.set(Calendar.MINUTE, Integer.parseInt(backupTime.split(":")[1]));
			calendar.set(Calendar.SECOND, 0);

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Thread backupThread = new Thread(new BackupThread(username, password));
					backupThread.start();
				}
			}, calendar.getTime(), 24 * 60 * 60 * 1000);
		}
	}

}
