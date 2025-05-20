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
    }

    public static enum CellState {ALIVE, DEAD}

    @Data
    @ToString
    public static class Cell {
        private boolean isAlive;
        private int nCount;

        public boolean isDead() {
            return !isAlive;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }

        public void setNeighboursCount(int c) {
            nCount = c;
        }

        public int getNeighboursCount() {
            return nCount;
        }

        public boolean survives() {
            return isAlive && (nCount==2 || nCount==3);
        }

        public boolean becomesAlive() {
            return nCount==3;
        }

        public boolean dies() {
            return isAlive && !survives();
        }

        public boolean nextState() {
            return survives() || becomesAlive();
        }

    }




}
