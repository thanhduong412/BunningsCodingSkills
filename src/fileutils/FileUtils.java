package fileutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {
	public File file;

	public FileUtils(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ArrayList<ArrayList<String>> convertToArrayList() throws FileNotFoundException {
		ArrayList<ArrayList<String>> fileList = new ArrayList<>();

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				fileList.add(getValueFromLine(scanner.nextLine()));
			}
		}

		return fileList;

	}

	private ArrayList<String> getValueFromLine(String line) {
		ArrayList<String> values = new ArrayList<String>();

		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(",");
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}

		return values;
	}
}
