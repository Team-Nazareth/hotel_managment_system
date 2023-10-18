package view.GuestView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.*;

import view.GuestView.invoicePanel.InvoicePanel;
// custom package
import view.GuestView.orderPanel.OrderPanel;
import view.GuestView.profilePanel.ProfilePanel;


public class GuestMainView {
	//passed from the container class
	JFrame theFrame;
	// end
	JPanel  containerPanel, headerPanel, contentWrapperPanel;
	JLabel heroTitle;
	JTabbedPane contentTab;
	
	public GuestMainView(JFrame f) {
		theFrame = f;
		
		containerPanel = new JPanel(new BorderLayout());
		headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentWrapperPanel = new JPanel(new BorderLayout());
		
		heroTitle = new JLabel("Discover Luxury and Comfort");
		heroTitle.setFont(new Font("Arial", Font.ITALIC, 35));
		
		headerPanel.setBackground(new Color(255,255,255));
		headerPanel.add(heroTitle);
		
		// content
		this.contentWrapper(contentWrapperPanel);
		
		// 
		containerPanel.add(headerPanel, BorderLayout.NORTH);
		containerPanel.add(contentWrapperPanel, BorderLayout.CENTER);
		
		theFrame.add(containerPanel);
	}
	
	private void contentWrapper(JPanel p) {
		// modify default UI manager
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        // manipulate the tab size/padding and font size

		contentTab = new JTabbedPane(JTabbedPane.LEFT);
		contentTab.setUI(new CustomTabbedPaneUI());
		
		
		JPanel explorePanel = new JPanel();
		JPanel orderPanel = new JPanel();
		JPanel invoicePanel = new JPanel();
		JPanel profilePanel = new JPanel();
		
		// fill content
		new ExplorePanel(explorePanel);
		new OrderPanel(orderPanel);
		new InvoicePanel(invoicePanel);
		new ProfilePanel(profilePanel);
		
		contentTab.addTab("Explore", explorePanel);
		contentTab.addTab("Orders", orderPanel);
		contentTab.addTab("Invoice", invoicePanel);
		contentTab.addTab("Profile", profilePanel);
		
		p.add(contentTab, BorderLayout.WEST);
	}
	
	private static class CustomTabbedPaneUI extends BasicTabbedPaneUI {
		
		private static final Color TAB_BACKGROUND_COLOR = Color.LIGHT_GRAY;
		
        @Override
        protected void installDefaults() {
            super.installDefaults();
  

            // Modify tab font size
            Font font = tabPane.getFont().deriveFont(Font.BOLD, 20); // Set the desired font and size
            tabPane.setFont(font);
            tabPane.setBackground(TAB_BACKGROUND_COLOR);
        }
        
    }
	
}

