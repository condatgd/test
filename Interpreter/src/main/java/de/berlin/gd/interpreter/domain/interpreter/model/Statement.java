package de.berlin.gd.interpreter.domain.interpreter.model;

import lombok.Data;

@Data
public class Statement {
    String stmtString;
    boolean debug = false;
}
