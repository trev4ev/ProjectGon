package main;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class ProjectGon {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project Gon");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - GamePanel.WIDTH,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - GamePanel.HEIGHT
				);
		frame.pack();
		frame.setVisible(true);
		
	}
}