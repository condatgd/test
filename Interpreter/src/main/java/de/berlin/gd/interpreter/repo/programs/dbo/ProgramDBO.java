package de.berlin.gd.interpreter.repo.programs.dbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProgramDBO {
    @Id
    @Column(name = "prog_id", nullable = false)
    private String progId;

    @Column
    private String stmtString;

    public String getProgId() {
        return progId;
    }

    public void setProgId(String progId) {
        this.progId = progId;
    }


    public String getStmtString() {
        return stmtString;
    }

    public void setStmtString(String stmtStr) {
        this.stmtString = stmtStr;
    }
}
