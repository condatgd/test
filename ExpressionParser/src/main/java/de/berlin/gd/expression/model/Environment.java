package de.berlin.gd.expression.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Environment {

    private final Stack<Map<String, Expression>> envStack;

    public Environment() {
        envStack = new Stack<>();
        pushNewEnv();
    }

    public Expression get(String key) {
        int size = envStack.size();
        for(int i=size-1; i>=0; i--) {
            Expression expression = envStack.get(i).get(key);
            if(expression!=null)
                return expression;
        }
        return null;
    }

    public void put(String key, Expression ex) {
        envStack.peek().put(key,ex);
    }

    public String toString() {
        return envStack.toString();
    }

    public void pushNewEnv() {
        Map<String, Expression> env = new HashMap<>();
        envStack.push(env);
    }

    public Map<String, Expression> popEnv() {
        return envStack.pop();
    }
}
