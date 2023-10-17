package view.GuestView.orderPanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

// local package
import Model.OrderData;



public class OrderPanel {
	JLabel itemNameLabel, categoryLabel , qtyLabel , unit_priceLabel;
	JPanel wrapperPanel, itemWrapperPanel, itemPanel, colNamePanel;
	JButton removeBtn;
	
	public OrderPanel(JPanel p)  {
		wrapperPanel = new JPanel();
		colNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 0));
		itemWrapperPanel = new JPanel();
		
		wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
		wrapperPanel.add(Box.createVerticalStrut(10));
		
		itemWrapperPanel.setLayout(new BoxLayout(itemWrapperPanel, BoxLayout.Y_AXIS));
		itemWrapperPanel.add(Box.createVerticalStrut(10));
		
		
		itemNameLabel = new JLabel("Name");
		categoryLabel = new JLabel("Category");
		qtyLabel = new JLabel("Quantity");
		unit_priceLabel = new JLabel("Unit Price");
		
		colNamePanel.add(itemNameLabel);
		colNamePanel.add(categoryLabel);
		colNamePanel.add(qtyLabel);
		colNamePanel.add(unit_priceLabel);
		
		this.itemWrapper(itemWrapperPanel);
		
		wrapperPanel.add(colNamePanel);
		wrapperPanel.add(itemWrapperPanel);
		
		p.add(wrapperPanel);
		
	}
	
	public void itemWrapper(JPanel p) {
		// fetch from db
		for(int i = 0; i < 5; i++) {
			
			OrderData order = itemFill("item name", 1, 10.23 + i, "food", i);
	        
			new OrderItem(p, order);
		}

	}
	
	public OrderData itemFill(String item_name, int qty, double unit_price, String category, int menu_id) {
		OrderData order = new OrderData();
		
		// dummy data
		order.item_name = item_name;
        order.qty = qty;
        order.unit_price = unit_price;
        order.menu_id = menu_id;

        // Set the Category enum
        if(category.equals("food")) {	        	
        	order.category = OrderData.Category.FOOD;
        } else order.category = OrderData.Category.DRINK;
        
        return order;
	}
	
}
