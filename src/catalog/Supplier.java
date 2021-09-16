package catalog;

public class Supplier {
	private int ID;
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
