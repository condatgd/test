package de.berlin.gd;

import java.util.Scanner;

public class Snake {

    private int headX;
    private int headY;
    private GlobalKeyListener keyboard;

    public static void main(String[] args) {
        Snake snake = new Snake();
        snake.game();
    }

    private void game() {
        System.out.println("init ...");
        initSnake();
        System.out.println("init done");
        while(true) {
            if (keyboard.isKeyPressed()) {
                System.console().printf("hallo");
                System.out.println("key: " + keyboard.keyPressed());
                VT100.cls();
            }
        }
    }

    private void initSnake() {
        keyboard = new GlobalKeyListener();
        keyboard.keyPressed();
        headX=10;
        headY = 10;
    }

}
