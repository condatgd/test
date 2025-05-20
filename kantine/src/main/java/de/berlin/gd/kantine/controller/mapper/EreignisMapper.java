package de.berlin.gd.kantine.controller.mapper;

import de.berlin.gd.kantine.controller.ereignis.model.EreignisDTO;
import de.berlin.gd.kantine.controller.ereignis.model.EreignisResultDTO;
import de.berlin.gd.kantine.domain.ereignis.model.KantinenEreignis;
import de.berlin.gd.kantine.domain.zeit.Zeitpunkt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EreignisMapper {
    EreignisMapper INSTANCE = Mappers.getMapper(EreignisMapper.class);
    KantinenEreignis toKantinenEreignis(EreignisDTO ereignisDTO);

    default KantinenEreignis toKantinenEreignisWithNowZeitpunkt(EreignisDTO ereignisDTO) {
        KantinenEreignis kantinenEreignis = toKantinenEreignis(ereignisDTO);
        kantinenEreignis.setZeitpunkt(new Zeitpunkt(LocalDateTime.now()));
        return kantinenEreignis;
    }

    EreignisResultDTO toKantinenEreignisResultDTO(KantinenEreignis kantinenEreignis);

    List<EreignisResultDTO> ereignisToEreignisResultDTOList(List<KantinenEreignis> ereignisDTOs);
}