package katas.regentropfen;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopologieTest {

    @Test
    void simpleCatch() throws Exception {
        Topologie topologie = new Topologie(List.of(1, 4, 2, 5, 2));
        boolean at0 = topologie.dropOfWaterKept(0);
        boolean at1 = topologie.dropOfWaterKept(1);
        boolean at2 = topologie.dropOfWaterKept(2);
        boolean at3 = topologie.dropOfWaterKept(3);
        boolean at4 = topologie.dropOfWaterKept(4);

        assertEquals(false, at0);
        assertEquals(false, at1);
        assertEquals(true, at2);
        assertEquals(false, at3);
        assertEquals(false, at4);

    }

    @Test
    void catch2() throws Exception {
        Topologie topologie = new Topologie(List.of(1, 4, 2, 5, 2));
        boolean at0 = topologie.dropOfWaterKept(0);
        boolean at1 = topologie.dropOfWaterKept(1);
        boolean at2_1 = topologie.dropOfWaterKept(2);
        boolean at2_2 = topologie.dropOfWaterKept(2);
        boolean at2_3 = topologie.dropOfWaterKept(2);
        boolean at3 = topologie.dropOfWaterKept(3);
        boolean at4 = topologie.dropOfWaterKept(4);

        assertEquals(false, at0);
        assertEquals(false, at1);
        assertEquals(true, at2_1);
        assertEquals(true, at2_2);
        assertEquals(false, at2_3);
        assertEquals(false, at3);
        assertEquals(false, at4);
    }

    @Test
    void catchDrops() throws Exception {
        Topologie topologie = new Topologie(List.of(1, 4, 2, 5, 2));
        Integer count = topologie.dropsOfWaterKept(2);
        assertEquals(2, count);
    }

    @Test
    void catchAll() throws Exception {
        Topologie topologie = new Topologie(List.of(1, 4, 2, 5, 2, 3));
        Integer count = topologie.waterKept();
        assertEquals(3, count);
    }


}
