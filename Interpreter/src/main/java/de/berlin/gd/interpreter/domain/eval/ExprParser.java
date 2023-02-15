package de.berlin.gd.interpreter.domain.eval;

import de.berlin.gd.interpreter.domain.eval.model.ParseResult;

import java.util.Optional;

public interface ExprParser {
    Optional<ParseResult> parse(String s);
}
