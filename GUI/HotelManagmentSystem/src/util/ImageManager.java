package util;

import java.awt.*;

import javax.swing.*;

public class ImageManager {
	ImageIcon icon ;
	Image image;
	
	public JLabel imageFiller(String imgPath, Dimension d) {
		
		icon = new ImageIcon(imgPath);
		
		if(d == null) {
			image = icon.getImage();
		} else {			
			// Get the image from the ImageIcon
			image = icon.getImage().getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
		}
		
        // Create a JLabel and set the image as its icon
        JLabel label = new JLabel(new ImageIcon(image));
        
        
        return label;
	}
	

}
