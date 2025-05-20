package de.berlin.gd.kantine.controller.ereignis;

import de.berlin.gd.kantine.controller.mapper.EreignisMapper;
import de.berlin.gd.kantine.controller.ereignis.model.EreignisDTO;
import de.berlin.gd.kantine.controller.ereignis.model.EreignisResultDTO;
import de.berlin.gd.kantine.domain.ereignis.port.EreignisBereitstellung;
import de.berlin.gd.kantine.domain.ereignis.port.EreignisEmpfaenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ereignis")
public class EreignisRestController {

    @Autowired
    EreignisEmpfaenger ereignisEmpfaenger;

    @Autowired
    EreignisBereitstellung ereignisBereitstellung;

    @GetMapping
    public List<EreignisResultDTO> ereignisse() {
        return EreignisMapper.INSTANCE.ereignisToEreignisResultDTOList(
                ereignisBereitstellung.alleEreignisse()
        );
    }

    @PostMapping
    public String erzeugeEreignis(@RequestBody EreignisDTO ereignisDTO) {
        ereignisEmpfaenger.empfangeEreignis(
                EreignisMapper.INSTANCE.toKantinenEreignisWithNowZeitpunkt(ereignisDTO)
        );
        return "thanks for telling " + ereignisDTO.getUser();
    }
}
