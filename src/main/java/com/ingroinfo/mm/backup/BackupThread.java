package com.ingroinfo.mm.backup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BackupThread extends Thread {
	private String username;
	private String password;

	public BackupThread(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public BackupThread() {
	}

	@Override
	public void run() {
		performBackup();
	}

	private void performBackup() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy~hh-mm aa");
		String currentDate = dateFormat.format(new Date());

		try {
			File backupFile = new File("Export.bat");
			FileOutputStream fileOutputStream = new FileOutputStream(backupFile, false);
			PrintStream printStream = new PrintStream(fileOutputStream);

			String createDirectoryCommand = "MKDIR E:\\BACKUP";
			String createSubDirectoryCommand = "MKDIR E:\\BACKUP\\MMDB";
			String exportCommand = "EXP " + username + "/" + password + " owner=" + username
					+ " file=E:\\BACKUP\\MMDB\\" + username + "-" + currentDate.trim().replace(" ", "")
					+ ".DMP Log=Backup.log;";

			printStream.println(createDirectoryCommand);
			printStream.println(createSubDirectoryCommand);
			printStream.println(exportCommand);
			printStream.println("EXIT");
			printStream.close();
			fileOutputStream.close();

			String command = "cmd /C Start /wait Export.bat";
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
