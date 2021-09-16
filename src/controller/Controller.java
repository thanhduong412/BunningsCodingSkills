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

public class Controller {

	public static void main(String[] args) throws FileNotFoundException {
		FileUtils fileUtils = new FileUtils();

		SupplierList supplierList = new SupplierList(new HashSet<Supplier>());
		BarcodeList barcodeList = new BarcodeList(new HashSet<Barcode>());
		CatalogList catalogList = new CatalogList(new HashSet<Product>());

		// create supplier objects from input files

		supplierList.addFromFile(fileUtils, new File("./input/suppliersA.csv"), SupplierGroup.A);
		supplierList.addFromFile(fileUtils, new File("./input/suppliersB.csv"), SupplierGroup.B);

		// create barcode objects from input files, sort which supplier group, if
		// barcodes same dont create
		barcodeList.addFromFile(fileUtils, supplierList, new File("./input/barcodesA.csv"), SupplierGroup.A);
		barcodeList.addFromFile(fileUtils, supplierList, new File("./input/barcodesB.csv"), SupplierGroup.B);

		// create valid products and add to catalog
		catalogList.addFromFile(fileUtils, supplierList, SupplierGroup.A, barcodeList,
				new File("./input/catalogA.csv"));
		catalogList.addFromFile(fileUtils, supplierList, SupplierGroup.B, barcodeList,
				new File("./input/catalogB.csv"));
		// output

		System.out.println(catalogList);
	}
}
