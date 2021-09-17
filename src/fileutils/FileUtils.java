package fileutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import catalog.Barcode;
import catalog.Product;
import catalog.Supplier;
import catalog.SupplierGroup;
import cataloglist.BarcodeList;

public class FileUtils {

	public FileUtils() {
	}

	public ArrayList<ArrayList<String>> convertAndClean(File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> cleanedList = convertToArrayList(file);
		cleanedList.remove(0);

		return cleanedList;
	}

	public void createFile(String fileDir) {
		try {
			File file = new File(fileDir);
			if (file.createNewFile()) {
				System.out.println("File createdL :" + file.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An unexpected error has occured.");
			e.printStackTrace();
		}
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
