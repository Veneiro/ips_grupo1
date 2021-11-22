package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class BackupDBControlador {

    private static final int NUM_BACKUPS = 4;

    LocalDate now = LocalDate.now();

    public BackupDBControlador() {
	if (now.getDayOfWeek() == DayOfWeek.MONDAY)
	    copyDB();
    }

    public boolean copyDB() {
	List<File> backups = new ArrayList<>();

	File origin = new File("db/ihospital.db");

	for (int i = 1; i <= NUM_BACKUPS; i++) {
	    LocalDate previousWeek = now.minusDays(7 * i);
	    File b = new File("db/ihospital_" + previousWeek.getDayOfMonth() + "_" + previousWeek.getMonthValue() + "_"
		    + previousWeek.getYear() + ".db");
	    if (b.exists()) {
		if (i == NUM_BACKUPS)
		    b.delete();
		else
		    backups.add(b);
	    }

	}

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

		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setEncryptFiles(true);
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		zipParameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG);

		File zip = new File("db/backup.db");
		zip.delete();
		ZipFile zipFile = new ZipFile("db/backup.zip");

		backups.forEach(b -> {
		    try {
			zipFile.addFile(b);
		    } catch (ZipException e) {
			e.printStackTrace();
		    }
		});
		zipFile.addFile(destination);

		zipFile.close();

		for (int i = 0; i <= NUM_BACKUPS; i++) {
		    LocalDate previousWeek = now.minusDays(7 * i);
		    File b = new File("db/ihospital_" + previousWeek.getDayOfMonth() + "_"
			    + previousWeek.getMonthValue() + "_" + previousWeek.getYear() + ".db");
		    if (b.exists())
			b.delete();
		}

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
