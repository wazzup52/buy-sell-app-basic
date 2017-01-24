package appBuyAndSale.Frames;

import javax.swing.*;

import appBuyAndSale.Main;
import appBuyAndSale.Product;
import appBuyAndSale.User;

import java.awt.*;
import java.awt.event.*;

public class buyFrame {
	JFrame frame ;
	JTextField quantityField;
	JLabel mainLabel , quantityLabel;
	JButton buyButton , closeButton;
	JTable productTable;
	JScrollPane productTablePane;
	
	buyFrame(User u, Product p)
	{
		frame = new JFrame("Buy windows");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(420,240));
        
        String[] columnNames = {"Cod","Denumire","Cantitate","Pret","Vanzator"};
        String[][] tableRow = {{p.getKey() , p.getName() , Integer.toString(p.getQuantity()) ,Double.toString(p.getPrice()) , p.getSeller()}};

        mainLabel = new JLabel();
        mainLabel.setPreferredSize(new Dimension(420,240 ));
        frame.getContentPane().add(mainLabel, BorderLayout.WEST);
        
        productTable = new JTable(tableRow,columnNames);
        productTablePane = new JScrollPane(productTable, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        productTablePane.setBounds(30, 20, 360, 100);
		mainLabel.add(productTablePane);
		
		quantityLabel = new JLabel("Introduceti cantitatea");
		quantityLabel.setBounds(10,130,130,30);
		mainLabel.add(quantityLabel);
		quantityField = new JTextField();
		quantityField.setBounds(10, 160, 100, 30);
        mainLabel.add(quantityField);
        
        buyButton = new JButton("Buy");
        buyButton.setBounds(110, 160, 100, 30);
        mainLabel.add(buyButton);
        buyButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		int q = Integer.parseInt(quantityField.getText());
        		if(p.getQuantity() - q >= 0)
        		{
        			p.changeQuantity(p.getQuantity() - q);
        			Main.updateProductsFile();
        			frame.dispose();
        		}
        		else 
        			JOptionPane.showMessageDialog(null,"Introduceti o cantitate mai mica");
        			
        	}
        });
        
        closeButton = new JButton("Close");
        closeButton.setBounds(210,160,100,30);
        mainLabel.add(closeButton);
        closeButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		frame.dispose();
        	}
        });
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
