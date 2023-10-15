package view;

import javax.swing.*;


public class MainView {

	public static void main(String[] args) {
		System.out.println("hello");
		JFrame theFrame = new JFrame("Hotel Managment System");

		new LoginUI(theFrame);
		theFrame.setSize(1000,800);
//		theFrame.pack();
//		
		theFrame.setVisible(true);
		theFrame.setResizable(false);
		
	}

}
