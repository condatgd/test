package de.berlin.gd.calculator.eventlistener;

import de.berlin.gd.calculator.domain.model.CodeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListner {
    @EventListener
    void handleCodeEvent(CodeEvent event) {
        System.out.println("CodeEvent: " + event);
    }
}
