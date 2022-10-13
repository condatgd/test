package de.berlin.gd.interpreter.domain.eval;

import de.berlin.gd.interpreter.domain.eval.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class ExprParser {

    public static final String DELIM = " =+.-*/()\"',<>";

    public Optional<ParseResult> parse(String s) {
        return parseExpr(getTokens(s));
    }

    public Optional<ParseResult> parseExpr(List<String> token) {
        // expr = const | var | ( expr ) | expr op expr
        if (token.size()>0) {
            Optional<ParseResult> firstExpr =
                    Optional.ofNullable(
                            parseConst(token)
                                    .orElse(parseVar(token)
                                            .orElse(parseBracket(token)
                                                    .orElse(null))
                                    ));
            if (firstExpr.isPresent()) {
                ParseResult e1 = firstExpr.get();
                List<String> restToken = e1.getRestToken();

                if(e1.getExpr() instanceof VariableExpression && restToken.size()>0 && restToken.get(0).equals("=")) {
                    Optional<ParseResult> e2 = parseExpr(restToken.subList(1, restToken.size()));
                    if(e2.isPresent())
                        return Optional.of(
                                new ParseResult(
                                    new AssignmentExpression(
                                            (VariableExpression) e1.getExpr(),e2.get().getExpr()),
                                            e2.get().getRestToken()
                                ));
                }

                Optional<ParseResult> infixExpr = parseInfixEpr(e1, restToken);
                if (infixExpr.isPresent()) return infixExpr;

                if(e1.getExpr() instanceof VariableExpression) {
                    Optional<ParseResult> prefixExpr = parsePrefixExpr(e1.getExpr().toString(), restToken);
                    if (prefixExpr.isPresent()) return prefixExpr;
                }

                return firstExpr;
            }
        }
        return Optional.empty();
    }

    private Optional<ParseResult> parsePrefixExpr(String f, List<String> restToken) {
        if (restToken.size() > 0 && restToken.get(0).equals("(")) {
            Optional<ParseResult> argExprs = parseExprList(restToken.subList(1, restToken.size()));
            if (argExprs.isPresent()) {
                ParseResult argsResult = argExprs.get();
                List<String> restToken1 = argsResult.getRestToken();
                if(restToken1.size()>0 && restToken1.get(0).equals(")"))
                    return Optional.of(
                            new ParseResult(
                                    new FunctionCallExpression(f, argsResult.getExpr()),
                                    restToken1.subList(1, restToken1.size())
                            )
                    );
            }
        }
        return Optional.empty();
    }

    private Optional<ParseResult> parseExprList(List<String> token) {
        if (token.size() > 0) {
            Optional<ParseResult> argExpr = parseExpr(token);
            if (argExpr.isPresent()) {
                List<Expression> args = new ArrayList<>();
                args.add(argExpr.get().getExpr());
                List<String> restToken = argExpr.get().getRestToken();
                while(restToken.size()>0 && restToken.get(0).equals(",")) {
                    argExpr = parseExpr(restToken.subList(1,restToken.size()));
                    if(argExpr.isPresent()) {
                        args.add(argExpr.get().getExpr());
                        restToken = argExpr.get().getRestToken();
                    } else
                        break;
                }
                return Optional.of(
                        new ParseResult(
                                new ExpressionList(args),
                                restToken
                        )
                );
            }
        }
        return Optional.empty();
    }

    private Optional<ParseResult> parseInfixEpr(ParseResult e1, List<String> restToken) {
        if (restToken.size() > 0 && isInfixOp(restToken.get(0))) {
            Optional<ParseResult> secondExpr = parseExpr(restToken.subList(1, restToken.size()));
            if (secondExpr.isPresent()) {
                return Optional.of(
                        new ParseResult(
                                new InfixExpression(e1.getExpr(), restToken.get(0), secondExpr.get().getExpr()),
                                secondExpr.get().getRestToken()
                        )
                );
            }
        }
        return Optional.empty();
    }

    private boolean isInfixOp(String op) {
        return "+-*/.<>".contains(op);
    }

    private Optional<ParseResult> parseConst(List<String> token) {
        String t = token.get(0);
        if(t.equals("true")) {
            return Optional.of(new ParseResult(new BooleanExpression(true), token.subList(1, token.size())));
        }
        if(t.equals("false")) {
            return Optional.of(new ParseResult(new BooleanExpression(false), token.subList(1, token.size())));
        }
        if (isNumeric(t)) {
            return Optional.of(new ParseResult(new NumberExpression(Double.parseDouble(t)), token.subList(1, token.size())));
        }
        if(t.equals("\"")) {
            token = token.subList(1, token.size());
            int nextDQuoteIndex = token.indexOf("\"");
            if(nextDQuoteIndex>-1) {
                List<String> sToken = token.subList(0,nextDQuoteIndex);
                String str = String.join("", sToken);
                return Optional.of(new ParseResult(new StringExpression(str), token.subList(nextDQuoteIndex + 1, token.size())));
            }
        }
        return Optional.empty();
    }

    private boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private Optional<ParseResult> parseVar(List<String> token) {
        String t = token.get(0);
        if (isIdentifier(t)) {
            return Optional.of(new ParseResult(new VariableExpression(t), token.subList(1, token.size())));
        }
        return Optional.empty();
    }

    private boolean isIdentifier(String s) {
        return !DELIM.contains(s.substring(0,1));
    }

    private Optional<ParseResult> parseBracket(List<String> token) {
        String t = token.get(0);
        if (t.equals("(")) {
            Optional<ParseResult> optE = parseExpr(token.subList(1, token.size()));
            if (optE.isPresent()) {
                List<String> restToken1 = optE.get().getRestToken();
                if (restToken1.size() > 0 && restToken1.get(0).equals(")")) {
                    return Optional.of(
                            new ParseResult(
                                    optE.get().getExpr(),
                                    restToken1.subList(1, restToken1.size())
                            )
                    );
                }
            }
        }
        return Optional.empty();
    }

    List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, DELIM, true);
        while (tokenizer.hasMoreElements()) {
            String t = tokenizer.nextToken();
            if(!" ".equals(t))
                tokens.add(t);
        }
        return tokens;
    }
    @Data
    @AllArgsConstructor
    public static class ParseResult {
        Expression expr;
        List<String> restToken;
    }

}
