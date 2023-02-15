package com.ingroinfo.mm.service.impl;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;
import com.ingroinfo.mm.dao.BackupScheduleRepository;
import com.ingroinfo.mm.entity.Backup;
import com.ingroinfo.mm.service.BackupService;

@Service
public class BackupServiceImpl implements BackupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BackupServiceImpl.class);
	private final BackupScheduleRepository backupScheduleRepository;
	private final DataSourceProperties dataSourceProperties;

	public BackupServiceImpl(BackupScheduleRepository backupScheduleRepository,
			DataSourceProperties dataSourceProperties) {
		this.backupScheduleRepository = backupScheduleRepository;
		this.dataSourceProperties = dataSourceProperties;
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

					List<String> backupTimes = Arrays.asList(firstBackup.getTimeOne(), firstBackup.getTimeTwo(),
							firstBackup.getTimeThree());

					Set<String> uniqueBackupTimes = new HashSet<>(backupTimes);
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

		File backupFile = new File("Export.bat");
		try (PrintWriter writer = new PrintWriter(backupFile)) {
			writer.println("MKDIR " + path);
			String pathBackup = path + "\\BACKUP";
			writer.println("MKDIR " + pathBackup);
			String formattedCommand = String.format(
					"EXP %s/%s owner=%s file=" + pathBackup + "\\%s-%s.DMP Log=Backup.log;",
					dataSourceProperties.getUsername(), dataSourceProperties.getPassword(),
					dataSourceProperties.getUsername(), dataSourceProperties.getUsername(),
					currentDate.trim().replace(" ", ""));
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

	@Override
	public List<String> getLocalDriveLetters() {

		List<String> driveLetters = new ArrayList<String>();
		File[] roots = File.listRoots();
		for (File root : roots) {
			String driveLetter = root.getAbsolutePath().substring(0, 2);
			driveLetters.add(driveLetter);
		}
		return driveLetters;
	}

	@Override
	public Backup getBackupSchedule() {
		Optional<Backup> backupOptional = backupScheduleRepository.findAll().stream().findFirst();
		if (backupOptional.isPresent()) {

			backupOptional.get().setTimeOne(timeParser(backupOptional.get().getTimeOne()));
			backupOptional.get().setTimeTwo(timeParser(backupOptional.get().getTimeTwo()));
			backupOptional.get().setTimeThree(timeParser(backupOptional.get().getTimeThree()));
			
			return backupOptional.get();
		} else {
			Backup b = new Backup();
			String na = "NA";
			b.setDrive(na);
			b.setPath(na);
			b.setSchedule(na);
			b.setTimeOne(na);
			b.setTimeTwo(na);
			b.setTimeThree(na);
			return b;
		}
	}

	private String timeParser(String timeStr) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime = LocalTime.parse(timeStr, inputFormatter);

		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");
		return localTime.format(outputFormatter);

	}

}
