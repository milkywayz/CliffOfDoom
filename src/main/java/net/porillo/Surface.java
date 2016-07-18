package net.porillo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.pow;

@Data
@EqualsAndHashCode(callSuper = false)
class Surface extends JPanel {

    private double started, totalTime;
    private int runs = 1;
    private Player player;

    Surface(Player player) {
        this.player = player;
        this.started = System.currentTimeMillis();
    }

    void setPlayer(Player player) {
        this.player = player;
        this.runs++;
        this.started = System.currentTimeMillis();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        double timeElapsed = (System.currentTimeMillis() - started) / 1000;

        g2d.drawString("Current Time: " + round(timeElapsed, 3) + "s", 10, 15);
        g2d.drawString("Current Moves: " + player.getMoves(), 10, 30);
        g2d.drawString("Average Time: " + round(totalTime / runs, 3), 10, 45);
        g2d.drawString("Average Moves: " + player.getTotalMoves() / runs, 10, 60);
        g2d.drawString("Total Time: " + round(totalTime, 3) + "s", 10, 75);
        g2d.drawString("Total Moves: " + player.getTotalMoves(), 10, 90);
        g2d.drawString("Runs: " + runs, 10, 105);
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(190, 200, 450, 270);
        g2d.setColor(Color.RED);
        g2d.fillRect(0, 440, 190, 20);

        if (player.isAlive()) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.fillRect(player.getX() - 5, player.getY() - 25, 10, 25);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private static double round(double v, int p) {
        long f = (long) pow(10, p);
        return (double) Math.round(v * f) / f;
    }
}
