package view.GuestView.orderPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// custom package
import Model.OrderData;
import constants.FilePaths;
import util.FileHandler;


class OrderItem implements ActionListener {
	JButton removeBtn;
	JPanel parentPanel, itemPanel;
	OrderData order;
	OrderData.Category category;
	
	public OrderItem(JPanel p, OrderData od) {
		parentPanel = p;
		itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 105, 5));
		itemPanel.setBackground(new Color(255,255,255));
		
		removeBtn = new JButton("remove");
		removeBtn.setForeground(new Color(200, 20, 20));
		removeBtn.setPreferredSize(new Dimension(100,40));
		removeBtn.addActionListener(this);
		
		
		order = od;
		
		order.item_name = order.item_name;
        order.qty = order.qty;
        order.unit_price = order.unit_price;
        category = order.category ;
        
        itemPanel.add(new JLabel(order.item_name));
        itemPanel.add(new JLabel(category.toString()));
        itemPanel.add(new JLabel((order.qty ).toString()));
        itemPanel.add(new JLabel((order.unit_price).toString()));
        itemPanel.add(removeBtn);
        
        parentPanel.add(itemPanel);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?",
				"Delete Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (choice == JOptionPane.YES_OPTION) {
        	// Perform the delete operation

        	// delete from file
        	
        	if(category == OrderData.Category.MENU ) {
        		
            	FileHandler fileMan = new FileHandler(FilePaths.menuFile);
            	String line = order.id.toString() +","+ order.qty.toString();
            	fileMan.deleteLine(line);
            	
        	} else if(category == OrderData.Category.ROOM) {
            	FileHandler fileMan = new FileHandler(FilePaths.roomFile);
            	String line = order.id.toString();
            	fileMan.deleteLine(line);
        	}
        	
        	// remove the item from GUI
        	parentPanel.remove(itemPanel);
        	parentPanel.revalidate();
        	parentPanel.repaint();
        	
        } else {
            // User clicked "No" or closed the dialog
            // Do nothing or handle cancellation
        	System.out.println("user said no");
        }
	}
}
