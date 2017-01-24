package appBuyAndSale;

public class Product {
	String key;
	String name;
	int quantity;
	double price;
	String seller;

	public Product(String k, String n, int q, double p, String s) {
		this.key = k;
		this.name = n;
		this.quantity = q;
		this.price = p;
		this.seller = s;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getSeller() {
		return seller;
	}

	public boolean isInStock() {
		if (quantity > 0)
			return true;
		else
			return false;
	}

	public void changeQuantity(int q) {
		quantity = q;
	}

	public void changePrice(double p) {
		price = p;
	}
}
