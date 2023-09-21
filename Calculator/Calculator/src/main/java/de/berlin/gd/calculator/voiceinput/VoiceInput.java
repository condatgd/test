package de.berlin.gd.calculator.voiceinput;

import de.berlin.gd.calculator.domain.model.Code;
import de.berlin.gd.calculator.domain.ports.inbound_ports.CalculatorInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoiceInput {

    private final CalculatorInputPort calculatorInputPort;

    @Autowired
    PhonemCodeMapper phonemCodeMapper;


    public VoiceInput(CalculatorInputPort inputDevice) {
        this.calculatorInputPort = inputDevice;
    }

    public void hear(List<Phonem> phonems) {
        phonems.stream()
                .map(p -> phonemCodeMapper.phonemToCode(p))
                .forEach(calculatorInputPort::enterCode);
    }

}
