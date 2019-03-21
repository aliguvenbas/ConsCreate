package com.ag.cc.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {

	private String filePath;

	public boolean createFileWithName(String fileName, String extension) {
		boolean ok = false;
		if (fileName != null && extension != null) {
			if (!fileName.isEmpty() && !extension.isEmpty()) {
				filePath = "result/" + (fileName + "." + extension);
				File file = new File(filePath);
				if (!file.exists()) {
					try {
						ok = file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return ok;
	}

	public boolean writeToFile(String content) {
		boolean ret = false;
		if (filePath != null && !filePath.isEmpty()) {
			BufferedWriter bw = null;
			FileWriter fw = null;
			try {
				fw = new FileWriter(filePath);
				bw = new BufferedWriter(fw);
				bw.write(content);
				ret = true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
