package view.ManagerView;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import view.MainView;

public class ManagerMainView  {
	//passed from the container class
		JFrame theFrame;
		// end
		JPanel  containerPanel, headerPanel, contentWrapperPanel, MenuBarPanel;
		JLabel heroTitle;
		JTabbedPane contentTab;
		JMenuBar menuBar;
		JMenu actionsMenu;
		JMenuItem logOutItem ;
		int staffId;
		
		public ManagerMainView(JFrame f, int staff_id, JPanel loginPanel) {
			theFrame = f;
			staffId = staff_id;
			
			containerPanel = new JPanel(new BorderLayout());
//			headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			contentWrapperPanel = new JPanel(new BorderLayout());
			MenuBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
	        menuBar = new JMenuBar();
	        actionsMenu = new JMenu("Action");
	        logOutItem= new JMenuItem("Log Out");


	        // Add the "Log Out" menu item to the "File" menu
	        actionsMenu.add(logOutItem);

	        // Add the "File" menu to the menu bar add add menubar to menubarPanel
	        menuBar.add(actionsMenu);
	        MenuBarPanel.add(menuBar);

			
			// content
			this.contentWrapper(contentWrapperPanel);
			
			containerPanel.add(MenuBarPanel, BorderLayout.NORTH);
			containerPanel.add(contentWrapperPanel, BorderLayout.CENTER);
			
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
			
			
			JPanel ManageResourcePanel = new JPanel();
			JPanel ManageInvoicePanel = new JPanel(new BorderLayout());
			JPanel ManagerProfilePanel = new JPanel();
			
			// fill content
			new ManageResourcePanel(ManageResourcePanel);
			new ManageInvoicePanel(ManageInvoicePanel);
			new ManagerProfilePanel(ManagerProfilePanel, staffId);
			
			contentTab.addTab("Manage Resource", ManageResourcePanel);
			contentTab.addTab("Invoice", ManageInvoicePanel);
			contentTab.addTab("Profile", ManagerProfilePanel);
			
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


