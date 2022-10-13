package de.berlin.gd.interpreter.repo.programs.adaptors;

import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import de.berlin.gd.interpreter.domain.interpreter.ports.ProgramRepoService;
import de.berlin.gd.interpreter.repo.programs.mapper.ProgramDBOMapper;
import org.springframework.stereotype.Service;

@Service
public class ProgramRepoServiceImpl implements ProgramRepoService {

    final
    ProgramRepo programRepo;

    public ProgramRepoServiceImpl(ProgramRepo programRepo) {
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
