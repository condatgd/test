package de.berlin.gd.interpreter.repo.programs.adaptors;

import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import de.berlin.gd.interpreter.domain.ports.outbound_ports.ProgramRepoPort;
import de.berlin.gd.interpreter.repo.programs.mapper.ProgramDBOMapper;
import org.springframework.stereotype.Service;

@Service
public class ProgramRepoServiceAdaptor implements ProgramRepoPort {

    final
    ProgramRepo programRepo;

    public ProgramRepoServiceAdaptor(ProgramRepo programRepo) {
        this.programRepo = programRepo;
    }

    @Override
    public Statements loadProgram(String name) {
        Statements statements = new Statements();
        statements.setStatements(
                ProgramDBOMapper.INSTANCE.programDbosToStatements(programRepo.findAll())
        );
        return statements;
    }
}
