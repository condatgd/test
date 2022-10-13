import berlin.gd.Stack;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStack {

    @Test
    void testPopNothing() {
        Stack<String> stack = new Stack<>();
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isEmpty());
    }

    @Test
    void testPop1() {
        Stack<String> stack = new Stack<>(List.of("a","b"));
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isPresent());
        assertEquals("a", optElement.get());
    }

    @Test
    void testPop2() {
        Stack<String> stack = new Stack<>(List.of("a","b"));
        stack.pop();
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isPresent());
        assertEquals("b", optElement.get());
    }

    @Test
    void testPop3() {
        Stack<String> stack = new Stack<>(List.of("a","b"));
        stack.pop();
        stack.pop();
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isEmpty());
    }

    @Test
    void testPush1() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isPresent());
        assertEquals("a", optElement.get());
    }

    @Test
    void testPush2() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isPresent());
        assertEquals("b", optElement.get());
    }

    @Test
    void testPush3() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.pop();
        Optional<String> optElement = stack.pop();
        assertTrue(optElement.isPresent());
        assertEquals("a", optElement.get());
    }

}
