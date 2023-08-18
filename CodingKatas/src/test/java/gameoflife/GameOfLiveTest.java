package gameoflife;

import katas.gameoflife.GameOfLife;
import katas.tennis.Tennis;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOfLiveTest {

    @Test
    void whenAliveCellHasNeighbours0_dead() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.ALIVE);
        cell.setNeighbours(new ArrayList<>());
        assertEquals(GameOfLife.CellState.DEAD, cell.computeNextState());
    }

    @Test
    void whenAliveCellHasNeighbours1_dead() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.ALIVE);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.DEAD, cell.computeNextState());
    }

    @Test
    void whenAliveCellHasNeighbours2_alive() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.ALIVE);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.ALIVE, cell.computeNextState());
    }

    @Test
    void whenAliveCellHasNeighbours3_alive() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.ALIVE);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.ALIVE, cell.computeNextState());
    }

    @Test
    void whenAliveCellHasNeighbours4_dead() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.ALIVE);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.DEAD, cell.computeNextState());
    }

    @Test
    void whenDeadCellHasNeighbours2_dead() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.DEAD);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.DEAD, cell.computeNextState());
    }

    @Test
    void whenDeadCellHasNeighbours3_alive() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.DEAD);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.ALIVE, cell.computeNextState());
    }

    @Test
    void whenDeadCellHasNeighbours5_3_alive() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.DEAD);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.DEAD));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.DEAD));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.ALIVE, cell.computeNextState());
    }

    @Test
    void whenDeadCellHasNeighbours4_dead() {
        GameOfLife.Cell cell = new GameOfLife.Cell(GameOfLife.CellState.DEAD);
        List<GameOfLife.Cell> neighbours = new ArrayList<>();
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        neighbours.add(new GameOfLife.Cell(GameOfLife.CellState.ALIVE));
        cell.setNeighbours(neighbours);
        assertEquals(GameOfLife.CellState.DEAD, cell.computeNextState());
    }

    @Test
    void game() {
        GameOfLife gameOfLife = new GameOfLife(20);
        GameOfLife.Cell cell = gameOfLife.matrix[1][1];
        System.out.println(cell);
        assertEquals(8, cell.getNeighbours().size());
        cell.getNeighbours().forEach(System.out::println);
        gameOfLife.display();

        for(int i=1; i<10; i++) {
            gameOfLife.nextGen();
            System.out.println();
            gameOfLife.display();
        }
    }


}
