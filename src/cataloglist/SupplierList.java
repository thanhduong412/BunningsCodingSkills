package cataloglist;

/**
 * @author Thanh Duong
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import catalog.Supplier;
import catalog.SupplierGroup;
import fileutils.FileUtils;

public class SupplierList {
	/**
	 * HashSet of Supplier objects.
	 */
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

	/**
	 * Takes a csv file and adds supplier to HashSet. New entries will ovewrite
	 * existing ones.
	 * 
	 * @param fileUtils     File utility used to convert csv into workable
	 *                      ArrayList.
	 * @param file          Input csv file.
	 * @param supplierGroup Supplier group the new entries originate from.
	 * @throws FileNotFoundException
	 */
	public void addFromFile(FileUtils fileUtils, File file, SupplierGroup supplierGroup) throws FileNotFoundException {
		ArrayList<ArrayList<String>> rawFile = fileUtils.convertAndClean(file);

		for (ArrayList<String> value : rawFile) {
			list.add(new Supplier(Integer.valueOf(value.get(0)), supplierGroup, value.get(1)));
		}

	}

	/**
	 * Find specific Supplier object based on its ID and group.
	 * 
	 * @param supplierID    The ID of the supplier.
	 * @param supplierGroup The group the supplier belongs to.
	 * @return
	 */
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
