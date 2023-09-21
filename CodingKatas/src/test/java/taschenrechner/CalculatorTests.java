package taschenrechner;

import katas.taschenrechner.Code;
import katas.taschenrechner.Calculator;
import katas.taschenrechner.impl.TRechnerV0;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTests {
    Calculator tRechner;
    @BeforeAll
    void init() {
        tRechner = new TRechnerV0();
    }

    @Test
    void powerOn() {
        tRechner.switchPower(true);
        assertTrue(tRechner.hasPower());
    }

    @Test
    void powerOff() {
        tRechner.switchPower(false);
        assertFalse(tRechner.hasPower());
    }

    @Test
    void powerAllParts() {
        tRechner.switchPower(true);
        assertTrue(tRechner.getInput().hasPower());
        assertTrue(tRechner.getOutput().hasPower());
    }

    @Test
    void simpleAddition() {
        tRechner.switchPower(true);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.EQ);
        assertEquals("2", tRechner.getOutput().asString());
    }

    @Test
    void simpleAdditionNoPower() {
        tRechner.switchPower(false);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.EQ);
        assertEquals("0", tRechner.getOutput().asString());
    }

    @Test
    void moreAddition() {
        tRechner.switchPower(true);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.EQ);

        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.N0);
        tRechner.getInput().enterCode(Code.EQ);
        assertEquals("12", tRechner.getOutput().asString());
    }

    @Test
    void moreAdditionWithoutEQ() {
        tRechner.switchPower(true);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);

        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.N0);
        tRechner.getInput().enterCode(Code.EQ);
        assertEquals("12", tRechner.getOutput().asString());
    }

    @Test
    void precedence() {
        tRechner.switchPower(true);
        tRechner.getInput().enterCode(Code.N1);
        tRechner.getInput().enterCode(Code.PLUS);
        tRechner.getInput().enterCode(Code.N5);
        tRechner.getInput().enterCode(Code.MULT);
        tRechner.getInput().enterCode(Code.N5);
        tRechner.getInput().enterCode(Code.EQ);
        assertEquals("27", tRechner.getOutput().asString());
    }


}
