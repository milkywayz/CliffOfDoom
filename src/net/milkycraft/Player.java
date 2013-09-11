package net.milkycraft;

public class Player {

	private int x, y;
	private boolean alive;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.alive = true;
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void die(){
		alive = false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void move(int x, int y) {
		if(!alive) return;
		this.x = x;
		this.y = y;
	}
}
