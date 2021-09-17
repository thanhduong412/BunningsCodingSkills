package catalog;

/**
 * @author Thanh Duong
 * @version 1.0
 */

public class Supplier {
	private int ID;
	/**
	 * Represents the group this supplier belongs to
	 */
	private SupplierGroup group;
	private String name;

	public Supplier(int ID, SupplierGroup group, String name) {
		this.ID = ID;
		this.group = group;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public SupplierGroup getGroup() {
		return group;
	}

	public void setGroup(SupplierGroup group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "Supplier [ID=" + ID + ", group=" + group + ", name=" + name + "]";
	}

}
