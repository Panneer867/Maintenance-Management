package com.ingroinfo.mm.backup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Backup extends Thread {

	private String dbUser;
	private String dbPassword;

	public Backup(String dbUser, String dbPassword) {
		this.dbUser = dbUser;
		this.dbPassword = dbPassword;

	}

	@Override
	public void run() {

		clientBackup();
	}

	public void clientBackup() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy~hh-mm aa");
		String currentDate = sdf.format(new Date());
		File f = null;
		FileOutputStream fout = null;

		try {

			PrintStream p = null;
			f = new File("ExportData.bat");
			fout = new FileOutputStream(f, false);
			p = new PrintStream(fout);
			String str1 = "MKDIR E:\\BACKUP";
			String str10 = "MKDIR E:\\BACKUP\\MMDB";
			String str2 = "EXP " + dbUser + "/" + dbPassword + " owner=" + dbUser + "  file=E:\\BACKUP\\MMDB\\" + dbUser
					+ "-" + currentDate.trim().replace(" ", "") + ".DMP Log = BackUp.Log; ";
			p.println(str1);
			p.println(str10);
			p.println(str2);
			p.println("EXIT");
			p.close();
			fout.close();

			String command = "cmd /C Start /wait ExportData.bat";
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec(command);
			pr.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
