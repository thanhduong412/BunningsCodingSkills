package cataloglist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Supplier;
import catalog.SupplierGroup;
import fileutils.FileUtils;

public class SupplierList {
	private HashSet<Supplier> list;

	public SupplierList(HashSet<Supplier> list) {
		this.list = list;
	}

	public HashSet<Supplier> getList() {
		return list;
	}

	public void setList(HashSet<Supplier> list) {
		this.list = list;
	}

	public void addFromFile(FileUtils fileUtils, File file, SupplierGroup supplierGroup) throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = fileUtils.convertAndClean(file);

		for (ArrayList<String> value : rawFile) {
			list.add(new Supplier(Integer.valueOf(value.get(0)), supplierGroup, value.get(1)));
		}

	}

	public Supplier findSupplier(int supplierID, SupplierGroup supplierGroup) {

		for (Supplier supplier : list) {
			if ((supplier.getID() == supplierID) && (supplier.getGroup() == supplierGroup)) {
				return supplier;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "SupplierList [list=" + list + "]";
	}

}
