package de.berlin.gd.kantine.controller.web;

import de.berlin.gd.kantine.controller.mapper.EreignisMapper;
import de.berlin.gd.kantine.controller.ereignis.model.EreignisDTO;
import de.berlin.gd.kantine.domain.ereignis.port.EreignisEmpfaenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    EreignisEmpfaenger ereignisEmpfaenger;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/ereignis")
    public String erzeugeEreignis(EreignisDTO ereignisDTO) {
        ereignisEmpfaenger.empfangeEreignis(
                EreignisMapper.INSTANCE.toKantinenEreignisWithNowZeitpunkt(ereignisDTO)
        );
        return "home";
    }
}
