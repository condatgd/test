package de.berlin.gd.ownterminal.snake;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Snake {

    private List<Position> body;
    private Direction direction;
    private int cols;
    private int rows;

    public Snake(int cols, int rows, int startx, int starty) {
        this.cols = cols;
        this.rows = rows;
        direction = Direction.RIGHT;
        body = new ArrayList<>();
        body.add(new Position(startx, starty));
    }

    public Position head() {
        return body.get(0);
    }

    public Optional<Position> neck() {
        if(body.size()>1) {
            return Optional.of(body.get(1));
        } else {
            return Optional.empty();
        }
    }

    public Position move() {
        Position headCurrent = body.get(0);
        Position headNew = new Position(headCurrent);
        headNew.move(direction);
        headNew.boundaryCheck(cols, rows);
        Position removedPosition = body.remove(body.size() - 1);
        body.add(0, headNew);
        return removedPosition;
    }

    public void changeDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow(Position removedPosition) {
        body.add(removedPosition);
    }


    protected enum Direction {UP, DOWN, LEFT, RIGHT}


    @Data
    @AllArgsConstructor
    protected class Position {
        private int x, y;

        public Position(Position p) {
            this.x = p.getX();
            this.y = p.getY();
        }

        public void move(Direction direction) {
            switch (direction) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
        }

        public void boundaryCheck(int cols, int rows) {
            if (x >= cols) x = 0;
            if (x < 0) x = cols - 1;
            if (y >= rows) y = 0;
            if (y < 0) y = rows - 1;
        }
    }
}
