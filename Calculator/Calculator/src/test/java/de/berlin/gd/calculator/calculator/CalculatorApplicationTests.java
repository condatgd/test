package de.berlin.gd.calculator.calculator;

import de.berlin.gd.calculator.keypad.Keypad;
import de.berlin.gd.calculator.lcddisplay.LCDDisplay;
import de.berlin.gd.calculator.power.PowerSwitch;
import de.berlin.gd.calculator.voiceinput.Phonem;
import de.berlin.gd.calculator.voiceinput.VoiceInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CalculatorApplicationTests {

    @Autowired
    Keypad keypad;

    @Autowired
    VoiceInput voiceInput;

    @Autowired
    PowerSwitch powerSwitch;

    @SpyBean
    LCDDisplay lcdDisplay;

    @Test
    void powerOn() {
        powerSwitch.switchPower(true);
        Mockito.verify(lcdDisplay).show("0");
    }

    @Test
    void powerOff() {
        powerSwitch.switchPower(false);
        Mockito.verify(lcdDisplay).show("");
    }

    @Test
    void simplePlusOn() {
        powerSwitch.switchPower(true);
        keypad.click('2');
        keypad.click('5');
        keypad.click('+');
        keypad.click('2');
        keypad.click('=');
        Mockito.verify(lcdDisplay).show("27");
    }

    @Test
    void simplePlusOff() {
        powerSwitch.switchPower(false);
        keypad.click('2');
        keypad.click('+');
        keypad.click('2');
        keypad.click('=');
        Mockito.verify(lcdDisplay).show("");
    }

    @Test
    void simplePlusVoice() {
        powerSwitch.switchPower(true);
        voiceInput.hear(List.of(Phonem.N2, Phonem.PLUS, Phonem.N3, Phonem.EQ));
        Mockito.verify(lcdDisplay).show("5");
    }

}

