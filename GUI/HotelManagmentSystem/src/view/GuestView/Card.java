package view.GuestView;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import util.FileHandler;
import util.ImageManager;

public class Card implements ActionListener {
	JPanel CardPanel, cardBodyPanel;
	JButton addBtn;
	JLabel NameLabel, DescLabel, RateLabel, id;
	FileHandler fileManager;
	
	public Card(JPanel p) {
		
		CardPanel = new JPanel(new BorderLayout());
		CardPanel.setBackground(new Color(255,255,255));
		CardPanel.setPreferredSize(new Dimension(300, 270));
		
		this.imageManger(CardPanel);
		
		cardBodyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		cardBodyPanel.setPreferredSize(new Dimension(10,0));
		
		addBtn = new JButton("Add To Cart");
		addBtn.addActionListener(this);
		
		CardPanel.add(cardBodyPanel, BorderLayout.CENTER);
		CardPanel.add(addBtn, BorderLayout.SOUTH);
		
		p.add(CardPanel);
		
	}
	
	public void imageManger(JPanel p) {
		
		ImageManager im = new ImageManager();
		JLabel label = im.imageFiller("./Assets/jason.jpg", new Dimension(300, 200));
		
		p.add(label, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent e) {
	}
	
}
