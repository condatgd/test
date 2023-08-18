package katas.maze;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MazeSolver1 {

    public static void main(String[] args) {
        MazeSolver1 mazeSolver = new MazeSolver1();

        // https://www.dcode.fr/maze-generator
        String mazeStr =
                        "█1████████████████████████████\n" +
                        "█ █                    █     █\n" +
                        "█ █  ███████  █  █  █  █  ████\n" +
                        "█    █     █  █  █  █     █  █\n" +
                        "█ █  █  ████  ███████  ████  █\n" +
                        "█ █  █     █     █           X\n" +
                        "██████████████████████████████";

        String mazeBigStr =
                        " 1███████████████████████████████████████████████████████████████████████████████\n" +
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
                        "█   █         █ █   █ █   █         █   █ █   █     █     █ █   █     █ █   █   X\n" +
                        "█████████████████████████████████████████████████████████████████████████████████";


        mazeSolver.solve(mazeBigStr, 1, 0);
    }

    private void solve(String mazeStr, int startx, int starty) {
        Maze maze = new Maze(mazeStr);
        Maze solved = maze.solve(startx, starty);
        if(solved!=null) {
            solved.display();
        }
    }

    @Data
    @AllArgsConstructor
    private static class Position {
        int x, y;
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
                        case '1' -> mazeMatrix[i][j] = 1;
                        case 'X' -> mazeMatrix[i][j] = 8;
                        case '█' -> mazeMatrix[i][j] = 9;
                    }
                }
            }
        }

        public Maze(int[][] mazeMatrixIn) {
            int dimy = mazeMatrixIn.length;
            int dimx = mazeMatrixIn[0].length;
            mazeMatrix = new int[dimy][dimx];
            for (int i = 0; i < dimy; i++) {
                for (int j = 0; j < dimx; j++) {
                    mazeMatrix[i][j] = mazeMatrixIn[i][j];
                }
            }
        }

        public Maze solve(int posX, int posY) {
            display();
            Maze solutionDown  = solutionStep(posX, posY + 1); if(solutionDown!=null) return solutionDown;
            Maze solutionRight = solutionStep(posX+1, posY);   if(solutionRight!=null) return solutionRight;
            Maze solutionLeft  = solutionStep(posX-1, posY);   if(solutionLeft!=null) return solutionLeft;
            return solutionStep(posX, posY - 1);
        }

        public Maze solutionStep(int x1, int y1) {
            if(!inbound(x1,y1)) {
                return null;
            }
            Maze mazeNext = new Maze(mazeMatrix);
            int v1 = getValueAtXY(x1,y1);
            if(v1==8) {
                return this;
            }
            if(v1==0) {
                mazeNext.setValueAtXY(x1, y1, 1);
                return mazeNext.solve(x1, y1);
            }
            return null;
        }

        public int getValueAtXY(int x, int y) {
            return mazeMatrix[y][x];
        }
        public void setValueAtXY(int x, int y, int v) {
            if(inbound(x,y)) mazeMatrix[y][x] = v;
        }
        public boolean inbound(int x, int y) {
            return 0 <= x && x < mazeMatrix[0].length && 0 <= y && y < mazeMatrix.length;
        }

        void display() {
            matrixPositions().forEach(position -> {
                if (position.getX() == 0)
                    System.out.println();
                printMatrixPosition(position);
            });
        }

        private Stream<Position> matrixPositions() {
            return IntStream.iterate(0, i -> i < mazeMatrix.length, i -> i + 1).boxed()
                    .flatMap(i -> IntStream.iterate(0, j -> j < mazeMatrix[0].length, j -> j + 1).boxed()
                            .map(j -> new Position(j, i))
                    );
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

    }


}
