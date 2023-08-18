package katas.gameoflife;

import lombok.Data;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameOfLife {

    // Any live cell with two or three live neighbours survives.
    // Any dead cell with three live neighbours becomes a live cell.
    // All other live cells die in the next generation. Similarly, all other dead cells stay dead.

    public Cell[][] matrix;
    public int dim;
    public GameOfLife(int dim) {
        this.dim = dim;
        Random random = new Random();
        matrix = new Cell[dim][dim];
        for(int y=0; y<dim; y++)
            for(int x=0; x<dim; x++) {
                if(random.nextBoolean())
                    matrix[y][x] = new Cell(CellState.ALIVE, x, y);
                else
                    matrix[y][x] = new Cell(CellState.DEAD, x, y);
            }

        for(int y=0; y<dim; y++)
            for(int x=0; x<dim; x++) {
                Cell cell = matrix[y][x];
                for(int dy=-1; dy<=1; dy++)
                    for(int dx=-1; dx<=1; dx++) {
                        int ny = (y + dy + dim) % dim;
                        int nx = (x + dx + dim) % dim;
                        if(nx!=x || ny!=y)
                            cell.neighbours.add(matrix[ny][nx]);
                    }
            }
    }

    public void display() {
        System.out.println("----------------------------");
        for(int y=0; y<dim; y++) {
            for (int x = 0; x < dim; x++) {
                if(matrix[y][x].getState().equals(CellState.ALIVE))
                    System.out.print("X");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }

    public void nextGen() {
        for(int y=0; y<dim; y++)
            for (int x = 0; x < dim; x++)
                matrix[y][x].computeNextState();
        for(int y=0; y<dim; y++)
            for (int x = 0; x < dim; x++)
                matrix[y][x].nextGen();

    }

    public static enum CellState {ALIVE, DEAD}

    @Data
    @ToString
    public static class Cell {
        private int x,y;
        private CellState state = CellState.DEAD;
        private CellState nextState = CellState.DEAD;
        @Singular
        @ToString.Exclude
        private List<Cell> neighbours = new ArrayList<>();

        public Cell(CellState state) {
            this.state = state;
        }
        public Cell(CellState state, int x, int y) {
            this.state = state;
            this.x = x;
            this.y = y;
        }

        public CellState computeNextState() {
            nextState = state;
            long numberOfAliveNeighbours = neighbours.stream().filter(neighbour -> neighbour.getState().equals(CellState.ALIVE)).count();
/*
            long numberOfAliveNeighbours = 0;
            for(int i=0; i<neighbours.size();i++) {
                if(neighbours.get(i).getState().equals(CellState.ALIVE)) numberOfAliveNeighbours++;
            }

            if(state.equals(CellState.ALIVE) && (numberOfAliveNeighbours<2 || 3<numberOfAliveNeighbours)) {
                nextState = CellState.FOOD;
            } else
            if(state.equals(CellState.DEAD) && numberOfFoodNeighbours>2) {
                nextState = CellState.ALIVE;
            } else
            if(state.equals(CellState.FOOD) && numberOfAliveNeighbours<3) {
                nextState = CellState.DEAD;
            } else
            if(state.equals(CellState.DEAD) && numberOfAliveNeighbours==3) {
                nextState = CellState.ALIVE;
            }
            return nextState;


*/
            if(state.equals(CellState.ALIVE) && (numberOfAliveNeighbours<2 || 3<numberOfAliveNeighbours)) {
                nextState = CellState.DEAD;
            } else
            if(state.equals(CellState.DEAD) && numberOfAliveNeighbours==3) {
                nextState = CellState.ALIVE;
            }
            return nextState;
        }

        public void nextGen() {
            state = nextState;
        }

    }




}
