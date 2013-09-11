package net.milkycraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {
	
	public double timeElapsed = 0;
	public int moves = 0;
	public double totalTime = 0;
	public int totalMoves = 0;
	public int runs = 1;
	protected Player p;
	
	protected void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;		
		g2d.drawString("Current Time: " + MathUtil.r(timeElapsed, 3) + "s", 10, 15);
		g2d.drawString("Current Moves: " + moves, 10, 30);
		g2d.drawString("Average Time: " + MathUtil.r(totalTime/runs, 3), 10, 45);
		g2d.drawString("Average Moves: " + totalMoves/runs, 10, 60);
		g2d.drawString("Total Time: " + MathUtil.r(totalTime, 3) + "s", 10, 75);
		g2d.drawString("Runs: " + runs, 10, 90);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(190, 200, 450, 270);
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 440, 190, 20);
		if(p.isAlive()) 
			g2d.setColor(Color.GREEN);
		else 
			g2d.setColor(Color.RED);
		g2d.fillRect(p.getX()-5, p.getY()-25, 10, 25);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
}

public class CliffOfDoom extends JFrame {
	
	public Timer t;
	public Surface s;
	public Random rand = new Random();
	public CliffOfDoom() {
		s = new Surface();
		s.p = new Player(205, 200);
		initUI();
		t = new Timer(true);
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if(!s.p.isAlive()) return;
				s.moves++;
				s.totalMoves++;
				s.totalTime = s.totalTime + 0.1D;
				s.timeElapsed = s.timeElapsed + 0.1D;
				if(rand.nextInt(100) > 50) {
					s.p.move(s.p.getX()+5, s.p.getY());
				} else {
					s.p.move(s.p.getX()-5, s.p.getY());
				}
				if(s.p.getX() < 190) {
					s.p.move(s.p.getX()-5, s.p.getY()+240);
					s.p.die();
					s.p = new Player(205, 200);					
					s.timeElapsed = 0;
					s.moves = 0;
					s.runs++;
				}
				s.repaint();
			}
			
		}, 100, 100);
	}

	private void initUI() {
		setTitle("Cliff of doom");
		add(s);
		setSize(600, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CliffOfDoom sk = new CliffOfDoom();
				sk.setVisible(true);

			}
		});
	}
}