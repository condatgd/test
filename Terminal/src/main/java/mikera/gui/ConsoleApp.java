package mikera.gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import mikera.image.Colours;
import mikera.util.Rand;

import javax.swing.*;

public class ConsoleApp {

    public static void main(String[] args) {
        JConsole jc=new JConsole(80,40);
        jc.setCursorVisible(true);
        jc.setCursorBlink(true);
        for(int i=0; i<40; i++)
            jc.write("Hello World\n");
        jc.setCursorPos(0,0);

        JFrame jConsole_test_application = Frames.display(jc, "JConsole test application");

        while(true) {
            jConsole_test_application.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MyKeyListener extends KeyAdapter {
        private JConsole jc;

        public MyKeyListener(JConsole jc) {
            this.jc = jc;
        }

        public void keyPressed(KeyEvent evt) {
           jc.write("keyPressed: " + evt.getKeyCode());
        }
    }
}
