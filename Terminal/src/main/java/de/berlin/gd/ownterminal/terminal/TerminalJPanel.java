package de.berlin.gd.ownterminal.terminal;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class TerminalJPanel extends JPanel {
    private final int COL_WIDTH;
    private final int DX;
    private final int ROW_HEIGHT;
    private final int DY;

    private final Character[][] matrix;
    private final JFrame frame;
    private final int cols;
    private final int rows;

    private int cursorX;
    private int cursorY;

    private boolean cursorState = false;

    public Stack<String> keyStack = new Stack<>();

    public TerminalJPanel(JFrame frame, int cols, int rows, int col_width, int dx, int row_height, int dy) {
        this.frame = frame;
        this.cols = cols;
        this.rows = rows;
        COL_WIDTH = col_width;
        DX = dx;
        ROW_HEIGHT = row_height;
        DY = dy;
        matrix = new Character[rows][cols];
        cls();
        Timer timer = new Timer(200, new MyTimer());
        timer.start();
    }

    public static TerminalJPanel createFrame(int cols, int rows, int col_width, int dx, int row_height, int dy) {
        JFrame frame = new JFrame("Basic");
        TerminalJPanel terminal = new TerminalJPanel(frame, cols, rows, col_width, dx, row_height, dy);
        frame.add(terminal);
        frame.setSize((cols + 2) * (col_width + dx), (rows + 2) * (row_height + dy));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new TerminalJPanel.MyKeyListener(frame, terminal));
        return terminal;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, (int) (ROW_HEIGHT * 1.5)));
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                graphic2d.setColor(Color.GRAY);
                int sx = x * (COL_WIDTH + DX);
                int sy = y * (ROW_HEIGHT + DY);
//                graphic2d.fillRect(sx, sy, COL_WIDTH, ROW_HEIGHT);
                graphic2d.setColor(Color.BLACK);
                Character chr = matrix[y][x];
                graphic2d.drawString(String.valueOf(chr), sx, sy + ROW_HEIGHT);
                if(x==cursorX && y==cursorY) {
                    if(cursorState) {
                        graphic2d.fillRect(sx, sy, COL_WIDTH, ROW_HEIGHT);
                    }
                }
            }
        }
    }

    private void scroll() {
        if(cursorY>rows-1) {
            int scrollLength = cursorY-(rows-1);
            for(int y=0; y<rows-scrollLength; y++) {
                matrix[y] = matrix[y+scrollLength];
            }
            for(int y=rows-scrollLength; y<rows; y++) {
                matrix[y] = new Character[cols];
                for (int c = 0; c < cols; c++) {
                    matrix[y][c] = ' ';
                }
            }
            cursorY -= scrollLength;
        }
    }

    public void printAtPosition(Position pos, char c) {
        gotoXY(pos.getX(), pos.getY());
        typeChar(c);
    }

    public void cls() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = ' ';
            }
        }
        cursorX = 0;
        cursorY = 0;
        frame.repaint();
    }

    public void gotoXY(int x, int y) {
        cursorX = x;
        cursorY = y;
        scroll();
    }

    public void printText(String s) {
        for (int i = 0; i < s.length(); i++) {
            typeChar(s.charAt(i));
        }
    }

    public Character typeChar(char c, boolean moveCursor) {
        Character prevChar = matrix[cursorY][cursorX];
        matrix[cursorY][cursorX] = c;
        if(moveCursor) {
            cursorX++;
            if (cols <= cursorX) {
                cursorX = 0;
                cursorY++;
                scroll();
            }
            if (rows <= cursorY) {
                cursorX = 0;
                cursorY = 0;
            }
        }
        frame.repaint();
        return prevChar;
    }
    public Character typeChar(char c) {
        return typeChar(c, true);
    }

    public void cursor(Direction direction) {
        switch (direction) {
            case UP: cursorY--;
                break;
            case DOWN: cursorY++;
                break;
            case LEFT: cursorX--;
                break;
            case RIGHT: cursorX++;
                break;
            case START_OF_LINE: cursorX=0;
                break;
        }
        scroll();
    }

    public String getCurrentLine() {
        Character[] lineCharArray = matrix[cursorY];
        return Arrays.stream(lineCharArray).map(String::valueOf).collect(Collectors.joining());
    }

    @Data
    @AllArgsConstructor
    public static class StackChar {
        Character c;
    }

    public static class MyKeyListener extends KeyAdapter {
        private final JFrame frame;
        private final TerminalJPanel snakeTerminal;

        public MyKeyListener(JFrame frame, TerminalJPanel snakeTerminal) {
            this.frame = frame;
            this.snakeTerminal = snakeTerminal;
        }

        public void keyPressed(KeyEvent evt) {
            if(evt.getKeyChar()!='\uFFFF') {
                snakeTerminal.keyStack.push("" + evt.getKeyChar());
            } else {
                int keyCode = evt.getKeyCode();
                String code = null;
                switch (keyCode) {
                    case 38: code = "up"; break;
                    case 40: code = "down"; break;
                    case 37: code = "left"; break;
                    case 39: code = "right"; break;
                }
                if(code!=null)
                    snakeTerminal.keyStack.push(code);
            }
            frame.repaint();
            // jc.write("keyPressed: " + evt.getKeyChar());
        }
    }

    public enum Direction {UP, DOWN, LEFT, RIGHT, START_OF_LINE}
    @Data
    @AllArgsConstructor
    public static class Position {
        private int x, y;

        public Position(Position p) {
            this.x = p.getX();
            this.y = p.getY();
        }

        public void move(Direction direction) {
            switch (direction) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
                case START_OF_LINE:
                    x=0;
                    break;
            }
        }

        public void boundaryCheck(int cols, int rows) {
            if (x >= cols) x = 0;
            if (x < 0) x = cols - 1;
            if (y >= rows) y = 0;
            if (y < 0) y = rows - 1;
        }
    }

    class MyTimer implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            cursorState = !cursorState;
            repaint();
        }
    }
}
