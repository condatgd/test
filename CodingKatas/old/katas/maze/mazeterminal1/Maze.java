package katas.maze.mazeterminal1;

import katas.terminal.AbstractTerminal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static katas.maze.mazeterminal1.MatrixValue.*;

public class Maze {
    private final PipedWriter writer;
    MatrixValue[][] mazeMatrix;
    Maze(String mazeString, PipedWriter writer) {
        this.writer = writer;
        String[] lines = mazeString.split("\n");
        int dimy = lines.length;
        int dimx = lines[0].length();
        mazeMatrix = new MatrixValue[dimy][dimx];
        for (int i = 0; i < dimy; i++) {
            for (int j = 0; j < dimx; j++) {
                mazeMatrix[i][j] = MatrixValue.getValueForChar(lines[i].charAt(j)).orElse(FREE);
            }
        }
    }

    public Maze solve(int posX, int posY) {
        display();

        Maze solutionDown  = solutionStep(posX, posY + 1);
        if(solutionDown!=null) {
            return solutionDown;
        }

        Maze solutionRight = solutionStep(posX+1, posY);
        if(solutionRight!=null) {
            return solutionRight;
        }

        Maze solutionLeft  = solutionStep(posX-1, posY);
        if(solutionLeft!=null) {
            return solutionLeft;
        }

        return solutionStep(posX, posY - 1);
    }

    public Maze solutionStep(int x1, int y1) {
        if(!inbound(x1,y1)) {
            return null;
        }
        MatrixValue currentValue = getValueAtXY(x1,y1);
        if(currentValue==END) {
            return this;
        }
        if(currentValue==FREE) {
            setValueAtXY(x1, y1, WAY);
            Maze solved = solve(x1, y1);
            if(solved==null)
                setValueAtXY(x1, y1, BLOCKED); // no way, backtrack ...
            return solved;
        }
        return null;
    }

    public MatrixValue getValueAtXY(int x, int y) {
        return mazeMatrix[y][x];
    }
    public void setValueAtXY(int x, int y, MatrixValue v) {
        if(inbound(x,y)) mazeMatrix[y][x] = v;
    }
    public boolean inbound(int x, int y) {
        return 0 <= x && x < mazeMatrix[0].length && 0 <= y && y < mazeMatrix.length;
    }

    void display() {
        write(AbstractTerminal.ESC + "[0;0f");
        matrixPositions().forEach(position -> {
            if (position.getX() == 0) {
                write('\n');
                write('\r');
            }
            printMatrixPosition(position);
        });
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Position> matrixPositions() {
        return IntStream.iterate(0, i -> i < mazeMatrix.length, i -> i + 1).boxed()
                .flatMap(i -> IntStream.iterate(0, j -> j < mazeMatrix[0].length, j -> j + 1).boxed()
                        .map(j -> new Position(j, i))
                );
    }

    private void printMatrixPosition(Position position) {
        MatrixValue value = mazeMatrix[position.getY()][position.getX()];
        write(value.getDisplayChar());
    }

    private void write(char c) {
        try {
            writer.write(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void write(String s) {
        try {
            writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Data
    @AllArgsConstructor
    private static class Position {
        int x, y;
    }

}
