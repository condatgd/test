package de.berlin.gd.calculator.voiceinput;

import de.berlin.gd.calculator.domain.model.Code;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PhonemCodeMapper {

    Code phonemToCode(Phonem phonem);

}
