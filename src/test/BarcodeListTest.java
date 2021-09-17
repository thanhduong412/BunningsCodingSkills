package test;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

import catalog.Barcode;
import catalog.Supplier;
import catalog.SupplierGroup;
import cataloglist.BarcodeList;

public class BarcodeListTest {
	private Barcode testCodeA1 = new Barcode(new Supplier(0, SupplierGroup.A, "foo"), "bar", "foo-bar-baz");
	private Barcode testCodeA2 = new Barcode(new Supplier(1, SupplierGroup.A, "qux"), "quux", "qux-quux-quuz");
	private Barcode testCodeA3 = new Barcode(new Supplier(2, SupplierGroup.A, "corge"), "grault",
			"corge-grault-garply");
	private Barcode[] groupAData = { testCodeA1, testCodeA2, testCodeA3 };

	private Barcode testCodeB1 = new Barcode(new Supplier(0, SupplierGroup.B, "foo"), "bar", "foo-bar-baz");
	private Barcode testCodeB2 = new Barcode(new Supplier(1, SupplierGroup.B, "qux"), "quux", "qux-quux-quuz");
	private Barcode testCodeB3 = new Barcode(new Supplier(2, SupplierGroup.B, "corge"), "grault",
			"corge-grault-garply");
	private Barcode[] groupBData = { testCodeB1, testCodeB2, testCodeB3 };

	private BarcodeList barcodeList = new BarcodeList(new HashSet<>());

//	@Test
//	public void testAddFromFile() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testFindBarcodeExists() {
		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupAData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.A, "bar"), testCodeA1);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupBData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.B, "bar"), testCodeB1);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcodeA : groupAData) {
			for (Barcode barcodeB : groupBData) {
				barcodeList.getList().add(barcodeA);
				barcodeList.getList().add(barcodeB);

				assertEquals(barcodeList.findBarcode(SupplierGroup.A, "bar"), testCodeA1);
			}
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcodeA : groupAData) {
			for (Barcode barcodeB : groupBData) {
				barcodeList.getList().add(barcodeA);
				barcodeList.getList().add(barcodeB);
			}
		}

		assertEquals(barcodeList.findBarcode(SupplierGroup.A, "bar"), testCodeA1);
		assertEquals(barcodeList.findBarcode(SupplierGroup.A, "quux"), testCodeA2);
		assertEquals(barcodeList.findBarcode(SupplierGroup.A, "grault"), testCodeA3);

		assertEquals(barcodeList.findBarcode(SupplierGroup.B, "bar"), testCodeB1);
		assertEquals(barcodeList.findBarcode(SupplierGroup.B, "quux"), testCodeB2);
		assertEquals(barcodeList.findBarcode(SupplierGroup.B, "grault"), testCodeB3);
	}

	@Test
	public void testFindBarcodeNotExist() {
		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupAData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.A, "waldo"), null);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupAData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.B, "waldo"), null);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupBData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.B, "waldo"), null);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcode : groupBData) {
			barcodeList.getList().add(barcode);
			assertEquals(barcodeList.findBarcode(SupplierGroup.A, "waldo"), null);
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcodeA : groupAData) {
			for (Barcode barcodeB : groupBData) {
				barcodeList.getList().add(barcodeA);
				barcodeList.getList().add(barcodeB);

				assertEquals(barcodeList.findBarcode(SupplierGroup.A, "waldo"), null);
			}
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcodeA : groupAData) {
			for (Barcode barcodeB : groupBData) {
				barcodeList.getList().add(barcodeA);
				barcodeList.getList().add(barcodeB);

				assertEquals(barcodeList.findBarcode(SupplierGroup.B, "waldo"), null);
			}
		}

		barcodeList = new BarcodeList(new HashSet<>());
		for (Barcode barcodeA : groupAData) {
			for (Barcode barcodeB : groupBData) {
				barcodeList.getList().add(barcodeA);
				barcodeList.getList().add(barcodeB);
			}
		}

		assertEquals(barcodeList.findBarcode(SupplierGroup.A, "waldo"), null);
		assertEquals(barcodeList.findBarcode(SupplierGroup.B, "waldo"), null);

	}

}