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

public class Controller {

	public static void main(String[] args) throws FileNotFoundException {
		FileUtils fileUtils = new FileUtils();

		HashSet<Supplier> supplierList = new HashSet<>();
		HashSet<Barcode> barcodeList = new HashSet<>();
		HashSet<Product> catalog = new HashSet<>();

		// create supplier objects from input files

		fileUtils.addSuppliersFromFile(supplierList, new File("./input/suppliersA.csv"), SupplierGroup.A);
		fileUtils.addSuppliersFromFile(supplierList, new File("./input/suppliersB.csv"), SupplierGroup.B);

		// create barcode objects from input files, sort which supplier group, if
		// barcodes same dont create
		fileUtils.addBarcodesFromFile(supplierList, barcodeList, new File("./input/barcodesA.csv"), SupplierGroup.A);
		fileUtils.addBarcodesFromFile(supplierList, barcodeList, new File("./input/barcodesB.csv"), SupplierGroup.B);

		// create valid products and add to catalog
		fileUtils.addProductsFromFile(supplierList, SupplierGroup.A, barcodeList, catalog,
				new File("./input/catalogA.csv"));
		fileUtils.addProductsFromFile(supplierList, SupplierGroup.B, barcodeList, catalog,
				new File("./input/catalogB.csv"));
		// output

	}
}
