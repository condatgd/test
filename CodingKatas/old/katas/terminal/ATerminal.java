package katas.terminal;


import com.jediterm.terminal.ui.TerminalPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import java.io.IOException;
import java.io.PipedWriter;

public class ATerminal extends AbstractTerminal {

    public static void main(String[] args) {
        ATerminal t = new ATerminal();
        SwingUtilities.invokeLater(t::createAndShowGUI);
    }

    public void mainEntryPoint(PipedWriter writer, TerminalPanel terminalPanel) {
        MyThread t = new MyThread(writer);
        t.start();
    }

    public class MyThread extends Thread {

        private final PipedWriter writer;

        public MyThread(PipedWriter writer) {
            this.writer = writer;
        }

        @SneakyThrows
        public void run(){
            for(int i=0; i<1000; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                writer.write(ESC + "%G");
                writer.write(ESC + "[31m");
                writer.write("Hello\r\n");
                writer.write(ESC + "[32;43m");
                writer.write("World\r\n");
            }
        }
    }
}
