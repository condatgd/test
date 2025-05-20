package katas.fractal;

import javax.swing.*;
import java.awt.*;

public class FractalTree extends JFrame {

    public FractalTree() {
        setBounds(300, 300, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void drawTree(Graphics g, int x1, int y1, double angle, int depth) {
        if (depth == 0) return;
        int x2 = x1;
        int y2 = y1;
        for(int i=0; i<4; i++) {
            x1 = x2; y1 = y2; angle += 90;
            x2 = getX2(x1, angle, depth);
            y2 = getY2(y1, angle, depth);
            g.drawLine(x1, y1, x2, y2);
        }

        x1 = x2; y1 = y2; angle += 45;
        x2 = getX2(x1, angle, depth);
        y2 = getY2(y1, angle, depth);
        g.drawLine(x1, y1, x2, y2);

    }

    private static int getY2(int y1, double angle, int depth) {
        return y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0);
    }

    private static int getX2(int x1, double angle, int depth) {
        return x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        drawTree(g, 400, 500, 3*90, 9);
    }

    public static void main(String[] args) {
        new FractalTree().setVisible(true);
    }
}