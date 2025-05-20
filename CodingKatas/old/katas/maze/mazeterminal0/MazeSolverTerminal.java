package katas.maze.mazeterminal0;

import com.jediterm.terminal.ui.TerminalPanel;
import katas.terminal.AbstractTerminal;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PipedWriter;

public class MazeSolverTerminal extends AbstractTerminal {

    public static void main(String[] args) {
        MazeSolverTerminal solver = new MazeSolverTerminal();
        SwingUtilities.invokeLater(solver::createAndShowGUI);
    }

    public void mainEntryPoint(PipedWriter writer, TerminalPanel terminalPanel)  {
        MyThread t = new MyThread(writer, terminalPanel);
        t.start();
    }

    public class MyThread extends Thread {

        private final PipedWriter writer;

        private boolean go = false;

        public MyThread(PipedWriter writer, TerminalPanel terminalPanel) {
            this.writer = writer;
            @NotNull KeyListener keyListener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println("keyTyped: " + e);
                    if(e.getKeyChar()=='x')
                        System.exit(0);
                    if(e.getKeyChar()=='g')
                        go = !go;
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            };
            terminalPanel.addCustomKeyListener(keyListener);
        }


        @SneakyThrows
        public void run(){
            while (!go) {
                Thread.sleep(100);
            }
            solve(MazeExamples.mazeBigStr, 1, 0, writer);
        }

        private void solve(String mazeStr, int startx, int starty, PipedWriter writer) {
            Maze maze = new Maze(mazeStr, writer);
            maze.solve(startx, starty);
        }
    }




}
