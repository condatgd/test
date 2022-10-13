package de.berlin.gd.interpreter.api.statements.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatementsDTO {
    List<StatementDTO> statements;
    boolean debug = false;
}
