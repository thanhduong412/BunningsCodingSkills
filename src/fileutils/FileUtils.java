package fileutils;

import java.io.File;
import java.io.FileNotFoundException;
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

public class FileUtils {

	public FileUtils() {
	}

	public void addSuppliersFromFile(HashSet<Supplier> supplierList, File file, SupplierGroup supplierGroup)
			throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = convertAndClean(file);

		for (ArrayList<String> value : rawFile) {
			supplierList.add(new Supplier(Integer.valueOf(value.get(0)), supplierGroup, value.get(1)));
		}

	}

	public void addBarcodesFromFile(HashSet<Supplier> supplierList, HashSet<Barcode> barcodeList, File file,
			SupplierGroup supplierGroup) throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = convertAndClean(file);

		if (barcodeList.isEmpty()) {
			for (ArrayList<String> value : rawFile) {
				Supplier supplier = findSupplier(supplierList, Integer.valueOf(value.get(0)), supplierGroup);

				if (supplier != null) {
					barcodeList.add(new Barcode(supplier, value.get(1), value.get(2)));
				} else {
					System.out.println("Supplier does not exist.");
				}

			}
		} else {
			ArrayList<String> temp = new ArrayList<>();

			for (Barcode barcode : barcodeList) {
				temp.add(barcode.getBarcode());
			}

			for (ArrayList<String> value : rawFile) {
				Supplier supplier = findSupplier(supplierList, Integer.valueOf(value.get(0)), supplierGroup);

				if (!temp.contains(value.get(2))) {
					if (supplier != null) {
						barcodeList.add(new Barcode(supplier, value.get(1), value.get(2)));
					} else {
						System.out.println("Supplier does not exist.");
					}
				}
			}
		}
	}

	public void addProductsFromFile(HashSet<Supplier> supplierList, SupplierGroup supplierGroup,
			HashSet<Barcode> barcodeList, HashSet<Product> catalog, File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = convertAndClean(file);
		ArrayList<String> temp = new ArrayList<>();

		for (Barcode barcode : barcodeList) {
			if (barcode.getSupplier().getGroup() == supplierGroup) {
				temp.add(barcode.getSku());
			}
		}

		for (ArrayList<String> value : rawFile) {
			if (temp.contains(value.get(0))) {
				Barcode barcode = findBarcode(barcodeList, supplierGroup, value.get(0));

				if (barcode != null) {
					Supplier supplier = barcode.getSupplier();

					catalog.add(new Product(value.get(0), value.get(1), barcode, supplier));
				} else {
					System.out.println("No barcode associated with sku and supplier.");
				}

			}
		}

	}

	private Supplier findSupplier(HashSet<Supplier> supplierList, int supplierID, SupplierGroup supplierGroup) {

		for (Supplier supplier : supplierList) {
			if ((supplier.getID() == supplierID) && (supplier.getGroup() == supplierGroup)) {
				return supplier;
			}
		}

		return null;
	}

	private Barcode findBarcode(HashSet<Barcode> barcodeList, SupplierGroup supplierGroup, String sku) {
		for (Barcode barcode : barcodeList) {
			if ((barcode.getSku().equals(sku)) && (barcode.getSupplier().getGroup() == supplierGroup)) {
				return barcode;
			}
		}

		return null;
	}

	private ArrayList<ArrayList<String>> convertAndClean(File file) throws FileNotFoundException {
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
