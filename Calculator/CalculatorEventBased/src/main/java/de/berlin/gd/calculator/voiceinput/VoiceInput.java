package de.berlin.gd.calculator.voiceinput;

import de.berlin.gd.calculator.domain.model.CodeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoiceInput {

    private final PhonemCodeMapper phonemCodeMapper;
    private final ApplicationEventPublisher publisher;

    public VoiceInput(ApplicationEventPublisher publisher, PhonemCodeMapper phonemCodeMapper) {
        this.publisher = publisher;
        this.phonemCodeMapper = phonemCodeMapper;
    }

    public void hear(List<Phonem> phonems) {
        phonems.stream()
                .map(p -> phonemCodeMapper.phonemToCode(p))
                .forEach(codeToEnter -> publisher.publishEvent(new CodeEvent(codeToEnter)));
    }

}
