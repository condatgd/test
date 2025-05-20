package katas.terminal.ashell;

import com.jediterm.terminal.ui.TerminalPanel;
import katas.terminal.AbstractTerminal;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PipedWriter;

public class AShellTerminal extends AbstractTerminal {

    public static void main(String[] args) {
        AShellTerminal terminal = new AShellTerminal();
        SwingUtilities.invokeLater(terminal::createAndShowGUI);
    }

    public void mainEntryPoint(PipedWriter writer, TerminalPanel terminalPanel)  {
        AShellThread t = new AShellThread(writer, terminalPanel);
        t.start();
    }

    public class AShellThread extends Thread {

        private final PipedWriter writer;

        private String command = "";

        private String commandCollector = "";

        public AShellThread(PipedWriter writer, TerminalPanel terminalPanel) {
            this.writer = writer;
            @NotNull KeyListener keyListener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    try {
                        // System.out.println("keyTyped: " + e);
                        char keyChar = e.getKeyChar();
                        switch (keyChar) {
                            case '\n': {
                                writer.write("\r\n");
                                command = commandCollector.trim();
                                commandCollector = "";
                                break;
                            }
                            case '\b': {
                                if(!commandCollector.isEmpty()) {
                                    writer.write("\b \b");
                                    commandCollector = commandCollector.substring(0, commandCollector.length() - 1);
                                }
                                break;
                            }
                            default: {
                                writer.write(keyChar);
                                commandCollector += keyChar;
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            };
            terminalPanel.addCustomKeyListener(keyListener);
        }

        @SneakyThrows
        public void run() {
            do {
                command = "";
                writer.write("> ");
                while (command.equals("")) {
                    Thread.sleep(100);
                }
                writer.write("got command: " + command + "\r\n");
                if(command.equals("list")) {
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                    writer.write("wiejf owi efowie jfowie jfowij e foi jweo fijwoe jfowe ijf\r\n");
                }

            } while (!command.equals("quit"));
        }

    }
}
