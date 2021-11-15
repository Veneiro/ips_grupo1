package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class BackupDBControlador {

    LocalDate now = LocalDate.now();

    public BackupDBControlador() {
	if (now.getDayOfWeek() == DayOfWeek.MONDAY)
	    copyDB();
    }

    public boolean copyDB() {
	File origin = new File("db/ihospital.db");
	File destination = new File(
		"db/ihospital_" + now.getDayOfMonth() + "_" + now.getMonthValue() + "_" + now.getYear() + ".db");
	if (origin.exists()) {
	    try {
		InputStream in = new FileInputStream(origin);
		OutputStream out = new FileOutputStream(destination);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
		    out.write(buf, 0, len);
		}
		in.close();
		out.close();
		return true;
	    } catch (IOException ioe) {
		ioe.printStackTrace();
		return false;
	    }
	} else {
	    return false;
	}
    }
}
