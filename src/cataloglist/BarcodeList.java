package cataloglist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Barcode;
import catalog.Supplier;
import catalog.SupplierGroup;
import fileutils.FileUtils;

public class BarcodeList {
	private HashSet<Barcode> list;

	public BarcodeList(HashSet<Barcode> list) {
		this.list = list;
	}

	public HashSet<Barcode> getList() {
		return list;
	}

	public void setList(HashSet<Barcode> list) {
		this.list = list;
	}

	public void addFromFile(FileUtils fileUtils, SupplierList supplierList, File file, SupplierGroup supplierGroup)
			throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = fileUtils.convertAndClean(file);

		if (list.isEmpty()) {
			for (ArrayList<String> value : rawFile) {
				Supplier supplier = supplierList.findSupplier(Integer.valueOf(value.get(0)), supplierGroup);

				if (supplier != null) {
					list.add(new Barcode(supplier, value.get(1), value.get(2)));
				} else {
					System.out.println("Supplier does not exist.");
				}

			}
		} else {
			ArrayList<String> temp = new ArrayList<>();

			for (Barcode barcode : list) {
				temp.add(barcode.getBarcode());
			}

			for (ArrayList<String> value : rawFile) {
				Supplier supplier = supplierList.findSupplier(Integer.valueOf(value.get(0)), supplierGroup);

				if (!temp.contains(value.get(2))) {
					if (supplier != null) {
						list.add(new Barcode(supplier, value.get(1), value.get(2)));
					} else {
						System.out.println("Supplier does not exist.");
					}
				}
			}
		}
	}

	public Barcode findBarcode(SupplierGroup supplierGroup, String sku) {
		for (Barcode barcode : list) {
			if ((barcode.getSku().equals(sku)) && (barcode.getSupplier().getGroup() == supplierGroup)) {
				return barcode;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "BarcodeList [list=" + list + "]";
	}

}
