package de.berlin.gd.ownterminal.basic;

import de.berlin.gd.ownterminal.terminal.TerminalJPanel;

import java.util.*;

public class BasicInterpreter {
    private final TerminalJPanel terminal;

    private final SortedMap<Integer, BasicCommand> program;

    private int currentLineindex = 0;
    public BasicInterpreter(TerminalJPanel terminal) {
        this.terminal = terminal;
        program = new TreeMap<>();
    }

    public void inputLoop() {
        while (true) {
            if (!terminal.keyStack.isEmpty()) {
                String code = terminal.keyStack.pop();
                switch (code) {
                    case "\b":
                        terminal.cursor(TerminalJPanel.Direction.LEFT);
                        terminal.typeChar(' ', false);
                        break;
                    case "\n":
                        String line = terminal.getCurrentLine();
                        terminal.cursor(TerminalJPanel.Direction.START_OF_LINE);
                        terminal.cursor(TerminalJPanel.Direction.DOWN);
                        executeCommand(line);
                        break;
                    case "up": terminal.cursor(TerminalJPanel.Direction.UP); break;
                    case "down": terminal.cursor(TerminalJPanel.Direction.DOWN);break;
                    case "left": terminal.cursor(TerminalJPanel.Direction.LEFT);break;
                    case "right": terminal.cursor(TerminalJPanel.Direction.RIGHT);break;
                    default:
                        terminal.typeChar(code.charAt(0));
                }
            }
        }
    }

    public void executeCommand(String line) {
        Optional<BasicCommand> command = parseLine(line.trim());
        command.ifPresent(basicCommand -> executeCommand(basicCommand));
    }

    public void executeCommand(BasicCommand command) {
        switch (command.commandType) {
            case PRINT: terminal.printText(String.join(" ", command.expr.evaluate()));
                break;
            case LIST:
                Set<Map.Entry<Integer, BasicCommand>> entries = program.entrySet();
                entries.forEach(e -> {
                    String line = e.getKey() + " " + e.getValue().toString();
                    terminal.printText(line);
                    terminal.cursor(TerminalJPanel.Direction.START_OF_LINE);
                    terminal.cursor(TerminalJPanel.Direction.DOWN);
                });
                break;
            case GOTO:
                Integer lineNum = Integer.valueOf((String) command.expr.evaluate());
                List<Integer> lineNumbersG = new ArrayList<>(program.keySet());
                currentLineindex = lineNumbersG.indexOf(lineNum);
                break;
            case RUN:
                List<Integer> lineNumbers = new ArrayList<>(program.keySet());
                currentLineindex = 0;
                while(currentLineindex<lineNumbers.size()) {
                    BasicCommand basicCommand = program.get(lineNumbers.get(currentLineindex));
                    executeCommand(basicCommand);
                    if(basicCommand.commandType!=BasicCommandType.GOTO) {
                        currentLineindex++;
                    }
                    if(!terminal.keyStack.isEmpty()) {
                        break;
                    }
                }
                break;
        }
    }
    private Optional<BasicCommand> parseLine(String line) {
        List<String> tokens = getTokens(line);
        if (tokens.size() > 0) {
            if (isNumeric(tokens.get(0))) {
                // line with number
                String lineNumber = tokens.get(0);
                Optional<BasicCommand> basicCommand = parseCommand(tokens.subList(1, tokens.size()));
                basicCommand.ifPresent(command -> program.put(Integer.valueOf(lineNumber), command));
                return Optional.empty();
            } else {
                return parseCommand(tokens);
            }
        }
        return Optional.empty();
    }

    private Optional<BasicCommand> parseCommand(List<String> tokens) {
        if(tokens.size()>0 && tokens.get(0).equalsIgnoreCase("print")) {
            return Optional.of(new BasicCommand(BasicCommandType.PRINT, tokens.subList(1, tokens.size())));
        }
        if(tokens.size()>0 && tokens.get(0).equalsIgnoreCase("list")) {
            return Optional.of(new BasicCommand(BasicCommandType.LIST, tokens.subList(1, tokens.size())));
        }
        if(tokens.size()>0 && tokens.get(0).equalsIgnoreCase("goto")) {
            return Optional.of(new BasicCommand(BasicCommandType.GOTO, tokens.subList(1, tokens.size())));
        }
        if(tokens.size()>0 && tokens.get(0).equalsIgnoreCase("run")) {
            return Optional.of(new BasicCommand(BasicCommandType.RUN, tokens.subList(1, tokens.size())));
        }
        return Optional.empty();
    }

    public List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str," =+-*/(,)\"'", true);
        while (tokenizer.hasMoreElements()) {
            String t = tokenizer.nextToken();
            if(!" ".equals(t))
                tokens.add(t);
        }
        return tokens;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    enum BasicCommandType {PRINT, LIST, GOTO, RUN}

    private static class BasicCommand {
        private final BasicCommandType commandType;

        private final BasicExprWrapper expr;

        public BasicCommand(BasicCommandType commandType, List<String> args) {
            this.commandType = commandType;
            this.expr = new BasicExprWrapper(args);
        }

        public String toString() {
            return commandType.name() + " " + expr;
        }
        private static class BasicExprWrapper {

            BasicExpr expr;
            List<String> token;
            public BasicExprWrapper(List<String> token) {
                this.token = token;
                if(token.size()>=3 && "\"".equals(token.get(0)) && "\"".equals(token.get(2))) {
                    expr = new BasicString(token.get(1));
                } else {
                    if(token.size()==1) {
                        if(isNumeric(token.get(0))) {
                            expr = new BasicNumber(token.get(0));
                        } else {
                            expr = new BasicVar(token.get(0));
                        }
                    }
                }
            }

            public CharSequence evaluate() {
                return expr.evaluate();
            }
            public String toString() {
                return expr.toString();
            }

            public interface BasicExpr {
                String toString();
                CharSequence evaluate();
            }

            private static class BasicString implements BasicExpr {
                private final String s;
                public BasicString(String s) {
                    this.s = s;
                }
                public String toString() {
                    return "\"" + s + "\"";
                }
                public CharSequence evaluate() {
                    return s;
                }
            }

            private static class BasicVar implements BasicExpr {
                private final String s;
                public BasicVar(String s) {
                    this.s = s;
                }
                public String toString() {
                    return s;
                }
                public CharSequence evaluate() {
                    return "4711";
                }
            }

            private static class BasicNumber implements BasicExpr {
                private final String s;
                public BasicNumber(String s) {
                    this.s = s;
                }
                public String toString() {
                    return s;
                }
                public CharSequence evaluate() {
                    return s;
                }
            }
        }
    }
}
