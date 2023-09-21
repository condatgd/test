package de.berlin.gd.calculator.controller;

import de.berlin.gd.calculator.keypad.Keypad;
import de.berlin.gd.calculator.lcddisplay.ReadableDisplay;
import de.berlin.gd.calculator.power.PowerSwitch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalculatorController {

    final PowerSwitch powerSwitch;
    final Keypad keypad;
    final List<ReadableDisplay> displays;

    public CalculatorController(PowerSwitch powerSwitch, Keypad keypad, List<ReadableDisplay> displays) {
        this.powerSwitch = powerSwitch;
        this.keypad  = keypad;
        this.displays = displays;
    }

    @GetMapping(value = "/on")
    public void on() {
        powerSwitch.switchPower(true);
    }

    @GetMapping(value = "/off")
    public void off() {
        powerSwitch.switchPower(false);
    }

    @PostMapping(value = "/calc")
    public CalculationResultDTO calc(@RequestParam String expression) {
        for(int i=0; i<expression.length(); i++) {
            keypad.click(expression.charAt(i));
        }
        return new CalculationResultDTO(expression, displays.get(0).read());
    }
}
