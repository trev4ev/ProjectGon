package main;

import javax.swing.JFrame;

public class ProjectGon {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project Gon");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
	}
}