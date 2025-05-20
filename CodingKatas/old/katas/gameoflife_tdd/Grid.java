package katas.gameoflife_tdd;

import lombok.Data;

import java.util.Optional;

@Data
public class Grid {

    /*
    private final int width;
    private final int height;

    private Cell[][] cells;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[height][width];
    }

    public void putCellAt(Cell cell, int x, int y) {
        cells[y][x] = cell;
    }

    public Optional<Cell> cellAt(int x, int y) {
        if(0<=x && x<width && 0<=y && y<height ) {
            return Optional.of(cells[y][x]);
        }
        return Optional.empty();
    }

    public void computeCellsNeighbourCounts() {
        for(int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                Optional<Cell> cell = cellAt(x, y);
                if(cell.isPresent()) {
                    int count =
                        testNeighbour(x, y, -1, -1) +
                        testNeighbour(x, y, -1, 0) +
                        testNeighbour(x, y, -1, 1) +
                        testNeighbour(x, y, 1, -1) +
                        testNeighbour(x, y, 1, 0) +
                        testNeighbour(x, y, 1, 1) +
                        testNeighbour(x, y, 0, -1) +
                        testNeighbour(x, y, 0, 1);
                    cell.get().setNeighboursCount(count);
                }
            }
        }
    }

    private int testNeighbour(int x, int y, int dx, int dy) {
        return cellAt(x+dx, y+dy).stream()
                .map(c -> c.isAlive() ? 1 : 0).findFirst().orElse(0);


    }

    public Grid nextGrid() {
        computeCellsNeighbourCounts();
        return null;
    }


     */
}
