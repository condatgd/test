package de.berlin.gd.ownterminal.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

public class SnakeTerminal extends JPanel {
    private static final int COL_WIDTH = 16;
    private static final int DX = 1;
    private static final int ROW_HEIGHT = 16;
    private static final int DY = 8;

    private final Character[][] matrix;
    private final JFrame frame;
    private final int cols;
    private final int rows;

    private int cursorX;
    private int cursorY;

    public Stack<Character> keyStack = new Stack<>();

    public SnakeTerminal(JFrame frame, int cols, int rows) {
        this.frame = frame;
        this.cols = cols;
        this.rows = rows;
        matrix = new Character[rows][cols];
        cls();
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
                graphic2d.fillRect(sx, sy, COL_WIDTH, ROW_HEIGHT);
                graphic2d.setColor(Color.BLACK);
                Character chr = matrix[y][x];
                graphic2d.drawString(String.valueOf(chr), sx, sy + ROW_HEIGHT);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SnakeTerminal terminal = createFrame(40, 40);
        snakeGame(terminal);
    }

    private static void snakeGame(SnakeTerminal terminal) throws InterruptedException {
        Random random = new Random();
        char snakeHeadChar = 'S';
        char snakeBodyChar = 'X';
        char blankChar = ' ';
        char foodChar = 'O';
        for(int i=0; i<100; i++) {
            terminal.gotoXY(random.nextInt(terminal.cols), random.nextInt(terminal.rows));
            terminal.typeChar(foodChar);
        }

        Snake snake = new Snake(terminal.cols, terminal.rows, 0, 10);
        printAtPosition(snake.head(), snakeBodyChar, terminal);

        boolean gameOver = false;
        while (!gameOver) {
            onInputChangeSnakeDirection(terminal, snake);
            Snake.Position removedPosition = snake.move();

            Snake.Position head = snake.head();

            if(terminal.matrix[head.getY()][head.getX()]==snakeBodyChar) {
                gameOver = true;
            }

            if(terminal.matrix[head.getY()][head.getX()]==foodChar) {
                snake.grow(removedPosition);
                printAtPosition(head, snakeBodyChar, terminal);
            } else {
                printAtPosition(removedPosition, blankChar, terminal);
                Optional<Snake.Position> neck = snake.neck();
                if(neck.isPresent()) {
                    Snake.Position neckPosition = neck.get();
                    printAtPosition(neckPosition, snakeBodyChar, terminal);
                }
                printAtPosition(head, snakeHeadChar, terminal);
            }
            Thread.sleep(50);
        }
    }

    private static void onInputChangeSnakeDirection(SnakeTerminal terminal, Snake snake) {
        if (!terminal.keyStack.isEmpty()) {
            Character chr = terminal.keyStack.pop();
            switch (chr) {
                case 'w': snake.changeDirection(Snake.Direction.UP); break;
                case 's': snake.changeDirection(Snake.Direction.DOWN); break;
                case 'a': snake.changeDirection(Snake.Direction.LEFT); break;
                case 'd': snake.changeDirection(Snake.Direction.RIGHT); break;
            }
        }
    }

    private static void printAtPosition(Snake.Position pos, char c, SnakeTerminal terminal) {
        terminal.gotoXY(pos.getX(), pos.getY());
        terminal.typeChar(c);
    }

    private void cls() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = ' ';
            }
        }
        cursorX = 0;
        cursorY = 0;
        frame.repaint();
    }

    private void gotoXY(int x, int y) {
        cursorX = x;
        cursorY = y;
    }

    private void typeText(String s) {
        for (int i = 0; i < s.length(); i++) {
            typeChar(s.charAt(i));
        }
    }

    private Character typeChar(char c) {
        Character prevChar = matrix[cursorY][cursorX];
        matrix[cursorY][cursorX] = c;
        cursorX++;
        if (cols <= cursorX) {
            cursorX = 0;
            cursorY++;
        }
        if (rows <= cursorY) {
            cursorX = 0;
            cursorY = 0;
        }
        frame.repaint();
        return prevChar;
    }

    private static SnakeTerminal createFrame(int cols, int rows) {
        JFrame frame = new JFrame("Demo");
        SnakeTerminal snakeTerminal = new SnakeTerminal(frame, cols, rows);
        frame.add(snakeTerminal);
        frame.setSize((cols + 2) * (COL_WIDTH + DX), (rows + 2) * (ROW_HEIGHT + DY));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new MyKeyListener(frame, snakeTerminal));
        return snakeTerminal;
    }

    static class MyKeyListener extends KeyAdapter {
        private final JFrame frame;
        private final SnakeTerminal snakeTerminal;

        public MyKeyListener(JFrame frame, SnakeTerminal snakeTerminal) {
            this.frame = frame;
            this.snakeTerminal = snakeTerminal;
        }

        public void keyPressed(KeyEvent evt) {
            snakeTerminal.keyStack.push(evt.getKeyChar());
            frame.repaint();
            // jc.write("keyPressed: " + evt.getKeyChar());
        }
    }

}
