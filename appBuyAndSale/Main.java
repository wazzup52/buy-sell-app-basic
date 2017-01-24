package appBuyAndSale;

import java.io.*;
import java.util.Vector;

import appBuyAndSale.Frames.LoginFrame;

public class Main {

	public static Vector<User> userList = new Vector<User>();
	public static Vector<Product> listOfProducts = new Vector<Product>();

	public static void addUser(User u) {
		userList.addElement(u);
	}

	public static void addProduct(Product p) {
		listOfProducts.addElement(p);
	}

	public static void getUsers() {
		userList = new Vector<User>();
		String fileName = "C:/Users/Keish/AplicatiePOO_9nov/Aplicatie_POO/src/res/Users.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(" ");
				userList.addElement(
						new User(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	public static void getProducts() {
		listOfProducts = new Vector<Product>();
		String fileName = "C:/Users/Keish/AplicatiePOO_9nov/Aplicatie_POO/src/res/Products.txt";
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split("-");
				listOfProducts.addElement(new Product(parts[0], parts[1], Integer.parseInt(parts[2]),
						Double.parseDouble(parts[3]), parts[4]));
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	public static void updateUsersFile() {
		String fileName = "C:/Users/Keish/AplicatiePOO_9nov/Aplicatie_POO/src/res/Users.txt";

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (User u : userList) {
				bufferedWriter.write(u.getFirstName() + " " + u.getLastName() + " " + u.getUsername() + " "
						+ u.getPassword() + " " + u.getEmail() + " " + u.getType());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static void updateProductsFile() {
		String fileName = "C:/Users/Keish/AplicatiePOO_9nov/Aplicatie_POO/src/res/Products.txt";

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Product u : listOfProducts) {
				bufferedWriter.write(u.getKey() + "-" + u.getName() + "-" + u.getQuantity() + "-" + u.getPrice() + "-"
						+ u.getSeller());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public static Product searchProductByKey(String key) {
		for (Product p : listOfProducts)
			if (p.getKey().equals(key))
				return p;
		return null;
	}

	public static Product searchProductByName(String name) {
		for (Product p : listOfProducts)
			if (p.getName().equals(name))
				return p;
		return null;
	}

	public static String[][] returnOwnProducts(String s) {

		String[][] ownProductsList = new String[listOfProducts.size()][4];
		int i = 0;
		for (Product p : listOfProducts) {
			if (p.getSeller().equals(s)) {
				ownProductsList[i][0] = new String(p.getKey().toString());
				ownProductsList[i][1] = new String(p.getName());
				ownProductsList[i][2] = new String(Integer.toString(p.getQuantity()));
				ownProductsList[i][3] = new String(Double.toString(p.getPrice()));
				i++;
			}
		}
		return ownProductsList;

	}

	public static String[][] returnAllProducts() {

		String[][] allProductsList = new String[listOfProducts.size()][5];
		int i = 0;
		for (Product p : listOfProducts) {
			if (p.isInStock()) {
				allProductsList[i][0] = new String(p.getKey().toString());
				allProductsList[i][1] = new String(p.getName());
				allProductsList[i][2] = new String(Integer.toString(p.getQuantity()));
				allProductsList[i][3] = new String(Double.toString(p.getPrice()));
				allProductsList[i][4] = new String(p.getSeller());
				i++;
			}
		}
		return allProductsList;

	}

	public static boolean checkIfProductExists(String key, int q) {
		for (Product p : listOfProducts) {
			if (p.getKey().equals(key)) {
				p.changeQuantity(p.getQuantity() + q);
				return true;
			}
		}
		return false;
	}

	public static boolean checkIfUserExist(String user) {
		for (User u : userList) {
			if (u.getUsername().equals(user)) {
				return true;
			}

		}
		return false;
	}

	public static boolean checkIfEmailExist(String email) {
		for (User u : userList) {
			if (u.getEmail().equals(email)) {
				return true;
			}

		}
		return false;
	}

	public static int checkValidLogin(String username, String password) {
		for (User u : userList) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password))
				return u.getType();
		}
		return 0;
	}

	public static User returnUser(String user, String pass) {
		for (User u : userList) {
			if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
				return u;
			}

		}
		return null;

	}

	public static User returnUserbyName(String user) {
		for (User u : userList) {
			if (u.getUsername().equals(user)) {
				return u;
			}

		}
		return null;

	}

	public static void main(String arr[]) {
		Main.getUsers();
		Main.getProducts();
		new LoginFrame();
	}

}
