package de.berlin.gd.kantine.controller.mapper;

import de.berlin.gd.kantine.controller.statistik.model.KantinenStatistikDTO;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistik;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KantinenStatistikMapper {
    KantinenStatistikMapper INSTANCE = Mappers.getMapper(KantinenStatistikMapper.class);
    KantinenStatistikDTO toKantinenStatistikDTO(KantinenStatistik kantinenStatistik);

}