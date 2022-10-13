package de.berlin.gd;


import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.*;
import org.jnativehook.*;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GlobalKeyListener implements NativeKeyListener {
    // static Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());

    Queue<String> fifo = new LinkedList<>();

    public GlobalKeyListener() {
        // logger.setLevel(Level.OFF);
        // logger.setUseParentHandlers(false);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());
        fifo.add(keyText);
        System.out.println("Key Pressed: " + keyText);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        // System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        // System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

    public boolean isKeyPressed() {
        return fifo.peek()!=null;
    }

    public String keyPressed() {
        return fifo.remove();
    }

    public static void main(String[] args) throws NativeHookException {
        // logger.setLevel(Level.OFF);
        // logger.setUseParentHandlers(false);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }
}