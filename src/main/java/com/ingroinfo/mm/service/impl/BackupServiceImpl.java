package com.ingroinfo.mm.service.impl;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.BackupScheduleRepository;
import com.ingroinfo.mm.entity.Backup;
import com.ingroinfo.mm.service.BackupService;

@Service
public class BackupServiceImpl implements BackupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BackupServiceImpl.class);
	private final BackupScheduleRepository backupScheduleRepository;
	private final Environment environment;

	public BackupServiceImpl(BackupScheduleRepository backupScheduleRepository, Environment environment) {

		this.backupScheduleRepository = backupScheduleRepository;
		this.environment = environment;
	}

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
		currentScheduler.setTimeOne(backup.getTimeOne());
		currentScheduler.setTimeTwo(backup.getTimeTwo());
		currentScheduler.setTimeThree(backup.getTimeThree());

		backupScheduleRepository.save(currentScheduler);
	}

	@Override
	public void startBackupThread() {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				List<Backup> backups = backupScheduleRepository.findAll();

				if (!backups.isEmpty()) {
					Backup firstBackup = backups.get(0);

					String path = firstBackup.getDrive() + "\\" + firstBackup.getPath();

					List<Object> backupTimes = Arrays.asList(firstBackup.getTimeOne(), firstBackup.getTimeTwo(),
							firstBackup.getTimeThree());

					Set<Object> uniqueBackupTimes = new HashSet<>(backupTimes);
					if (backupTimes.size() == uniqueBackupTimes.size()) {
						scheduleBackup(firstBackup.getTimeOne(), path);
						scheduleBackup(firstBackup.getTimeTwo(), path);
						scheduleBackup(firstBackup.getTimeThree(), path);
					} else {
						LOGGER.info("Times are not distinct");
					}
				}
			}
		}, 0, 60 * 1000);

	}

	private void scheduleBackup(String backupTime, String path) {

		Calendar calendar = Calendar.getInstance();
		String[] timeParts = backupTime.split(":");
		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);

		Calendar calenderNow = Calendar.getInstance();

		if (calenderNow.get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY)
				&& calenderNow.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)) {
			LOGGER.info("Starting Backup Process");

			generateBackup(path);

			LOGGER.info("Backup Process Completed");
		}
	}

	public void generateBackup(String path) {

		Date now = new Date();
		String currentDate = new SimpleDateFormat("dd-MMM-yy~hh-mm aa").format(now);
		String username = environment.getProperty("spring.datasource.username");
		String password = environment.getProperty("spring.datasource.password");

		File backupFile = new File("Export.bat");
		try (PrintWriter writer = new PrintWriter(backupFile)) {
			writer.println("MKDIR " + path);
			String pathBackup = path + "\\BACKUP";
			writer.println("MKDIR " + pathBackup);
			String formattedCommand = String.format(
					"EXP %s/%s owner=%s file=" + pathBackup + "\\%s-%s.DMP Log=Backup.log;", username, password,
					username, username, currentDate.trim().replace(" ", ""));
			writer.println(formattedCommand);
			writer.println("EXIT");
		} catch (Exception e) {
			LOGGER.error("Error while creating backup file", e);
		}
		try {
			Process process = Runtime.getRuntime().exec("cmd /C Start /wait Export.bat");
			process.waitFor();
		} catch (Exception e) {
			LOGGER.error("Error during backup process", e);
		}
	}

}
