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
import util.FileHandler;


class OrderItem implements ActionListener {
	JButton removeBtn;
	JPanel parentPanel, itemPanel;
	OrderData order;
	public OrderItem(JPanel p, OrderData od) {
		parentPanel = p;
		itemPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 100, 10));
		itemPanel.setBackground(new Color(255,255,255));
		
		removeBtn = new JButton("remove");
		removeBtn.setForeground(new Color(200, 20, 20));
		removeBtn.setPreferredSize(new Dimension(100,40));
		removeBtn.addActionListener(this);
		
		
		order = od;
		
		order.item_name = order.item_name;
        order.qty = order.qty;
        order.unit_price = order.unit_price;
        OrderData.Category category = order.category ;
        
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
        	FileHandler fileMan = new FileHandler("./Assets/menu.csv");
        	String line = order.menu_id.toString() +","+ order.qty.toString();
        	fileMan.deleteLine(line);
        	
        	
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
