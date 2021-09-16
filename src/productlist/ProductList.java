//package productlist;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import catalog.Barcode;
//import catalog.Catalog;
//import fileutils.FileUtils;
//
//public class ProductList {
//	private FileUtils fileUtils = new FileUtils();
//
//	public ProductList() {
//	}
//
//	public void addBarcodeList(ArrayList<Barcode> barcodeList, File newEntries) throws FileNotFoundException {
//		ArrayList<Barcode> newEntriesList = addBarcodeListFromFile(newEntries);
//
//		if (barcodeList.isEmpty()) {
//			for (Barcode barcode : newEntriesList) {
//				barcodeList.add(barcode);
//			}
//		} else {
//			dupeBarcodeCheck(barcodeList, newEntriesList);
//		}
//	}
//
//	private ArrayList<Barcode> addBarcodeListFromFile(File barcodes) throws FileNotFoundException {
//		ArrayList<ArrayList<String>> temp = fileUtils.convertAndClean(barcodes);
//
//		ArrayList<Barcode> barcodeList = new ArrayList<>();
//
//		for (ArrayList<String> value : temp) {
//			barcodeList.add(new Barcode(Integer.parseInt(value.get(0)), value.get(1), value.get(2)));
//		}
//
//		return barcodeList;
//
//	}
//
//	private void dupeBarcodeCheck(ArrayList<Barcode> barcodeList, ArrayList<Barcode> newEntries) {
//		for(Barcode newBarcode : newEntries) {
//			if(!barcodeList.contains(newBarcode)) {
//				barcodeList.add(newBarcode);
//			}
//		}
////		boolean add;
////
////		for (Barcode newBarcode : newEntries) {
////			add = true;
////			for (Barcode existingBarcode : barcodeList) {
////				if (newBarcode.getBarcode() == existingBarcode.getBarcode()) {
////					add = false;
////				}
////			}
////
////			if (add) {
////				barcodeList.add(newBarcode);
////			}
////		}
//	}
//
//	// check catalog sku dupe
//	public void addNewCatalogItems(ArrayList<Barcode> barcodeList, HashMap<String, String> catalogList, File newEntries)
//			throws FileNotFoundException {
//		ArrayList<ArrayList<String>> temp = fileUtils.convertAndClean(newEntries);
//
//		for (ArrayList<String> value : temp) {
//			for (Barcode barcode : barcodeList) {
//				if ((barcode.getSku().equals(value.get(0)))) {
//					catalogList.put(value.get(0), value.get(1));
//				}
//			}
//		}
//	}
//}
