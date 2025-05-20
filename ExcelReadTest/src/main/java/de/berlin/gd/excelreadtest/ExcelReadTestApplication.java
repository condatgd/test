package de.berlin.gd.excelreadtest;

import de.berlin.gd.excelreadtest.dbo.Table1;
import de.berlin.gd.excelreadtest.excel.ExcelLine;
import de.berlin.gd.excelreadtest.excel.ExcelReader;
import de.berlin.gd.excelreadtest.repo.Repo1;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ExcelReadTestApplication implements CommandLineRunner {

    @Autowired
    Repo1 repo1;

    public static void main(String[] args) {
        SpringApplication.run(ExcelReadTestApplication.class, args);
        // System.exit(0);
    }

    @Override
    public void run(String... args) throws IOException, InvalidFormatException {
        ExcelReader excelReader = new ExcelReader("Dashboard HWP.xlsx");
        List<ExcelLine> abgelehnteRechnungen =
                excelReader.readLinesFromSheetWithName("Abgelehnte Rechnungen");
        log.info("abgelehnteRechnungen: {}", abgelehnteRechnungen);
        // repo1.deleteAll();
        Table1 d = new Table1();
        d.setId("a3");
        d.setB("b");
        repo1.save(d);
    }
}