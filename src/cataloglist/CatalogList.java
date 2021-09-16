package cataloglist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Barcode;
import catalog.Product;
import catalog.Supplier;
import catalog.SupplierGroup;
import fileutils.FileUtils;

public class CatalogList {
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

	public String toString() {
		return "CatalogList [list=" + list + "]";
	}

}
