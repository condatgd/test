package katas.gameoflife;

import javax.swing.*;
import java.awt.*;

public class GOLCanvas extends JFrame {

    Canvas c;

    // constructor
    GOLCanvas() {
        super("canvas");

        int dim = 160;
        int w = 5;

        GameOfLife gameOfLife = new GameOfLife(dim);

        // create a empty canvas
        c = new Canvas() {

            // paint the canvas
            public void paint(Graphics g)
            {
                // set color to red
                g.setColor(Color.white);

                for(int y=0; y<dim; y++) {
                    for (int x = 0; x < dim; x++) {
                        if(gameOfLife.matrix[y][x].getState().equals(GameOfLife.CellState.ALIVE))
                            g.fillRect(x*w,y*w,w,w);
                    }
                }
               //  gameOfLife.nextGen();
            }
        };

        // set background
        c.setBackground(Color.black);

        add(c);
        setSize(800, 800);
        show();
    }

    // Main Method
    public static void main(String args[]) throws InterruptedException {
        GOLCanvas golCanvas = new GOLCanvas();
        while(true) {
            golCanvas.c.repaint();
//            Thread.sleep(10);
        }
    }
}