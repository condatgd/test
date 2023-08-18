package katas.maze;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MazeSolver {

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver();

        // https://www.dcode.fr/maze-generator
        String maze =
                "  ███████████████████████████████████████████████████████████████████████████████\n" +
                "  █         █   █           █         █     █ █   █   █   █     █ █   █         █\n" +
                "█ ███ █ █ ███ ███████ █████ █████ █ █ █ ███ █ █ █ ███ █ █████ ███ █ █ █ ███ ███ █\n" +
                "█ █ █ █ █   █   █     █   █ █ █ █ █ █   █     █ █ █       █         █ █   █   █ █\n" +
                "█ █ █ █ ███ █ ███ █ █ ███ ███ █ █ ███████ █ █ ███ █ █████████ ███████ █ ███ █ █ █\n" +
                "█     █ █   █     █ █   █   █         █   █ █ █ █   █ █ █       █       █   █ █ █\n" +
                "███ █ ███████ ███ ███ █████ ███ █████ █████ ███ █ ███ █ █ █ █ ███████ ███ █ ███ █\n" +
                "█   █   █   █   █ █     █   █       █     █   █ █     █   █ █   █     █   █   █ █\n" +
                "███ █ ███ █████ ███ █ █ █ █ █ █████ ███ █ █ ███ ███ █ █ ███████ ███ ███ █████████\n" +
                "█ █ █ █   █     █ █ █ █ █ █     █ █ █   █ █ █   █   █   █         █ █     █ █ █ █\n" +
                "█ █ █ █ █ █ ███ █ ███████ █████ █ █ ███████ ███ ███ ███████ █████ ███████ █ █ █ █\n" +
                "█   █   █ █ █           █ █ █     █   █       █ █ █     █   █ █ █ █   █         █\n" +
                "███ ███ ███ ███ █████ ███ █ ███████ █ █ ███████ █ ███████ ███ █ ███ █ █ ███ █████\n" +
                "█     █       █     █     █ █ █ █   █ █ █     █ █     █       █ █ █ █ █   █ █   █\n" +
                "█ ███████ █ █████ ███ █ ███ █ █ █████████ ███ █ ███ █ █ █████ █ █ ███ ███ █████ █\n" +
                "█   █   █ █     █   █ █ █       █ █ █     █ █       █ █ █             █   █   █ █\n" +
                "███ █ ███████ █████ █████ ███████ █ ███ ███ ███ █████ █ █████ █████ ███ █████ █ █\n" +
                "█ █ █           █   █   █     █         █       █         █       █   █ █   █   █\n" +
                "█ █ █████ █ █ █ ███ ███ ███ █████ ███ ███ █████ ███ ███████ ███████ █ █████ █ █ █\n" +
                "█     █ █ █ █ █   █   █ █ █       █ █ █     █ █ █   █     █ █   █   █   █     █ █\n" +
                "█ █████ ███ █████████ █ █ ███ █ █ █ █ █████ █ █████ ███ █████ ███ █ ███████ █████\n" +
                "█     █     █     █       █   █ █ █       █   █   █     █ █       █ █ █ █ █     █\n" +
                "█ █ █ █████ █ █████ █████ ███ █ ███ █████████████ ███████ █ ███ ███ █ █ █ ███ █ █\n" +
                "█ █ █ █           █   █ █     █   █   █   █ █ █       █ █ █   █ █   █ █     █ █ █\n" +
                "█████ █ ███ ███████ ███ █ ███ ███████ █ ███ █ ███ ███ █ █ █ █ █ ███ █ █████ █ █ █\n" +
                "█     █   █   █   █   █     █     █     █     █   █       █ █ █ █   █   █ █   █ █\n" +
                "█████ █ █████ █ █ ███ ███ ███ █████ █ █ ███ ███████ █ █████ ███████████ █ █ ███ █\n" +
                "█     █ █       █ █   █   █     █   █ █       █   █ █             █     █   █   █\n" +
                "█ ███ ███████████████████ █████ █ ███ ███████████ █ █ █ ███████████ █ █ █ █ █ ███\n" +
                "█ █ █     █         █   █ █ █   █   █ █   █       █ █ █     █ █   █ █ █   █ █   █\n" +
                "█ █ █ █ █████████ █████ ███ █████ ███ █ █ █ ███████ █ ███ ███ █ █ █████████ ███ █\n" +
                "█   █ █     █       █   █ █ █     █ █ █ █     █     █ █   █   █ █   █         █ █\n" +
                "█ ███ ███ █ ███ █████ █ █ █ █ █████ █ █ ███ █ █████ █ ███ █ █████ █████ █████ █ █\n" +
                "█ █ █ █   █   █   █   █     █       █   █ █ █ █   █ █ █ █   █       █     █   █ █\n" +
                "███ ███ ███ █████ █ █████████████ ███████ ███ █ █ ███ █ █████ ███ █████ ███ █ ███\n" +
                "█ █     █ █   █       █   █ █         █   █ █ █ █             █ █   █   █   █ █ █\n" +
                "█ █████ █ █ █ █ █████ █ ███ █ ███ ███████ █ ███████ ███ █████ █ ███ █ ███ █ █ █ █\n" +
                "█       █ █ █   █ █     █   █   █ █     █       █     █ █         █   █   █ █ █ █\n" +
                "███ █████ █ ███ █ █ █ ███ █ █████ █ ███ █ █ ███ ███ █████ █ ███ █████ █ █ ███ █ █\n" +
                "█   █         █ █   █ █   █         █   █ █   █     █     █ █   █     █ █   █    \n" +
                "███████████████████████████████████████████████████████████████████████████████ X";

        String maze1 =
                        "   ████████████████████████████\n" +
                        "   █                    █     █\n" +
                        "█  █  ███████  █  █  █  █  ████\n" +
                        "█     █     █  █  █  █     █  █\n" +
                        "█  █  █  ████  ███████  ████  █\n" +
                        "█  █  █     █     █           X\n" +
                        "████████████████████████████   ";

        mazeSolver.findWayFromPositionToValue(0, 0, maze);

    }

    private void findWayFromPositionToValue(int x, int y, String mazeString) {
        Maze maze = new Maze(mazeString);
        Wave wave = new Wave(1);
        wave.addPosition(new Position(x, y));
        maze.drawWave(wave);
        maze.display();

        boolean hasReachedExit = false;
        while (!hasReachedExit && wave.getPositions().size() > 0) {
            wave = wave.nextWave(maze);
            System.out.println("--");
            System.out.println(wave.getPositions());
            System.out.println("--");
            maze.drawWave(wave);
            // maze.display();
            hasReachedExit = wave.hasReachedExit();
        }
        if (wave.hasReachedExit()) {
            System.out.println("exit: " + wave.getExit());
            maze.drawPathBackwards(wave);
            maze.deleteWaveColors();
            maze.display();
        } else {
            System.out.println("no exit!!!");
        }
    }


    @Data
    @AllArgsConstructor
    private static class Position {
        int x, y;
    }

    @Data
    private class Wave {
        List<Position> positions = new ArrayList<>();
        private int color;

        private Position exit;

        public Wave(int color) {
            this.color = color;
            this.exit = null;
        }

        public void addPosition(Position position) {
            positions.add(position);
        }

        public Wave nextWave(Maze maze) {
            Wave nextWave = new Wave(nextColor());
            positions.forEach(position -> {
                int x = position.getX();
                int y = position.getY();
                searchDeltaPositions().forEach(deltaPos -> {
                    int wx = x + deltaPos.getX();
                    int wy = y + deltaPos.getY();
                    if (maze.inbound(wx, wy)) {
                        int valueAtXY = maze.getValueAtXY(wx, wy);
                        switch (valueAtXY) {
                            case 0 -> nextWave.addPosition(new Position(wx, wy));
                            case 8 -> nextWave.foundExit(new Position(x, y));
                        }
                    }
                });
            });
            return nextWave;
        }

        public Stream<Position> searchDeltaPositions() {
            return IntStream.iterate(-1, dy -> dy <= 1, dy -> dy + 1).boxed()
                    .flatMap(y -> IntStream.iterate(-1, dx -> dx <= 1, dx -> dx + 1).boxed()
                            .map(x -> new Position(x, y))
                            .filter(position -> position.getX() == 0 ^ position.getY() == 0)
                    );
        }

        private int nextColor() {
            int nextColor = color + 1;
            if (nextColor == 4) nextColor = 1;
            return nextColor;
        }

        public void foundExit(Position position) {
            exit = position;
        }

        public boolean hasReachedExit() {
            return exit != null;
        }


    }

    private class Maze {

        int[][] mazeMatrix;

        Maze(String mazeString) {
            String[] lines = mazeString.split("\n");
            int dimy = lines.length;
            int dimx = lines[0].length();
            mazeMatrix = new int[dimy][dimx];
            for (int i = 0; i < dimy; i++) {
                for (int j = 0; j < dimx; j++) {
                    char c = lines[i].charAt(j);
                    switch (c) {
                        case ' ' -> mazeMatrix[i][j] = 0;
                        case 'X' -> mazeMatrix[i][j] = 8;
                        case '█' -> mazeMatrix[i][j] = 9;
                    }
                }
            }
        }

        void display() {
            matrixPositions().forEach(position -> {
                if (position.getX() == 0)
                    System.out.println();
                printMatrixPosition(position);
            });
        }

        private void printMatrixPosition(Position position) {
            int value = mazeMatrix[position.getY()][position.getX()];
            char c = switch (value) {
                case 1 -> '1';
                case 2 -> '2';
                case 3 -> '3';
                case 7 -> '.';
                case 8 -> 'X';
                case 9 -> '█';
                default -> ' ';
            };
            System.out.print(c);
        }

        public void drawWave(Wave wave) {
            int color = wave.getColor();
            wave.getPositions().forEach(position -> mazeMatrix[position.getY()][position.getX()] = color);
        }

        private Stream<Position> matrixPositions() {
            return IntStream.iterate(0, i -> i < mazeMatrix.length, i -> i + 1).boxed()
                    .flatMap(i -> IntStream.iterate(0, j -> j < mazeMatrix[0].length, j -> j + 1).boxed()
                            .map(j -> new Position(j, i))
                    );
        }

        public boolean inbound(int x, int y) {
            return 0 <= x && x < mazeMatrix[0].length && 0 <= y && y < mazeMatrix.length;
        }

        public int getValueAtXY(int x, int y) {
            return mazeMatrix[y][x];
        }

        public void drawPathBackwards(Wave wave) {
            Position exitPos = wave.getExit();
            final int x = exitPos.getX();
            final int y = exitPos.getY();
            int color = mazeMatrix[y][x];
            mazeMatrix[y][x] = 7;
            final int prevColor = getPrevColor(color);
            wave.searchDeltaPositions().forEach(deltaPos -> {
                int wx = x + deltaPos.getX();
                int wy = y + deltaPos.getY();
                if (inbound(wx, wy)) {
                    if (getValueAtXY(wx, wy) == prevColor) {
                        wave.setExit(new Position(wx, wy));
                        drawPathBackwards(wave);
                    }
                }
            });

        }

        private int getPrevColor(int color) {
            int prevColor = color - 1;
            if (prevColor == 0) prevColor = 3;
            return prevColor;
        }

        public void deleteWaveColors() {
            matrixPositions().forEach(deltaPos -> {
                int y = deltaPos.getY();
                int x = deltaPos.getX();
                int color = mazeMatrix[y][x];
                if (1 <= color && color <= 3)
                    mazeMatrix[y][x] = 0;
            });
        }
    }

}
