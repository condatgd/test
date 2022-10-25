package de.berlin.gd.interpreter.api.statements;

import de.berlin.gd.interpreter.api.statements.dto.StatementDTO;
import de.berlin.gd.interpreter.api.statements.dto.StatementsDTO;
import de.berlin.gd.interpreter.api.statements.mapper.StatementMapper;
import de.berlin.gd.interpreter.domain.ports.inbound_ports.InterpreterPort;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementController {

    InterpreterPort interpreterService;

    public StatementController(InterpreterPort interpreterService) {
        this.interpreterService = interpreterService;
    }

    @RequestMapping(
            value = {"/statement"},
            method = {RequestMethod.POST}
    )
    public String submitStatement(@RequestBody StatementDTO statement) {
        System.out.println(statement);
        return interpreterService.execute(StatementMapper.INSTANCE.statementDTOToStatement(statement));
    }

    @RequestMapping(
            value = {"/statements"},
            method = {RequestMethod.POST}
    )
    public String submitStatements(@RequestBody StatementsDTO statementsDTO) {
        Statements statements = StatementMapper.INSTANCE.statementsDTOToListOfStatement(statementsDTO);
        return interpreterService.execute(statements);
    }

}
