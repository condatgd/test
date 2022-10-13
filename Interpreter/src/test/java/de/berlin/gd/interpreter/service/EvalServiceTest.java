package de.berlin.gd.interpreter.service;

import de.berlin.gd.interpreter.domain.evaluator.impl.EvalServiceImpl;
import de.berlin.gd.interpreter.domain.interpreter.ports.ProgramRepoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://howtodoinjava.com/spring-boot2/testing/spring-boot-2-junit-5/
// https://www.arhohuttunen.com/spring-boot-webmvctest/

@ExtendWith(MockitoExtension.class)
class EvalServiceTest {

    @Mock
    ProgramRepoService programRepoService;

    @InjectMocks
    EvalServiceImpl serviceToTest;

    @Test
    public void testEval()
    {
//        when(programRepoService.loadProgram(any())).thenReturn(new Statements());

        String result = serviceToTest.eval("1+2+3");
        assertEquals("6.0", result);
    }

}