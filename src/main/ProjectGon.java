package main;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class ProjectGon {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Project Gon");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().x - (int)(GamePanel.WIDTH/2 * GamePanel.SCALE),
				GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().y - (int)(GamePanel.HEIGHT * GamePanel.SCALE)
				);
		frame.pack();
		frame.setVisible(true);
		
	}
}