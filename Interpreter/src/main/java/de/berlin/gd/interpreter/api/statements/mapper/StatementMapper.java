package de.berlin.gd.interpreter.api.statements.mapper;

import de.berlin.gd.interpreter.api.statements.dto.StatementDTO;
import de.berlin.gd.interpreter.api.statements.dto.StatementsDTO;
import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StatementMapper {
    StatementMapper INSTANCE = Mappers.getMapper( StatementMapper.class );
    Statement statementDTOToStatement(StatementDTO dto);
    Statements statementsDTOToListOfStatement(StatementsDTO dto);

}
