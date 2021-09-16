package productlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import fileutils.FileUtils;
import products.Barcode;
import products.Catalog;

public class ProductList {
	private FileUtils fileUtils = new FileUtils();

	public ProductList() {
	}

	public void addBarcodeList(ArrayList<Barcode> barcodeList, File newEntries) throws FileNotFoundException {
		ArrayList<Barcode> newEntriesList = addBarcodeListFromFile(newEntries);

		if (barcodeList.isEmpty()) {
			for (Barcode barcode : newEntriesList) {
				barcodeList.add(barcode);
			}
		} else {
			dupeBarcodeCheck(barcodeList, newEntriesList);
		}
	}

	private ArrayList<Barcode> addBarcodeListFromFile(File barcodes) throws FileNotFoundException {
		ArrayList<ArrayList<String>> temp = fileUtils.convertAndClean(barcodes);

		ArrayList<Barcode> barcodeList = new ArrayList<>();

		for (ArrayList<String> value : temp) {
			barcodeList.add(new Barcode(Integer.parseInt(value.get(0)), value.get(1), value.get(2)));
		}

		return barcodeList;

	}

	private void dupeBarcodeCheck(ArrayList<Barcode> barcodeList, ArrayList<Barcode> newEntries) {
		boolean add;

		for (Barcode newBarcode : newEntries) {
			add = true;
			for (Barcode existingBarcode : barcodeList) {
				if (newBarcode.getBarcode() == existingBarcode.getBarcode()) {
					add = false;
				}
			}

			if (add) {
				barcodeList.add(newBarcode);
			}
		}
	}
}
