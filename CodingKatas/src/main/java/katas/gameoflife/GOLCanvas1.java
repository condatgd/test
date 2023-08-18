package katas.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GOLCanvas1 extends JPanel {

    int dim = 80;
    int w = 10;

    GameOfLife gameOfLife;

    public GOLCanvas1() {
        gameOfLife = new GameOfLife(dim);
    }

    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        g.setColor(Color.black);

        for(int y=0; y<dim; y++) {
            for (int x = 0; x < dim; x++) {
                if(gameOfLife.matrix[y][x].getState().equals(GameOfLife.CellState.ALIVE))
                    g.fillRect(x*w,y*w,w,w);

/*
                else
                    if(gameOfLife.matrix[y][x].getState().equals(GameOfLife.CellState.FOOD))
                        g.drawArc(x*w,y*w, w,w,0, 180);
*/
            }
        }

        gameOfLife.nextGen();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GOLCanvas1());
        frame.setSize(800, 800);
        frame.setVisible(true);
        while(true) {
            frame.repaint();
//            Thread.sleep(10);
        }
    }
}