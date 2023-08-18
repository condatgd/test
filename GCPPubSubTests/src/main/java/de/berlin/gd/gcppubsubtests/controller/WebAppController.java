package de.berlin.gd.gcppubsubtests.controller;

import de.berlin.gd.gcppubsubtests.pub.PubConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class WebAppController {

    // tag::autowireGateway[]
    @Autowired
    private PubConfiguration.PublishGdTopicGateway messagingGateway;
    // end::autowireGateway[]

    @PostMapping("/publishMessage")
    public RedirectView publishMessage(@RequestParam("message") String message) {
        messagingGateway.sendToPubsub(message);
        return new RedirectView("/");
    }
}