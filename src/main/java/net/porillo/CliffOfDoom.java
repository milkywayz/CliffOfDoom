package net.porillo;

import javax.swing.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CliffOfDoom extends JFrame {

    private static final Random rand = new Random();
    private Surface surface;
    private long started;

    public CliffOfDoom() {
        surface = new Surface(new Player(205, 200));

        initializeUI();

        this.started = System.currentTimeMillis();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (!surface.getPlayer().isAlive()) {
                    return;
                }

                /* Set total time in seconds, with double precision */
                surface.setTotalTime((System.currentTimeMillis() - started) / 1000);

                if (rand.nextInt(100) > 50) {
                    surface.getPlayer().move(5, 0);
                } else {
                    surface.getPlayer().move(-5, 0);
                }

                if (surface.getPlayer().getX() < 190) {
                    surface.getPlayer().move(-5, 240);

                    surface.getPlayer().setAlive(false);
                    int totalMoves = surface.getPlayer().getTotalMoves();
                    Player newPlayer = new Player(205, 200, totalMoves);
                    surface.setPlayer(newPlayer);
                }

                surface.repaint();
            }

        }, 100, 100);
    }

    private void initializeUI() {
        setTitle("Cliff of doom");
        add(surface);
        setSize(600, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                CliffOfDoom main = new CliffOfDoom();
                main.setVisible(true);
            }
        });
    }
}