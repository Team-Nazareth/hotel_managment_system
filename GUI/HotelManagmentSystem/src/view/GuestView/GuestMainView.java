package view.GuestView;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.*;

// custom package
import view.MainView;
import view.GuestView.invoicePanel.InvoicePanel;
import view.GuestView.orderPanel.OrderPanel;
import view.GuestView.profilePanel.ProfilePanel;


public class GuestMainView {
	//passed from the container class
	JFrame theFrame;
	// end
	JPanel  containerPanel, headerPanel, contentWrapperPanel, MenuBarPanel;
	JLabel heroTitle;
	JTabbedPane contentTab;
	JMenuBar menuBar;
	JMenu actionsMenu;
	JMenuItem logOutItem ;
	int guestId;
	
	public GuestMainView(JFrame f, int guest_id, JPanel loginPanel) {
		theFrame = f;
		guestId = guest_id;
		
		containerPanel = new JPanel(new BorderLayout());
		headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contentWrapperPanel = new JPanel(new BorderLayout());
		MenuBarPanel = new JPanel(new BorderLayout());
		
        menuBar = new JMenuBar();
        actionsMenu = new JMenu("Action");
        logOutItem= new JMenuItem("Log Out");


        // Add the "Log Out" menu item to the "File" menu
        actionsMenu.add(logOutItem);

        // Add the "File" menu to the menu bar
        menuBar.add(actionsMenu);
        MenuBarPanel.add(menuBar, BorderLayout.WEST);
        
		heroTitle = new JLabel("Discover Luxury and Comfort");
		heroTitle.setFont(new Font("Arial", Font.ITALIC, 35));
		
		headerPanel.setBackground(new Color(255,255,255));
		headerPanel.add(heroTitle);
		
		// content
		this.contentWrapper(contentWrapperPanel);
		
		containerPanel.add(MenuBarPanel, BorderLayout.NORTH);
		containerPanel.add(headerPanel, BorderLayout.CENTER);
		containerPanel.add(contentWrapperPanel, BorderLayout.SOUTH);
		
		theFrame.add(containerPanel);
		
        logOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	MainView.panelRemover(theFrame, containerPanel);
            	theFrame.add(loginPanel);
            	MainView.repainter(theFrame);
            	
                System.out.println("Log Out clicked");
            }
        });
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
		JPanel invoicePanel = new JPanel(new BorderLayout());
		JPanel profilePanel = new JPanel();
		
		// fill content
		new ExplorePanel(explorePanel);
		new OrderPanel(orderPanel, guestId);
		new InvoicePanel(invoicePanel, guestId);
		new ProfilePanel(profilePanel, guestId);
		
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

