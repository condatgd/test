package de.berlin.gd.excelreadtest.dbo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Table1 {
    @Id
    private String id;
    private String b;

}
