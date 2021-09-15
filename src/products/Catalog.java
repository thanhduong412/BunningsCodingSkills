package products;

public class Catalog {
	private String sku;
	private String desc;
	
	public Catalog(String sku, String desc) {
		this.sku = sku;
		this.desc = desc;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String toString() {
		return String.format("%s, %s", sku, desc);
	}
}
