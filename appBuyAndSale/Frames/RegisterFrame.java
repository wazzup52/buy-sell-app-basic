package appBuyAndSale.Frames;

import javax.swing.*;

import appBuyAndSale.Buyer;
import appBuyAndSale.Main;
import appBuyAndSale.Seller;

import java.awt.*;
import java.awt.event.*;

public class RegisterFrame extends JFrame implements ActionListener {
	JFrame frame;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JTextField userField, emailField, firstNameField, lastNameField;
	JRadioButton sellerBox, buyerBox;
	JButton registerBtn, backBtn;
	JPasswordField passField, retypePassField;

	RegisterFrame() {
		frame = new JFrame("Login Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640, 480));

		l1 = new JLabel();
		l1.setPreferredSize(new Dimension(100, 480));
		frame.add(l1, BorderLayout.NORTH);

		// Login Fields
		firstNameField = new JTextField();
		firstNameField.setBounds(10, 10, 140, 30);
		l1.add(firstNameField, BorderLayout.NORTH);

		lastNameField = new JTextField();
		lastNameField.setBounds(10, 50, 140, 30);
		l1.add(lastNameField, BorderLayout.NORTH);

		userField = new JTextField();
		userField.setBounds(10, 90, 140, 30);
		l1.add(userField, BorderLayout.NORTH);

		passField = new JPasswordField();
		passField.setBounds(10, 130, 140, 30);
		l1.add(passField, BorderLayout.NORTH);

		retypePassField = new JPasswordField();
		retypePassField.setBounds(10, 170, 140, 30);
		l1.add(retypePassField, BorderLayout.NORTH);

		emailField = new JTextField();
		emailField.setBounds(10, 210, 140, 30);
		l1.add(emailField, BorderLayout.NORTH);

		sellerBox = new JRadioButton("Vanzator");
		sellerBox.setBounds(10, 250, 140, 30);
		l1.add(sellerBox, BorderLayout.NORTH);
		buyerBox = new JRadioButton("Cumparator");
		buyerBox.setBounds(10, 280, 140, 30);
		l1.add(buyerBox, BorderLayout.NORTH);
		ButtonGroup group = new ButtonGroup();
		group.add(buyerBox);
		group.add(sellerBox);

		// labels
		l2 = new JLabel("Nume");
		l2.setBounds(150, 10, 100, 30);
		l1.add(l2, BorderLayout.NORTH);
		l3 = new JLabel("Prenume");
		l3.setBounds(150, 50, 100, 30);
		l1.add(l3, BorderLayout.NORTH);
		l4 = new JLabel("Username");
		l4.setBounds(150, 90, 100, 30);
		l1.add(l4, BorderLayout.NORTH);
		l5 = new JLabel("Password");
		l5.setBounds(150, 130, 100, 30);
		l1.add(l5, BorderLayout.NORTH);
		l6 = new JLabel("Retype password");
		l6.setBounds(150, 170, 100, 30);
		l1.add(l6, BorderLayout.NORTH);
		l7 = new JLabel("E-mail");
		l7.setBounds(150, 210, 100, 30);
		l1.add(l7, BorderLayout.NORTH);

		// Buttons
		registerBtn = new JButton("Register");
		registerBtn.setBounds(15, 330, 125, 25);
		l1.add(registerBtn, BorderLayout.NORTH);

		registerBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ae) {
				String username = userField.getText();
				String fN = firstNameField.getText();
				String lN = lastNameField.getText();
				String eM = emailField.getText();
				String password = passField.getText();
				String rpassword = retypePassField.getText();
				if (!password.equals(rpassword)) {
					JOptionPane.showMessageDialog(null, "Parolele sunt diferite .", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					passField.setText("");
					retypePassField.setText("");
				} else if (Main.checkIfUserExist(username) || Main.checkIfEmailExist(eM)) {
					JOptionPane.showMessageDialog(null, "Inregistrare esuata.Username/email deja inregistrat",
							"Warning!", JOptionPane.WARNING_MESSAGE);
					userField.setText("");
					passField.setText("");
					retypePassField.setText("");
					emailField.setText("");
				} else {
					if (buyerBox.isSelected()) {
						Main.addUser(new Seller(fN, lN, username, password, eM));
						Main.updateUsersFile();
					} else {
						Main.addUser(new Buyer(fN, lN, username, password, eM));
						Main.updateUsersFile();

					}

					JOptionPane.showMessageDialog(null, "Inregistrare cu succes.", "Warning!",
							JOptionPane.WARNING_MESSAGE);
					LoginFrame logFrame = new LoginFrame();
					frame.dispose();
				}

			}
		});

		backBtn = new JButton("Back");
		backBtn.setBounds(15, 370, 125, 25);
		l1.add(backBtn, BorderLayout.NORTH);

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				LoginFrame logFrame = new LoginFrame();
				frame.dispose();
			}
		});

		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}