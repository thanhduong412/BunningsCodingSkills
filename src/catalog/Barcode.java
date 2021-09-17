package catalog;

/**
 * @author Thanh Duong
 * @version 1.0
 */

public class Barcode {
	/**
	 * Represents the supplier associated with the barcode
	 */
	private Supplier supplier;
	private String sku;
	private String barcode;

	public Barcode(Supplier supplier, String sku, String barcode) {
		this.supplier = supplier;
		this.sku = sku;
		this.barcode = barcode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String toString() {
		return "Barcode [supplier=" + supplier + ", sku=" + sku + ", barcode=" + barcode + "]";
	}

}
