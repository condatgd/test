package katas.terminal;


import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalPanel;
import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public abstract class AbstractTerminal {
    public static final char ESC = '\u001b';

    public abstract void mainEntryPoint(@NotNull PipedWriter writer, TerminalPanel terminalPanel);

    private @NotNull JediTermWidget createTerminalWidget() {
        JediTermWidget widget = new JediTermWidget(160, 60, new DefaultSettingsProvider());
        PipedWriter terminalWriter = new PipedWriter();
        widget.setTtyConnector(new AbstractTerminal.ExampleTtyConnector(terminalWriter));
        widget.start();
        mainEntryPoint(terminalWriter, widget.getTerminalPanel());
        return widget;
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Basic Terminal Example");
        frame.setDefaultCloseOperation(3);
        JediTermWidget terminalWidget = createTerminalWidget();
        frame.setContentPane(terminalWidget);
        frame.pack();
        frame.setVisible(true);
    }


    @Getter
    static class ExampleTtyConnector implements TtyConnector {
        private final PipedReader myReader;
        private final PipedWriter writer;

        public ExampleTtyConnector(@NotNull PipedWriter writer) {
            try {
                this.writer = writer;
                this.myReader = new PipedReader(writer);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }

        public void close() {
        }

        public String getName() {
            return null;
        }

        public int read(char[] buf, int offset, int length) throws IOException {
            return this.myReader.read(buf, offset, length);
        }

        public void write(byte[] bytes) {
        }

        public boolean isConnected() {
            return true;
        }

        public void write(String string) {
        }

        public int waitFor() {
            return 0;
        }

        public boolean ready() throws IOException {
            return this.myReader.ready();
        }
    }
}
