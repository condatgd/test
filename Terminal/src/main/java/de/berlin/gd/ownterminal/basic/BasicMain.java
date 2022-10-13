package de.berlin.gd.ownterminal.basic;

import de.berlin.gd.ownterminal.terminal.TerminalJPanel;

public class BasicMain {
    private static final int COLS = 40;
    private static final int ROWS = 40;
    private static final int COL_WIDTH = 16;
    private static final int DX = 1;
    private static final int ROW_HEIGHT = 16;
    private static final int DY = 4;

    public static void main(String[] args) throws InterruptedException {
        TerminalJPanel terminal = TerminalJPanel.createFrame(COLS, ROWS, COL_WIDTH, DX, ROW_HEIGHT, DY);
        terminal.gotoXY(0,0);
        terminal.printText("Basic - zero bytes free");
        terminal.gotoXY(0,1);
        BasicInterpreter basic = new BasicInterpreter(terminal);
        basic.inputLoop();
    }


}
