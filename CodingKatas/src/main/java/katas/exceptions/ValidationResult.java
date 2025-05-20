package katas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ValidationResult {
    boolean isValid;
    String reason;
}
