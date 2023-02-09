package com.ingroinfo.mm.backup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Backup extends Thread {

	private String username;
	private String password;

	public Backup(String username, String password) {
		this.username = username;
		this.password = password;
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

			String createDirectory = "MKDIR E:\\BACKUP";
			String createSubDirectory = "MKDIR E:\\BACKUP\\MMDB";
			String exportCommand = "EXP " + username + "/" + password + " owner=" + username
					+ "  file=E:\\BACKUP\\MMDB\\" + username + "-" + currentDate.trim().replace(" ", "")
					+ ".DMP Log = BackUp.Log;";

			printStream.println(createDirectory);
			printStream.println(createSubDirectory);
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
