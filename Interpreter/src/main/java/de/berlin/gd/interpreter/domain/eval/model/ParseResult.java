package de.berlin.gd.interpreter.domain.eval.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParseResult {
    Expression expr;
    List<String> restToken;
}