package de.berlin.gd.interpreter.integration;

import de.berlin.gd.interpreter.InterpreterApplication;
import de.berlin.gd.interpreter.repo.programs.adaptors.ProgramRepo;
import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InterpreterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts={"classpath:create_data.sql"}, executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts={"classpath:drop_data.sql"}, executionPhase = AFTER_TEST_METHOD)
public class StatementControllerIT {
    @LocalServerPort
    private int port;

    @Resource
    private ProgramRepo programRepo;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void test1() throws JSONException {

        ProgramDBO entry = new ProgramDBO();
        entry.setProgId("p1");
        entry.setStmtString("a");
        programRepo.save(entry);

        System.out.println("count: " + programRepo.count());

        HttpEntity<String> entity = new HttpEntity<>(
                "{\n" +
                "\t\"statements\": [\n" +
                "\t\t{\"stmtString\": \"new\"},\n" +
                "\t\t{\"stmtString\": \"a=100\"},\n" +
                "\t\t{\"stmtString\": \"a=a+1\"},\n" +
                "\t\t{\"stmtString\": \"a=a+a\"}\n" +
                "\t]\n" +
                "}", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/statements"),
                HttpMethod.POST, entity, String.class);

        String expected = "";

        // JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void test2() throws JSONException {

        ProgramDBO entry = new ProgramDBO();
        entry.setProgId("p1");
        entry.setStmtString("ab");
        programRepo.save(entry);

        System.out.println("count: " + programRepo.count());

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
