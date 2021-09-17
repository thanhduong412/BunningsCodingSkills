package cataloglist;

/**
 * @author Thanh Duong
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Barcode;
import catalog.Product;
import catalog.Supplier;
import catalog.SupplierGroup;
import fileutils.FileUtils;

public class CatalogList {
	/**
	 * HashSet of Product objects defining the merged products
	 */
	private HashSet<Product> list;

	public CatalogList(HashSet<Product> list) {
		this.list = list;
	}

	public HashSet<Product> getList() {
		return list;
	}

	public void setList(HashSet<Product> list) {
		this.list = list;
	}

	/**
	 * Takes a csv file, creates a Product object from values and adds to HashSet if
	 * valid. Will only add products that contain barcode and supplier properties
	 * present in the existing barcode and supplier HashSets.
	 * 
	 * @param fileUtils     File utility object used to convert csv into workable
	 *                      ArrayList.
	 * @param supplierList  HashSet of valid suppliers.
	 * @param supplierGroup Supplier group that the new entries originate from.
	 * @param barcodeList   HashSet of valid barcodes.
	 * @param file          Input csv file.
	 * @throws FileNotFoundException
	 */
	public void addFromFile(FileUtils fileUtils, SupplierList supplierList, SupplierGroup supplierGroup,
			BarcodeList barcodeList, File file) throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = fileUtils.convertAndClean(file);
		ArrayList<String> temp = new ArrayList<>();

		for (Barcode barcode : barcodeList.getList()) {
			if (barcode.getSupplier().getGroup() == supplierGroup) {
				temp.add(barcode.getSku());
			}
		}

		for (ArrayList<String> value : rawFile) {
			if (temp.contains(value.get(0))) {
				Barcode barcode = barcodeList.findBarcode(supplierGroup, value.get(0));

				if (barcode != null) {
					Supplier supplier = barcode.getSupplier();

					list.add(new Product(value.get(0), value.get(1), barcode, supplier));
				} else {
					System.out.println("No barcode associated with sku and supplier.");
				}

			}
		}

	}

	/**
	 * Generates csv file containing merged product catalog.
	 * 
	 * @param fileDir Destination and name of output file.
	 */
	public void writeToFile(String fileDir) {
		try {
			FileWriter writer = new FileWriter(fileDir);
			writer.write("SKU, Description, Source");

			for (Product product : list) {
				writer.write(String.format("\n%s, %s, %s", product.getSku(), product.getProductName(),
						product.getSupplier().getGroup().toString()));
			}

			writer.close();
			System.out.println("Successfully output catalog.");
		} catch (IOException e) {
			System.out.println("An error occured while writing to file.");
			e.printStackTrace();
		}
	}

	public String toString() {
		return "CatalogList [list=" + list + "]";
	}

}
