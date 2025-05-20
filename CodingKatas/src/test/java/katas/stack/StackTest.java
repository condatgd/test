package katas.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {

    /*
         ADT Stacks(T) IS
          SORTS:
            stack, T, boolean
          OPERATIONS:
            newstack: stack
            push: T stack stack
            top: stack T
            pop: stack stack
            isempty: stack boolean
          RULES:
            isempty(newstack) = true
            isempty(push(x, l)) = false
            top(push(x, l)) = x
            pop(push(x, l)) = l
            push(top(s),pop(s)) = s
            pop(emptyStack()) = ERROR
            top(emptyStack()) = ERROR
          END Stacks.
     */

    @Test
    void newStackIsMT() {
        Stack<String> stack = new Stack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void newStackIsNotMTAfterPush() {
        Stack<String> stack = new Stack<>();
        stack.push("element");
        assertTrue(!stack.isEmpty());
    }

    @Test
    void whenElementIsPushed_thenElementIsOnTop() {
        Stack<String> stack = new Stack<>();
        stack.push("element");
        assertThat(stack.top(), is("element"));
    }

    @Nested
    class WhenElementIsPushed {
        Stack<String> stack;
        @BeforeEach
        void setUp() {
            stack = new Stack<>();
            stack.push("element");
        }
        @Test
        void andVorbedingung_thenElementIsOnTop() {
            // Vorbedingung
            assertThat(stack.top(), is("element"));
        }
    }

    @Test
    void newStackPopAfterPushGivesMTStack() {
        Stack<String> stack = new Stack<>();
        stack.push("element");
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void newStackPushAPushBPopGivesTopA() {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        stack.push("B");
        stack.pop();
        assertThat(stack.top(), is("A"));
    }

    @Test
    // push(top(s),pop(s)) = s
    void newStackPopPush() {
        Stack<String> stack = new Stack<>();
        stack.push("A");
        String top = stack.top();
        stack.pop().push(top);
        assertThat(stack.top(), is("A"));
    }

    @Test
    void newStackErrorTop() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Stack<String> stack = new Stack<>();
            stack.top();
        });
        String expectedMessage = "Stack is empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void newStackErrorPop() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            Stack<String> stack = new Stack<>();
            stack.pop();
        });
        String expectedMessage = "Stack is empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
