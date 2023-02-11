package com.ingroinfo.mm.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.backup.BackupThread;
import com.ingroinfo.mm.dao.BackupScheduleRepository;
import com.ingroinfo.mm.entity.Backup;
import com.ingroinfo.mm.service.BackupService;

@Service
public class BackupServiceImpl implements BackupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BackupServiceImpl.class);
	private static final Timer TIMER = new Timer();

	@Autowired
	private BackupScheduleRepository backupScheduleRepository;

	@Autowired
	public Environment environment;

	@Override
	public void saveBackupSchedule(Backup backup) {
		List<Backup> backups = backupScheduleRepository.findAll();
		Backup currentScheduler;

		if (!backups.isEmpty()) {
			backupScheduleRepository.deleteAll(backups.subList(1, backups.size()));
			currentScheduler = backups.get(0);
		} else {
			currentScheduler = new Backup();
		}

		currentScheduler.setDrive(backup.getDrive());
		currentScheduler.setPath(backup.getPath());
		currentScheduler.setSchedule(backup.getSchedule());
		currentScheduler.setTime(backup.getTime());

		backupScheduleRepository.save(currentScheduler);
	}

	@Override
	public void scheduleBackup() {

		TIMER.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				backup();
			}
		}, 0, 60 * 1000);
	}

	private void backup() {

		List<Backup> backups = backupScheduleRepository.findAll();
		if (backups.isEmpty()) {
			return;
		}

		String username = environment.getProperty("spring.datasource.username");
		String password = environment.getProperty("spring.datasource.password");

		Backup firstBackup = backups.get(0);
		String backupTime = firstBackup.getTime();

		String[] timeParts = backupTime.split(":");
		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);

		Calendar calendar = Calendar.getInstance();
		int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
		int currentMinute = calendar.get(Calendar.MINUTE);

		if (hour <= currentHour && minute <= currentMinute) {

			return;
		}

		LOGGER.info("Starting the backup process.");
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);

		TIMER.schedule(new TimerTask() {
			@Override
			public void run() {
				LOGGER.info("Backup process has started.");
				new BackupThread(username, password).run();
				LOGGER.info("The backup process has been completed.");
			}
		}, calendar.getTime());
	}

}
