package de.berlin.gd.interpreter.api;

import de.berlin.gd.interpreter.api.statements.StatementController;
import de.berlin.gd.interpreter.domain.interpreter.InterpreterService;
import de.berlin.gd.interpreter.domain.interpreter.model.Statement;
import de.berlin.gd.interpreter.domain.interpreter.model.Statements;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatementController.class)

class StatementControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    InterpreterService interpreterService;

    @Test
    void submitStatement() throws Exception {
        when(interpreterService.execute((Statement) any())).thenReturn("2");

        mvc.perform(
                post("/statement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"stmtString\": \"1+1\"}"))

                .andExpect(status().isOk())
                .andExpect(content().string(
                        is(equalTo("2")))
                );
    }

    @Test
    void submitStatements() throws Exception {
        when(interpreterService.execute((Statements) any())).thenReturn("");

        mvc.perform(
                        post("/statements")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "\t\"statements\": [\n" +
                                        "\t\t{\"stmtString\": \"a=100\"},\n" +
                                        "\t\t{\"stmtString\": \"a=a+1\"}\n" +
                                        "\t]\n" +
                                        "}"))

                .andExpect(status().isOk())
                .andExpect(content().string(
                        is(equalTo("")))
                );
    }
}