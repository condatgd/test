package katas.domino;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DominoField {

    public static void main(String[] args) {
        String[] field = {
                "-||/",
                "/||\\"
        };
        Pos startPosition = new Pos(0,0);
        actuate(field, startPosition, Direction.down);
        for (String s : field) {
            System.out.println(s);
        }
    }

    private static void actuate(String[] field, Pos startPosition, Direction direction) {
        changeSymbolAtPosition(field, startPosition, '.');
        Pos nextPosition = new Pos(startPosition.x + direction.getDx(), startPosition.y + direction.getDy());
        Optional<Character> symbolAtPositionOpt = getSymbolAtPosition(field, nextPosition);
        symbolAtPositionOpt.ifPresent(System.out::println);
        if(symbolAtPositionOpt.isPresent()) {
            Direction newDirection = calcNewDirection(direction, symbolAtPositionOpt.get());
            if(newDirection!=null) {
                actuate(field, nextPosition, newDirection);
            }
        }
    }

    @Nullable
    private static Direction calcNewDirection(Direction direction, Character symbolAtPositionOpt) {
        Direction newDirection = null;
        if(symbolAtPositionOpt == '/') {
            newDirection = switch (direction) {
                case up -> Direction.left;
                case down -> Direction.right;
                case left -> Direction.up;
                case right -> Direction.down;
            };
        }
        if(symbolAtPositionOpt == '\\') {
            newDirection = switch (direction) {
                case up -> Direction.right;
                case down -> Direction.left;
                case left -> Direction.down;
                case right -> Direction.up;
            };
        }
        if(symbolAtPositionOpt == '-') {
            newDirection = switch (direction) {
                case up, down -> direction;
                default -> null;
            };
        }
        if(symbolAtPositionOpt == '|') {
            newDirection = switch (direction) {
                case left, right -> direction;
                default -> null;
            };
        }
        return newDirection;
    }

    private static void changeSymbolAtPosition(String[] field, Pos position, char c) {
        if(checkInbound(field, position)) {
            field[position.y] = field[position.y].substring(0, position.x) + c + field[position.y].substring(position.x+1);
        }
    }

    private static Optional<Character> getSymbolAtPosition(String[] field, Pos position) {
        if(checkInbound(field, position)) {
            return Optional.of(field[position.y].charAt(position.x));
        }
        return Optional.empty();
    }

    private static boolean checkInbound(String[] field, Pos position) {
        return 0 <= position.y && position.y < field.length && 0 <= position.x && position.x < field[position.y].length();
    }

    public record Pos(int x, int y) {}

    @Getter
    public enum Direction { up(0,-1), down(0,1), left(-1,0), right(1,0);
        private final int dx;
        private final int dy;
        Direction(int x, int y) {
            this.dx = x;
            this.dy = y;
        }
    }

}
