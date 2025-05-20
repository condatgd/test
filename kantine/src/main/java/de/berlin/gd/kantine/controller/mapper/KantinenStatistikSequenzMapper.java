package de.berlin.gd.kantine.controller.mapper;

import de.berlin.gd.kantine.controller.statistik.model.KantinenStatistikSequenzDTO;
import de.berlin.gd.kantine.domain.statistik.model.KantinenStatistikSequenz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KantinenStatistikSequenzMapper {
    KantinenStatistikSequenzMapper INSTANCE = Mappers.getMapper(KantinenStatistikSequenzMapper.class);
    KantinenStatistikSequenzDTO toKantinenStatistikSequenzDTO(KantinenStatistikSequenz kantinenStatistik);

}