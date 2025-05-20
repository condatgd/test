package katas.textumbruch.v0;

import java.util.*;

public class WortSequenz implements Iterator<String> {
    private final StringTokenizer stringTokenizer;
    private final int zeilenLaenge;

    private final Stack<String> aheadTokenStack;

    public WortSequenz(String text, int zeilenLaenge) {
        stringTokenizer = new StringTokenizer(text, " \n\t", false);
        this.zeilenLaenge = zeilenLaenge;
        aheadTokenStack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return (!aheadTokenStack.isEmpty()) || stringTokenizer.hasMoreTokens();
    }

    @Override
    public String next() {
        if(!aheadTokenStack.isEmpty()) {
            return aheadTokenStack.pop();
        } else {
            return getNextTokenWithValidSize();
        }
    }

    private String getNextTokenWithValidSize() {
        String token = stringTokenizer.nextToken();
        if(zeilenLaenge<token.length()) {
            List<String> subtokens = Arrays.asList(token.split("(?<=\\G.{" + zeilenLaenge + "})"));
            Collections.reverse(subtokens);
            subtokens.forEach(aheadTokenStack::push);
            return next();
        }
        return token;
    }

    public String lookAhead() {
        if(aheadTokenStack.isEmpty()) {
            if(hasNext()) {
                aheadTokenStack.push(next());
            }
        }
        return aheadTokenStack.peek();
    }

}
