package de.berlin.gd.interpreter.repo.programs.mapper;

import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramDBOMapper {
    ProgramDBOMapper INSTANCE = Mappers.getMapper( ProgramDBOMapper.class );

    Statement programDboToStatement(ProgramDBO dbo);

    List<Statement> programDbosToStatements(List<ProgramDBO> dbos);
}
