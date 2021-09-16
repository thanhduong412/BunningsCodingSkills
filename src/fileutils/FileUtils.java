package fileutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {

	public FileUtils() {
	}

	public ArrayList<ArrayList<String>> convertAndClean(File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> cleanedList = convertToArrayList(file);
		cleanedList.remove(0);

		return cleanedList;
	}
	
	private ArrayList<ArrayList<String>> convertToArrayList(File file) throws FileNotFoundException {
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
