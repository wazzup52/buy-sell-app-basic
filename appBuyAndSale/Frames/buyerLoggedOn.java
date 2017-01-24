package appBuyAndSale.Frames;

import javax.swing.*;

import appBuyAndSale.Main;
import appBuyAndSale.Product;
import appBuyAndSale.User;

import java.awt.*;
import java.awt.event.*;

public class buyerLoggedOn {
	JFrame frame;
	JLabel mainLabel , welcomeLabel;
	JButton changePassBtn,showProductsBtn,searchProductBtn,logoutBtn;
	//Change Password declarations
	JLabel oldPass,newPass,retypePass ,changePass;
	JTextField oldPassField,newPassField,retypePassField;
	JButton changePassFormBtn;
	//Show Products
	String[] columnNames = {"Cod","Denumire","Cantitate","Pret","Vanzator"};
	JTable productsTable;
	JScrollPane productsTablePane;
	//Search products and Buy
	JButton searchProductByNameBtn,searchProductByCodeBtn;
	JTextField searchField;
	JLabel searchLabel;
	buyerLoggedOn(User b)
	{
		frame = new JFrame("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(640,480));

        mainLabel = new JLabel();
        mainLabel.setPreferredSize(new Dimension(640,480 ));
        frame.getContentPane().add(mainLabel, BorderLayout.WEST);
        
        String welcomeMsg = new String("Salut , " + b.getFirstName() + " " + b.getLastName() + " !");
        welcomeLabel = new JLabel(welcomeMsg);
        welcomeLabel.setBounds(0, 0, 200, 25);
        mainLabel.add(welcomeLabel);
       
        //Buttons
        changePassBtn = new JButton("Change Password");
        changePassBtn.setBounds(0,40,150,25);
        mainLabel.add(changePassBtn);
        changePassFormBtn = new JButton("Change Password");
        changePassFormBtn.setBounds(320,190,150,25);
        mainLabel.add(changePassFormBtn);
        changePassFormBtn.setVisible(false);
        
        //Change Password stage
        changePass= new JLabel("Change password : ");
    	changePass.setBounds(320, 70, 150, 25);
    	mainLabel.add(changePass);
    	changePass.setVisible(false);
        { 
        	oldPassField = new JTextField();
        	oldPassField.setBounds(320, 100, 150, 25);
        	mainLabel.add(oldPassField);
        	oldPassField.setVisible(false);
        
        	oldPass= new JLabel("Enter old password ");
        	oldPass.setBounds(470, 100, 150, 25);
        	mainLabel.add(oldPass);
        	oldPass.setVisible(false);
        }
        {
        	newPassField = new JTextField();
        	newPassField.setBounds(320, 130, 150, 25);
        	mainLabel.add(newPassField);
        	newPassField.setVisible(false);
	        
        	newPass= new JLabel("Enter new password ");
        	newPass.setBounds(470, 130, 150, 25);
        	mainLabel.add(newPass);
        	newPass.setVisible(false);
        }
        {
        	retypePassField = new JTextField();
        	retypePassField.setBounds(320, 160, 150, 25);
        	mainLabel.add(retypePassField);
        	retypePassField.setVisible(false);
	        
        	retypePass= new JLabel("Retype new password ");
        	retypePass.setBounds(470, 160, 150, 25);
        	mainLabel.add(retypePass);
        	retypePass.setVisible(false);
        	
        }
        //Buttons
        changePassBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		changePassForm(true);
        		productsTablePane.setVisible(false);
        	}
        });
        changePassFormBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		String op = oldPassField.getText();
        		String p = newPassField.getText();
        		String np = retypePassField.getText();
        		if(p.equals(np) && op.equals(b.getPassword()))
        		{
        			b.changePassword(p);
        			JOptionPane.showMessageDialog(null,"Password change succeded.");
        			Main.updateUsersFile();
        			Main.getUsers();
        			changePassForm(false);
        		}
        		else
        			JOptionPane.showMessageDialog(null,"Passwords don't match or wrong old password");
        	}
        });
        //Show products
        showProductsBtn = new JButton("Show all Products");
        showProductsBtn.setBounds(0,70,150,25);
        mainLabel.add(showProductsBtn);
        {
        	showProductsBtn.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
        			productsTable = new JTable(Main.returnAllProducts(),columnNames);
        			productsTable.setEnabled(false);
        			productsTablePane = new JScrollPane(productsTable, 
            				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            		productsTablePane.setBounds(320,50,300,300);

            		mainLabel.add(productsTablePane);
        			productsTablePane.setVisible(true);
        			productsTable = new JTable(Main.returnAllProducts(),columnNames);
        			changePassForm(false);
        		}
        	});
        }
        searchField = new JTextField();
        searchField.setBounds(0, 180, 150, 25);
        mainLabel.add(searchField);
        searchLabel = new JLabel("Codul/Numele produsului de cautat");
        searchLabel.setBounds(0, 150 ,200, 25);
        mainLabel.add(searchLabel);
        searchProductByNameBtn = new JButton("Cauta dupa nume");
        searchProductByNameBtn.setBounds(0, 210, 150, 25);
        mainLabel.add(searchProductByNameBtn);
        searchProductByNameBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		Product p;
        		String n = searchField.getText();
        		if( (p = Main.searchProductByName(n)) != null)
        		{
        			if(p.isInStock())
        			{
        				buyFrame bFrame = new buyFrame(b,p);
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(null,"Produsul nu este in stoc.");
        			}
        		}
        		else 
        		{
        			JOptionPane.showMessageDialog(null,"Produsul cu acest nume nu exista.");
        			searchField.setText("");
        		}
        	}
        });
        searchProductByCodeBtn = new JButton("Cauta dupa cod");
        searchProductByCodeBtn.setBounds(0, 240, 150, 25);
        mainLabel.add(searchProductByCodeBtn);
        searchProductByCodeBtn.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		Product p;
        		String n = searchField.getText();
        		if( (p = Main.searchProductByKey(n)) != null)
        		{
        			if(p.isInStock())
        			{
        				buyFrame bFrame = new buyFrame(b,p);
        			}
        			else
        			{
        				JOptionPane.showMessageDialog(null,"Produsul nu este in stoc.");
        			}
        		}
        		else 
        		{
        			JOptionPane.showMessageDialog(null,"Produsul cu acest nume nu exista.");
        			searchField.setText("");
        		}
        	}
        });
        logoutBtn = new JButton("Log out");
        logoutBtn.setBounds(0, 400, 150, 25);
        mainLabel.add(logoutBtn);
        logoutBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e)
        	{
        		LoginFrame lF = new LoginFrame();
        		frame.dispose();
        	}
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	

	protected void changePassForm(boolean t) 
	{
		changePass.setVisible(t);
		oldPass.setVisible(t); oldPassField.setVisible(t);
		newPass.setVisible(t); newPassField.setVisible(t);
		retypePass.setVisible(t); retypePassField.setVisible(t);
		changePassFormBtn.setVisible(t);
		
	}
	

}
