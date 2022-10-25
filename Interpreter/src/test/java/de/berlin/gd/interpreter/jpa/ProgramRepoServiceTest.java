package de.berlin.gd.interpreter.jpa;

import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import de.berlin.gd.interpreter.domain.ports.outbound_ports.ProgramRepoPort;
import de.berlin.gd.interpreter.repo.programs.adaptors.ProgramRepo;
import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProgramRepoServiceTest {

    @Autowired
    ProgramRepo programRepo;

    @Autowired
    ProgramRepoPort programRepoService;

    @Test
    public void load() {
        ProgramDBO e = new ProgramDBO();
        e.setProgId("p1");
        e.setStmtString("a+b");
        programRepo.save(e);
        Statements p1 = programRepoService.loadProgram("p1");
        Assertions.assertNotNull(p1);
    }
}
