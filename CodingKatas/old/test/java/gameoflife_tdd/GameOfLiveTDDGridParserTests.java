package gameoflife_tdd;

import katas.gameoflife_tdd.Grid;
import katas.gameoflife_tdd.GridParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class GameOfLiveTDDGridParserTests {
    /*
    @Test
    void aSingleCellInASingleGridHasNoNeighbours() throws Exception {
        GridParser parser = new GridParser();
        Grid grid = parser.createGrid(
                new String[]{
                        "*",
                }
        );
        assertEquals(1, grid.getWidth());
        assertEquals(1, grid.getHeight());
        assertTrue(grid.cellAt(0, 0).isPresent());
        assertEquals(0, grid.cellAt(0, 0).get().getNeighboursCount());
    }

    @Test
    void noValidInput_xwidth()  {
        GridParser parser = new GridParser();
        Exception exception = assertThrows(Exception.class,
                () -> parser.createGrid(
                        new String[]{
                                "*",
                                "**",
                        }
                )
        );
        assertEquals("different lines have different length.", exception.getMessage());
    }

    @Test
    void noValidInput_yheight()  {
        GridParser parser = new GridParser();
        Exception exception = assertThrows(Exception.class,
                () -> parser.createGrid(
                        new String[]{
                        }
                )
        );
        assertEquals("height must be >0.", exception.getMessage());
    }

    @Test
    void aSingleCellHas8Neighbours() throws Exception {
        GridParser parser = new GridParser();
        Grid grid = parser.createGrid(
                new String[]{
                        "***",
                        "***",
                        "***",
                }
        );
        assertEquals(3, grid.getWidth());
        assertEquals(3, grid.getHeight());
        assertTrue(grid.cellAt(1, 1).isPresent());
        assertEquals(8, grid.cellAt(1, 1).get().getNeighboursCount());
    }

    @Test
    void aSingleCellHas0Neighbours() throws Exception {
        GridParser parser = new GridParser();
        Grid grid = parser.createGrid(
                new String[]{
                        "...",
                        ".*.",
                        "...",
                }
        );
        assertEquals(3, grid.getWidth());
        assertEquals(3, grid.getHeight());
        assertTrue(grid.cellAt(1, 1).isPresent());
        assertEquals(0, grid.cellAt(1, 1).get().getNeighboursCount());
    }

*/
}
