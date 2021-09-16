package catalog;

public class Product {
	private String sku;
	private String productName;
	private Barcode barcode;
	private Supplier supplier;

	public Product(String sku, String productName, Barcode barcode, Supplier supplier) {
		this.sku = sku;
		this.productName = productName;
		this.barcode = barcode;
		this.supplier = supplier;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Barcode getBarcode() {
		return barcode;
	}

	public void setBarcode(Barcode barcode) {
		this.barcode = barcode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String toString() {
		return String.format("%s, %s, %s", sku, productName, supplier.getGroup().toString());
	}

}
