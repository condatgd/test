package de.berlin.gd.interpreter.domain.interpreter.model;

import lombok.Data;

import java.util.List;

@Data
public class Statements {
    List<Statement> statements;
    boolean debug = false;
}
