package appBuyAndSale.Frames;

import javax.swing.*;

import appBuyAndSale.Main;

import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
	JFrame frame;
	JLabel l1, l2, l3;
	JTextField userField;
	JButton registerBtn, loginBtn;
	JPasswordField passField;

	public LoginFrame() {
		frame = new JFrame("Login Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(640, 480));

		l1 = new JLabel();
		l1.setPreferredSize(new Dimension(100, 200));
		frame.add(l1, BorderLayout.NORTH);

		// Login Fields
		userField = new JTextField();
		userField.setBounds(25, 50, 125, 30);
		l1.add(userField, BorderLayout.NORTH);
		passField = new JPasswordField();
		passField.setBounds(25, 85, 125, 30);
		l1.add(passField, BorderLayout.NORTH);

		// labels
		l2 = new JLabel("Username");
		l2.setBounds(150, 50, 100, 30);
		l1.add(l2, BorderLayout.NORTH);
		l3 = new JLabel("Password");
		l3.setBounds(150, 85, 100, 30);
		l1.add(l3, BorderLayout.NORTH);

		// Buttons
		loginBtn = new JButton("Login");
		loginBtn.setBounds(25, 120, 125, 25);
		l1.add(loginBtn, BorderLayout.NORTH);
		registerBtn = new JButton("Register");
		registerBtn.setBounds(25, 150, 125, 25);
		l1.add(registerBtn, BorderLayout.NORTH);

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String username = userField.getText();
				String password = passField.getText();
				int check = Main.checkValidLogin(username, password);
				if (check == 1) {

					buyerLoggedOn blogFrame = new buyerLoggedOn(Main.returnUser(username, password));
					frame.dispose();
				} else if (check == 2) {
					sellerLoggedOn logFrame = new sellerLoggedOn(Main.returnUser(username, password));
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Password / Username");
					userField.setText("");
					passField.setText("");
					userField.requestFocus();
				}
			}
		});

		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				RegisterFrame regFrame = new RegisterFrame();
				frame.dispose();

			}
		});

		// Display the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}