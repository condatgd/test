package de.berlin.gd.interpreter.api.statements.dto;

import lombok.Data;

@Data
public class StatementDTO {
    String stmtString;
    boolean debug = false;
}
