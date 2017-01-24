package appBuyAndSale;

import java.util.Vector;

public class Buyer extends User {

	Vector<Product> cart;

	public Buyer(String fN, String lN, String u, String p, String e) {
		super(fN, lN, u, p, e, 1);

	}

	public void addToCart(Product p) {
		cart.addElement(p);
	}

}
