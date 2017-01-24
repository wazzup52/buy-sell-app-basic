package appBuyAndSale.Frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import appBuyAndSale.Main;
import appBuyAndSale.Product;
import appBuyAndSale.User;

public class sellerLoggedOn {
	JFrame frame;
	JLabel mainLabel, welcomeLabel;
	JButton changePassBtn, showOwnProductsBtn, addProductBtn, logoutBtn;
	// Change Password declarations
	JLabel oldPass, newPass, retypePass, changePass;
	JTextField oldPassField, newPassField, retypePassField;
	JButton changePassFormBtn;
	// Show products
	String[] columnNames = { "Cod", "Denumire", "Cantitate", "Pret" };
	JTable productTable;
	JScrollPane productTablePane;
	// Add new product
	JTextField keyField, nameField, quantityField, priceField;
	JLabel key, name, quantity, price;
	JButton addProductFormBtn;

	sellerLoggedOn(User s) {
		frame = new JFrame("Login Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640, 480));

		mainLabel = new JLabel();
		mainLabel.setPreferredSize(new Dimension(640, 480));
		frame.getContentPane().add(mainLabel, BorderLayout.WEST);

		String welcomeMsg = new String("Salut , " + s.getFirstName() + " " + s.getLastName() + " !");
		welcomeLabel = new JLabel(welcomeMsg);
		welcomeLabel.setBounds(0, 0, 200, 25);
		mainLabel.add(welcomeLabel);

		// Buttons
		changePassBtn = new JButton("Change Password");
		changePassBtn.setBounds(0, 30, 150, 25);
		mainLabel.add(changePassBtn);
		{
			// Change Password stage
			changePass = new JLabel("Change password : ");
			changePass.setBounds(320, 70, 150, 25);
			mainLabel.add(changePass);
			changePass.setVisible(false);
			{
				oldPassField = new JTextField();
				oldPassField.setBounds(320, 100, 150, 25);
				mainLabel.add(oldPassField);
				oldPassField.setVisible(false);

				oldPass = new JLabel("Enter old password ");
				oldPass.setBounds(470, 100, 150, 25);
				mainLabel.add(oldPass);
				oldPass.setVisible(false);
			}
			{
				newPassField = new JTextField();
				newPassField.setBounds(320, 130, 150, 25);
				mainLabel.add(newPassField);
				newPassField.setVisible(false);

				newPass = new JLabel("Enter new password ");
				newPass.setBounds(470, 130, 150, 25);
				mainLabel.add(newPass);
				newPass.setVisible(false);
			}
			{
				retypePassField = new JTextField();
				retypePassField.setBounds(320, 160, 150, 25);
				mainLabel.add(retypePassField);
				retypePassField.setVisible(false);

				retypePass = new JLabel("Retype new password ");
				retypePass.setBounds(470, 160, 150, 25);
				mainLabel.add(retypePass);
				retypePass.setVisible(false);

			}
			changePassFormBtn = new JButton("Change Password");
			changePassFormBtn.setBounds(320, 190, 150, 25);
			mainLabel.add(changePassFormBtn);
			changePassFormBtn.setVisible(false);
			changePassBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changePassForm(true);
					productTablePane.setVisible(false);
					showAddProductForm(false);
				}
			});
			changePassFormBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String op = oldPassField.getText();
					String p = newPassField.getText();
					String np = retypePassField.getText();
					if (p.equals(np) && op.equals(s.getPassword())) {
						s.changePassword(p);
						JOptionPane.showMessageDialog(null, "Password change succeded.");
						Main.updateUsersFile();
						Main.getUsers();
						changePassForm(false);
					} else
						JOptionPane.showMessageDialog(null, "Passwords don't match or wrong old password");
				}
			});

		}

		showOwnProductsBtn = new JButton("Show own Products");
		showOwnProductsBtn.setBounds(0, 60, 150, 25);
		mainLabel.add(showOwnProductsBtn);
		{
			showOwnProductsBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					productTable = new JTable(Main.returnOwnProducts(s.getUsername()), columnNames);
					productTable.setEnabled(false);
					productTablePane = new JScrollPane(productTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					productTablePane.setBounds(320, 50, 300, 300);

					mainLabel.add(productTablePane);
					productTablePane.setVisible(true);
					productTable = new JTable(Main.returnOwnProducts(s.getUsername()), columnNames);
					changePassForm(false);
					showAddProductForm(false);
				}
			});
		}
		addProductBtn = new JButton("Add new product");
		addProductBtn.setBounds(0, 90, 150, 25);
		mainLabel.add(addProductBtn);
		{
			keyField = new JTextField();
			keyField.setBounds(320, 50, 150, 25);
			mainLabel.add(keyField);
			keyField.setVisible(false);

			key = new JLabel("Cod produs");
			key.setBounds(470, 50, 150, 25);
			mainLabel.add(key);
			key.setVisible(false);

			nameField = new JTextField();
			nameField.setBounds(320, 80, 150, 25);
			mainLabel.add(nameField);
			nameField.setVisible(false);

			name = new JLabel("Nume produs");
			name.setBounds(470, 80, 150, 25);
			mainLabel.add(name);
			name.setVisible(false);

			quantityField = new JTextField();
			quantityField.setBounds(320, 110, 150, 25);
			mainLabel.add(quantityField);
			quantityField.setVisible(false);

			quantity = new JLabel("Cantitate");
			quantity.setBounds(470, 110, 150, 25);
			mainLabel.add(quantity);
			quantity.setVisible(false);

			priceField = new JTextField();
			priceField.setBounds(320, 140, 150, 25);
			mainLabel.add(priceField);
			priceField.setVisible(false);

			price = new JLabel("Pret");
			price.setBounds(470, 140, 150, 25);
			mainLabel.add(price);
			price.setVisible(false);

			addProductFormBtn = new JButton("Add product");
			addProductFormBtn.setBounds(320, 170, 150, 25);
			mainLabel.add(addProductFormBtn);
			addProductFormBtn.setVisible(false);
			addProductBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showAddProductForm(true);
					productTablePane.setVisible(false);
					changePassForm(false);

				}
			});
			addProductFormBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String k = keyField.getText();
					String n = nameField.getText();
					int q = Integer.parseInt(quantityField.getText());
					double p = Double.parseDouble(priceField.getText());
					String seller = s.getUsername();
					if (Main.checkIfProductExists(k, q)) {
						JOptionPane.showMessageDialog(null, "Produsul exista deja . Adaugat noua cantitate");
						showAddProductForm(false);
						Main.updateProductsFile();
						Main.getProducts();
					} else {
						JOptionPane.showMessageDialog(null, "Ati adaugat cu succes");
						showAddProductForm(false);
						Main.addProduct(new Product(k, n, q, p, seller));
						Main.updateProductsFile();
						Main.getProducts();
					}
				}

			});
		}
		logoutBtn = new JButton("Log out");
		logoutBtn.setBounds(0, 400, 150, 25);
		mainLabel.add(logoutBtn);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame lF = new LoginFrame();
				frame.dispose();
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	protected void showAddProductForm(boolean t) {
		keyField.setVisible(t);
		key.setVisible(t);
		nameField.setVisible(t);
		name.setVisible(t);
		quantityField.setVisible(t);
		quantity.setVisible(t);
		priceField.setVisible(t);
		price.setVisible(t);
		addProductFormBtn.setVisible(t);
	}

	protected void changePassForm(boolean t) {
		changePass.setVisible(t);
		oldPass.setVisible(t);
		oldPassField.setVisible(t);
		newPass.setVisible(t);
		newPassField.setVisible(t);
		retypePass.setVisible(t);
		retypePassField.setVisible(t);
		changePassFormBtn.setVisible(t);

	}

	protected void showOwnProducts(JLabel mainL) {

	}

}