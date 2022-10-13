package de.berlin.gd.interpreter.jpa;

import de.berlin.gd.interpreter.repo.programs.adaptors.ProgramRepo;
import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class ProgramRepoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    ProgramRepo programRepo;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @Test
    public void load() {
        ProgramDBO e = new ProgramDBO();
        e.setProgId("p1");
        e.setStmtString("a+b");
        programRepo.save(e);
        long count = programRepo.count();
        int size = programRepo.findAll().size();
        Assertions.assertEquals(count, size);
    }
}
