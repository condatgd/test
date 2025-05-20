package gameoflife_tdd;

import katas.gameoflife.GameOfLife.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;


public class GameOfLiveTDDCellTests {

    @Test
    void aDeadCellAsDefault() {
        Cell cell = new Cell();
        assertTrue(cell.isDead());
    }

    @Test
    void aLivingCell() {
        Cell cell = new Cell();
        cell.setAlive(true);
        assertTrue(cell.isAlive());
    }

    @Test
    void aLivingCellIsAliveAndNotDead() {
        Cell cell = new Cell();
        cell.setAlive(true);
        assertTrue(cell.isAlive());
        assertFalse(cell.isDead());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0, 0",
            "1, 1",
            "2, 2",
            "3, 3",
            "4, 4"
    })
    void cellHasNeighboursCount(int setCount, int expectedCount) {
        Cell cell = new Cell();
        cell.setNeighboursCount(setCount);
        assertEquals(expectedCount, cell.getNeighboursCount());
    }


    // Any alive cell with two or three live neighbours survives.
    @ParameterizedTest
    @CsvSource(value = {
            "0, false",
            "1, false",
            "2, true",
            "3, true",
            "4, false",
            "5, false",
            "6, false",
            "7, false",
            "8, false",
    })
    void cellSurvives(int numNeighbours, boolean survives) {
        Cell cell = new Cell();
        cell.setAlive(true);
        cell.setNeighboursCount(numNeighbours);
        assertEquals(survives, cell.survives());
    }

    // Any dead cell with three live neighbours becomes a live cell.
    @ParameterizedTest
    @CsvSource(value = {
            "0, false",
            "1, false",
            "2, false",
            "3, true",
            "4, false",
            "5, false",
            "6, false",
            "7, false",
            "8, false",
    })
    void deadCellBecomesAliveWithThreeLiveNeighbours(int n, boolean expected) {
        Cell cell = new Cell();
        cell.setAlive(false);
        cell.setNeighboursCount(n);
        assertEquals(expected, cell.becomesAlive());
    }

    // All other live cells die in the next generation.
    @ParameterizedTest
    @CsvSource(value = {
            "0",
            "1",
            "4",
            "5",
            "6",
            "7",
            "8",
    })
    void otherCellsDie(int numNeighbours) {
        Cell cell = new Cell();
        cell.setAlive(true);
        cell.setNeighboursCount(numNeighbours);
        assertTrue(cell.dies());
    }

    // Similarly, all other dead cells stay dead.
    @ParameterizedTest
    @CsvSource(value = {
            "0",
            "1",
            "2",
            "4",
            "5",
            "6",
            "7",
            "8",
    })
    void otherCellsStayDead(int numNeighbours) {
        Cell cell = new Cell();
        cell.setNeighboursCount(numNeighbours);
        assertTrue(cell.isDead());
    }

    // nextState tests
    @ParameterizedTest
    @CsvSource(value = {
            "0,false,false",
            "1,false,false",
            "2,false,false",
            "3,false,true",
            "4,false,false",
            "5,false,false",
            "6,false,false",
            "7,false,false",
            "8,false,false",
            "0,true,false",
            "1,true,false",
            "2,true,true",
            "3,true,true",
            "4,true,false",
            "5,true,false",
            "6,true,false",
            "7,true,false",
            "8,true,false",
    })
    void nextStateTest(int numNeighbours, boolean alive, boolean nextAlive) {
        Cell cell = new Cell();
        cell.setAlive(alive);
        cell.setNeighboursCount(numNeighbours);
        assertEquals(nextAlive, cell.nextState());
    }


}
