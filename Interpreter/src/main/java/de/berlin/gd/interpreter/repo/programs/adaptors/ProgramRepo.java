package de.berlin.gd.interpreter.repo.programs.adaptors;

import de.berlin.gd.interpreter.repo.programs.dbo.ProgramDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepo extends JpaRepository<ProgramDBO, String> {
}
