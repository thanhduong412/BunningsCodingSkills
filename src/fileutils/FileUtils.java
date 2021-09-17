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

/**
 * 
 * @author Thanh Duong
 * @version 1.0
 *
 */
public class FileUtils {

	public FileUtils() {
	}

	/**
	 * Takes converted csv file and converts to an ArrayList of ArrayLists of only
	 * values (i.e labels are removed).
	 * 
	 * @param file Input csv file.
	 * @return
	 * @throws FileNotFoundException
	 */
	public ArrayList<ArrayList<String>> convertAndClean(File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> cleanedList = convertToArrayList(file);
		cleanedList.remove(0);

		return cleanedList;
	}

	/**
	 * Creates a new file for the purposes of generating output.
	 * 
	 * @param fileDir Destination and name of file.
	 */
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

	/**
	 * Helper function that takes input csv file and converts it into an ArrayList
	 * of ArrayLists.
	 * 
	 * @param file Input csv file.
	 * @return ArrayList of ArrayLists representation of csv file.
	 * @throws FileNotFoundException
	 */
	private ArrayList<ArrayList<String>> convertToArrayList(File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> fileList = new ArrayList<>();

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				fileList.add(getValueFromLine(scanner.nextLine()));
			}
		}

		return fileList;

	}

	/**
	 * Helper function that gets the raw values of each line of the input file.
	 * 
	 * @param line The current line the scanner is on.
	 * @return ArrayList of values from the current line.
	 */
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
