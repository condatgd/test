package mikera.gui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeTerminal {

    JConsole jc;
    JFrame frame;

    int x = 0;
    int y = 0;
    int dx = 1;
    int dy = 0;


    public static void main(String[] args) {
        SnakeTerminal snake = new SnakeTerminal(80,40);
        snake.loop();
    }

    public SnakeTerminal(int col, int row) {
        jc=new JConsole(80,40);
        jc.setCursorVisible(false);
        jc.setCursorBlink(false);
        jc.setCursorPos(0,0);
        frame = Frames.display(jc, "Snake");
    }


    private void loop() {
        while(true) {
            repaintAndWait();
            x += dx;
            y += dy;
            jc.setCursorPos(x,y);
            jc.write("x");
            if(!jc.keyStack.isEmpty()) {
                Character poppedKeyChar = jc.keyStack.pop();
                if (poppedKeyChar != null) {
                    switch (poppedKeyChar) {
                        case 'w':
                            dx = 0;
                            dy = -1;
                            break;
                        case 's':
                            dx = 0;
                            dy = 1;
                            break;
                        case 'a':
                            dx = -1;
                            dy = 0;
                            break;
                        case 'd':
                            dx = 1;
                            dy = 0;
                            break;
                    }
                }
            }
        }
    }

    private void repaintAndWait() {
        frame.repaint();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyKeyListener extends KeyAdapter {
        private JConsole jc;

        public MyKeyListener(JConsole jc) {
            this.jc = jc;
        }

        public void keyPressed(KeyEvent evt) {
            jc.keyStack.push(evt.getKeyChar());
            // jc.write("keyPressed: " + evt.getKeyChar());
        }
    }
}
