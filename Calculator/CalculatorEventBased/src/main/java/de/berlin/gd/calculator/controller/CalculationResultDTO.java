package de.berlin.gd.calculator.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CalculationResultDTO {
    private String expression;
    private String result;

    public CalculationResultDTO(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }
}
