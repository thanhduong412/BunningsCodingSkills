package products;

public class Barcode {
	private int supplierID;
	private String sku;
	private String barcode;

	public Barcode(int supplierID, String sku, String barcode) {
		this.supplierID = supplierID;
		this.sku = sku;
		this.barcode = barcode;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
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
		return String.format("%d, %s, %s ", supplierID, sku, barcode);
	}
}
