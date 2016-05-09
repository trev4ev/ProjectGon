package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ProjectGon extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 160;
	public static final int HEIGHT = 120;
	public static final int SCALE = 3;
	
	private JFrame frame;

	public ProjectGon() {
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame("Project Gon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
