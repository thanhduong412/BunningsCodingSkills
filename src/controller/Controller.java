package controller;

import java.io.File;
import fileutils.FileUtils;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Barcode;
import catalog.Product;
import catalog.Supplier;
import catalog.SupplierGroup;
import cataloglist.BarcodeList;
import cataloglist.CatalogList;
import cataloglist.SupplierList;

/**
 * 
 * @author Thanh Duong
 * @version 1.0
 *
 */

public class Controller {
	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * Create new file utility object for csv handling.
		 */
		FileUtils fileUtils = new FileUtils();

		/**
		 * Instantiate empty supplier, barcode and catalog HashSets
		 */
		SupplierList supplierList = new SupplierList(new HashSet<Supplier>());
		BarcodeList barcodeList = new BarcodeList(new HashSet<Barcode>());
		CatalogList catalogList = new CatalogList(new HashSet<Product>());

		/**
		 * Adds valid values to corresponding HashSets. Note: suppliers should be added
		 * in order of least priority as duplicate new entries will overwrite old ones.
		 */
		supplierList.addFromFile(fileUtils, new File("../input/suppliersA.csv"), SupplierGroup.A);
		supplierList.addFromFile(fileUtils, new File("../input/suppliersB.csv"), SupplierGroup.B);

		barcodeList.addFromFile(fileUtils, supplierList, new File("../input/barcodesA.csv"), SupplierGroup.A);
		barcodeList.addFromFile(fileUtils, supplierList, new File("../input/barcodesB.csv"), SupplierGroup.B);

		catalogList.addFromFile(fileUtils, supplierList, SupplierGroup.A, barcodeList,
				new File("../input/catalogA.csv"));
		catalogList.addFromFile(fileUtils, supplierList, SupplierGroup.B, barcodeList,
				new File("../input/catalogB.csv"));

		/**
		 * Create output csv files and write valid products to it.
		 */
		fileUtils.createFile("../output/result_output.csv");
		catalogList.writeToFile("../output/result_output.csv");
	}
}
