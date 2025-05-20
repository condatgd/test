package katas.maze.mazeterminal0;

import katas.terminal.AbstractTerminal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static katas.maze.mazeterminal0.MatrixValue.*;

public class Maze {
    private final PipedWriter writer;
    MatrixValue[][] mazeMatrix;

    Position endPosition;
    MatrixValue lastColor;
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

    public void solve(int posX, int posY) {
        display();
        List<Position> wave = List.of(new Position(posX,posY));
        solveWave(wave);
        if(endPosition!=null)
            drawWay(endPosition);
        else
            write("no way");
    }

    private void drawWay(Position position) {
        MatrixValue colorToFind = lastColor;
        Position pos = position;
        do {
            pos = findPositionOfColorAround(pos, colorToFind);
            if (pos!=null) {
                setValueAtPosition(pos, WAY);
                display();
            }
            if (colorToFind == WAVE3) colorToFind = WAVE2; else
            if (colorToFind == WAVE2) colorToFind = WAVE1; else
            if (colorToFind == WAVE1) colorToFind = WAVE3;

        } while (pos!=null);

    }

    private Position findPositionOfColorAround(Position position, MatrixValue lastColor) {
        int x = position.getX();
        int y = position.getY();
        Position up = new Position(x, y - 1);
        Position down = new Position(x, y + 1);
        Position left = new Position(x - 1, y);
        Position right = new Position(x + 1, y);
        if (getValueAtPosition(up).equals(lastColor)) return up;
        if (getValueAtPosition(down).equals(lastColor)) return down;
        if (getValueAtPosition(left).equals(lastColor)) return left;
        if (getValueAtPosition(right).equals(lastColor)) return right;
        return null;
    }

    private void solveWave(List<Position> wave) {
        List<Position> newWave;
        MatrixValue color = WAVE1;
        do {
            newWave = new ArrayList<>();
            for (Position position : wave) {
                int x = position.getX();
                int y = position.getY();
                Position up = new Position(x, y - 1);
                Position down = new Position(x, y + 1);
                Position left = new Position(x - 1, y);
                Position right = new Position(x + 1, y);
                addIfFree(newWave, up, color);
                addIfFree(newWave, down, color);
                addIfFree(newWave, left, color);
                addIfFree(newWave, right, color);
                display();
            }
            if (color == WAVE1) color = WAVE2; else
            if (color == WAVE2) color = WAVE3; else
            if (color == WAVE3) color = WAVE1;
            wave = newWave;
        } while (!wave.isEmpty());
        System.out.println("endPosition: " + endPosition);
        System.out.println("lastColor: " + lastColor);
    }

    private void addIfFree(List<Position> newWave, Position position, MatrixValue color) {
        if(inbound(position) && getValueAtPosition(position).equals(FREE)) {
            newWave.add(position);
            setValueAtPosition(position,color);
            lastColor = color;
        }
        if(inbound(position) && getValueAtPosition(position).equals(END)) {
            endPosition = position;
        }
    }

    public MatrixValue getValueAtPosition(Position position) {
        return mazeMatrix[position.y][position.x];
    }

    public void setValueAtPosition(Position position, MatrixValue v) {
        if(inbound(position.x,position.y)) mazeMatrix[position.y][position.x] = v;
    }

    public boolean inbound(int x, int y) {
        return 0 <= x && x < mazeMatrix[0].length && 0 <= y && y < mazeMatrix.length;
    }

    public boolean inbound(Position position) {
        return 0 <= position.x && position.x < mazeMatrix[0].length && 0 <= position.y && position.y < mazeMatrix.length;
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
            Thread.sleep(0);
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
