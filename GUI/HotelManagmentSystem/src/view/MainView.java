package view;

import javax.swing.*;


public class MainView {
	
	public static void panelRemover(JFrame f ,JPanel p) {
		f.remove(p);
	}
	
	public static void repainter(JFrame f) {
		f.revalidate();
		f.repaint();
	}

	public static void main(String[] args) {
		System.out.println("hello");
		JFrame theFrame = new JFrame("Hotel Managment System");

		new LoginUI(theFrame);
		theFrame.setSize(1030,870);
//		theFrame.pack();
//		
		theFrame.setVisible(true);
		theFrame.setResizable(false);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
